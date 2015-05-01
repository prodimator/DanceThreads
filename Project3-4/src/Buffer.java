import java.util.concurrent.atomic.AtomicBoolean;


public class Buffer {
	
	int[] vals;
	int low, high, size;
	public AtomicBoolean using = new AtomicBoolean(false);


	public Buffer(int len){
	vals = new int[len];
	}
	public synchronized void put(int v){
		while (size==vals.length){
			try{ this.wait(); }
			catch (InterruptedException e){}
		}
		vals[high] = v;
		high = (high+1) % vals.length;
		size++;
		notifyAll();
	}
	
	public synchronized int get() {
	int v;
	while (size<=0){
		try{ wait(); }
		catch(InterruptedException e){}
	}
	v = vals[low];
	low = (low+1) % vals.length;
	size--;

	notifyAll();
	return v;
	}
	
}
