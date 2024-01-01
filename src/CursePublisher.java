import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ExecutorService;

import java.util.concurrent.Flow.Publisher;
import java.util.concurrent.Flow.Subscriber;

/**
	publishes random curses!
*/
public class CursePublisher implements Publisher<Curse>{
	
	/*private final ExecutorService executor = ForkJoinPool.commonPool();*/
	private boolean subbed;
	private ExecutorService executor;
	
	public CursePublisher(ExecutorService executor){
		this.subbed=false;
		this.executor=executor; // should it be final?
	}

	public CursePublisher(){
		this(ForkJoinPool.commonPool()); // daemon
	}

	@Override
	public void subscribe(Subscriber<? super Curse> subscriber){
		if(subbed)throw new IllegalArgumentException("publisher already subbed");
		this.subbed=true;
		PrintSubscription<Curse> cursePrintSub=
			new PrintSubscription<Curse>(
				subscriber,
				executor,
				new RCRecorder(
					new DataSource<Curse>(){
						@Override	public Curse get(){
							return Curse.getRandCurse();
						}
						@Override public void end(){ /* do nothing*/ }
					}
				)
		);
		subscriber.onSubscribe(cursePrintSub);
	}


}
