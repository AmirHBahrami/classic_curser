import java.util.Scanner;
import java.util.NoSuchElementException;

import java.io.IOException;

public class Main{
	
	public static void main(String[] args){

		final PrintSubscriber ps=new PrintSubscriber(); // main operator
		final CursePublisher cp=new CursePublisher(); // initializer

		Thread t=new Thread(
			new Runnable(){
				@Override public void run(){
					cp.subscribe(ps);
				}
			}
		);
		
		t.start();

		// continue reading user input in main
		Scanner sc=new Scanner(System.in);
		String shit=null;
		System.out.println("Hello, "+Curse.getRandCurse()+"! hit CTRL+D to end this shitshow");
		try{
			while((shit=sc.next())!=null);
		}catch(NoSuchElementException e){ 	
			ps.stop();
			sc.close();
		}

	}

}
