import java.io.IOException;

public class ScanApp {
    public static void main(String args[]) throws IOException, InterruptedException {
        URLPool pool = new URLPool(args[0], Integer.parseInt(args[1]), Integer.parseInt(args[2]));
        for (int i = 0; i < Integer.parseInt(args[2]); i++) {
            CrawlerTask crawler = new CrawlerTask(pool);
            new Thread(crawler).start();
            System.out.println("Crawler " + i + " running");
        }
    }
}
