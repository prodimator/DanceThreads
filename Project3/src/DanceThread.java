public class DanceThread implements Runnable {

	public int numThreads = 1;
    public void run() {
    	System.out.println("Thread started...");
        for (int i =0; i < 10; i++){
        	 try
             {
                 Thread.sleep(100);
                 this.out(i);
             }
             catch (InterruptedException interruptedException)
             {
                /*Interrupted exception will be thrown when a sleeping or waiting
                 * thread is interrupted.
                 */
                 System.out.println( "Second Thread is interrupted when it is sleeping" +interruptedException);
             }
        }
    }
    public void out(int i){
    	System.out.println(i);
    }
 
}
