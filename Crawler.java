import java.io.*;
import java.net.*;
import java.util.LinkedList;


public class Crawler {
    private LinkedList<URLDepthPair> Done = new LinkedList<URLDepthPair>();
    private LinkedList<URLDepthPair> NotDone = new LinkedList<URLDepthPair>();

    public Crawler(String host, int depth) {
        NotDone.add(new URLDepthPair(host, depth));
    }

    public void Scan() throws IOException {

        while (NotDone.size() > 0) {
            Process(NotDone.removeFirst());
        }
    }

    public void Process(URLDepthPair pair) throws IOException{
        String input;
        URL url = new URL(pair.getURL());
        URLConnection connection = url.openConnection();
        String redirect = connection.getHeaderField("Location");
        if (redirect != null) {
            connection = new URL(redirect).openConnection();
        }
        //connection.addRequestProperty("User-Agent", "Opera");
        //connection.setReadTimeout(5000);
        //connection.setConnectTimeout(5000);
        Done.add(pair);
        if (pair.getDepth() == 0) return;
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        while ((input = reader.readLine()) != null) {
            String prefix = "http";
            while (input.contains("a href=\"" + prefix)) {
                input = input.substring(input.indexOf("a href=\"" + prefix) + 8);
                String link = input.substring(0, input.indexOf('\"'));
                if(link.contains(" "))
                   link = link.replace(" ", "%20");
                if (NotDone.contains(new URLDepthPair(link, 0)) ||
                        Done.contains(new URLDepthPair(link, 0))) continue;
                NotDone.add(new URLDepthPair(link, pair.getDepth() - 1));
            }
        }
        reader.close();
    }

    public void Show() {
        for (var link : Done)
            System.out.println(link.getURL());
        System.out.println("Links visited: " + Done.size());
    }
}


