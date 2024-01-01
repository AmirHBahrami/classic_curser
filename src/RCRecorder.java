import java.util.SortedMap;
import java.util.Iterator;
import java.util.Map;

import java.util.concurrent.ConcurrentSkipListMap;

/**	
	just to show at the end how many curses of what type you've got in your run! 
	RC stands for 'R'andom 'C'ount. this class provides both getting random shit
	and records them shit elements in a map structure that has Integer as the value

	this integer indicates how many times that specific element has happened during
	via provide operations

	this class is a wrapper for other DataSource classes, so it can be used to fwd data
	after having recorded it
*/
public class RCRecorder<T> implements DataSource<T>{

	private SortedMap<T,Integer> recs;
	private DataSource<T> prov;

	public RCRecorder(DataSource<T> prov){
		this.recs=new ConcurrentSkipListMap<T,Integer>();
		this.prov=prov;
	}
	
	/* update or create records and their integer(count) 'count'erparts (no pun intended!)*/
	public void feed(T t){
		if(!recs.containsKey(t)) recs.put(t,0);
		recs.put(t,recs.get(t)+1);
	}
	
	@Override
	public T get(){
		T t=prov.get();
		this.feed(t);
		return t;
	}

	@Override
	public void end(){
		prov.end();
		System.out.flush();
		System.out.println();
		System.out.println("-------------SUMMARY-----------");
		printRecords();
	}
	
	public void printRecords(){
		Iterator<Map.Entry<T, Integer>> iterator = recs.entrySet().iterator();
		int sum=0;
    while (iterator.hasNext()) {
				Map.Entry<T, Integer> entry = iterator.next();
        System.out.println("(x"+ entry.getValue()+")\t"+entry.getKey());
				sum+=entry.getValue().intValue();
    }
		System.out.println("you've got insulted "+sum+" times you, "+Curse.getRandCurse()+"!");
	}

}
