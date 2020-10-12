import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;


public class CrawlerTask implements Runnable {
    final static int AnyDepth = 0;
    private URLPool Pool;

    @Override
    public void run() {
        try {
            Scan();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public CrawlerTask(URLPool pool) {
        Pool = pool;
    }

    private  void  Scan() throws IOException, InterruptedException {
        while (true) {
            Process(Pool.get());
        }
    }

    private void Process(URLDepthPair pair) throws IOException{
        URL url = new URL(pair.getURL());
        URLConnection connection = url.openConnection();
        String redirect = connection.getHeaderField("Location");
        if (redirect != null) {
            connection = new URL(redirect).openConnection();
        }
        Pool.addProcessed(pair);
        if (pair.getDepth() == 0) return;
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String input;
        while ((input = reader.readLine()) != null) {
            String prefix = "http";
            while (input.contains("a href=\"" + prefix)) {
                input = input.substring(input.indexOf("a href=\"" + prefix) + 8);
                String link = input.substring(0, input.indexOf('\"'));
                if (link.contains(" "))
                    link = link.replace(" ", "%20");
                if (Pool.getNotProcessed().contains(new URLDepthPair(link, AnyDepth)) ||
                        Pool.getProcessed().contains(new URLDepthPair(link, AnyDepth))) continue;
                Pool.addNotProcessed(new URLDepthPair(link, pair.getDepth() - 1));
            }
        }
        reader.close();
    }
}
