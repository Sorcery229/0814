import java.util.LinkedList;

public class URLPool {

    private LinkedList<URLDepthPair> Done = new LinkedList<URLDepthPair>();
    private LinkedList<URLDepthPair> NotDone = new LinkedList<URLDepthPair>();
    private int Depth;
    private int Waiting;
    private int Threads;

    public URLPool(String url, int depth, int threads) {
        NotDone.add(new URLDepthPair(url, depth));
        Depth = depth;
        Threads = threads;
    }

    public synchronized URLDepthPair get() throws InterruptedException {
        if (isEmpty()) {
            Waiting++;
            if (Waiting == Threads) {
                getSites();
                System.exit(0);
            }
            wait();
        }
        return NotDone.removeFirst();
    }
    public synchronized void addNotProcessed(URLDepthPair pair) {
        NotDone.add(pair);
        if (Waiting > 0) {
            Waiting--;
            notify();
        }
    }

    private boolean isEmpty() {
        if (NotDone.size() == 0) return true;
        return false;
    }

    public void getSites() {
        System.out.println("Depth: " + Depth);
        for (int i = 0; i < Done.size(); i++) {
            System.out.println( Depth - Done.get(i).getDepth() + " " +  Done.get(i).getURL());
        }
        System.out.println("Links visited: " + Done.size());
    }


    public void addProcessed(URLDepthPair pair) {
        Done.add(pair);
    }

    public LinkedList<URLDepthPair> getProcessed()
    {
        return Done;
    }

    public LinkedList<URLDepthPair> getNotProcessed()
    {
        return NotDone;
    }

}
