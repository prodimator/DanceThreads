public class DanceThread extends Thread {
	public int numThreads = 1;
    public void run() {
        System.out.printf("This is thread %d\n", numThreads);
        numThreads++;
    }
}
