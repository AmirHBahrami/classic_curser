import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;

import java.util.concurrent.Future;
import java.util.concurrent.ExecutorService;

public class PrintSubscription<T> implements Subscription {

	private Subscriber<T> sub;
	private ExecutorService ex;
	private Future<?> future;
	private boolean completed;
	private DataSource<T> ds;

	public PrintSubscription(Subscriber sub, ExecutorService ex,DataSource<T> ds){
		this.sub=sub;
		this.ex=ex;
		this.ds=ds;
		this.completed=false; // unnecessary tho
	}

	public void stop(){
		ds.end();
		sub.onComplete();
		return;
	}

	@Override
	public synchronized void request(long n){

		if(this.completed){
			this.stop();
			return;
		}

		try{
			Thread.sleep(250);
		}catch(InterruptedException ie){
			this.stop();
			return;
		}

		// main thing
		this.future=ex.submit(()->{	
			sub.onNext(
				ds.get()  // XXX THE WHOLE POINT OF THIS (STUPID) PROGRAM
			);
		});
	}

	@Override 
	public synchronized void cancel(){
		completed=true;
		this.stop();
		if(future!=null)future.cancel(false);
	}
	
}
