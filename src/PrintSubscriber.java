import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;

/** prints an object upon receipt */
public class PrintSubscriber<T> implements Subscriber<T>{
	
	private long buffSize;
	private long count;
	private Subscription sub;

	private boolean stopped=false;

	public PrintSubscriber(long buffSize){
		this.buffSize=buffSize;
		this.count=0;
	}

	public PrintSubscriber(){
		this(16);
	}
	
	// just to stop the program by giving a signal to stdin
	public synchronized void stop(){
		this.stopped=true;
		this.sub.cancel();
	}
	
	@Override
	public void onSubscribe(Subscription sub){
		this.sub=sub;
		this.count=0; // not used for anything at all - just filling java's wank api
		while(!stopped) // while true - but for cultered Gentlemen!
			this.sub.request(++count);
	}
	
	@Override
	public void onNext(T item){
		System.out.println(item); // consume the dude
	}
	
	@Override
	public void onError(Throwable ex){ex.printStackTrace();}

	@Override
	public void onComplete(){}

}
