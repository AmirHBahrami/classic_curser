public class Curse implements Comparable{

	public static final String[] HARDCORE_CURSES={
		"Schweinhund",
		"Zounderkite",
		"Rantallion",
		"Bescumber",
		"Fopdoodle",
		"Smellfungus",
		"Numpty",
		"Thingumbob",
		"Gadzooks",
		"Whiffle-Whuffle"
	};

	public static Curse getRandCurse(){
		int rInt=Utils.getRandInt(HARDCORE_CURSES.length);
		Curse c=new Curse(HARDCORE_CURSES[rInt],rInt);
		return c;
	}

	private String content;
	private int level; // just to have something as a warning level!

	public Curse(String content,int level){
		this.content=new StringBuffer(content).toString();
		this.level=level;
	}

	@Override
	public int compareTo(Object o){
		Curse c=(Curse) o;
		if (c.getLevel()<this.level)return-1;
		else if (c.getLevel()==this.level)return 0;
		return 1;
	}

	public boolean equals(Curse c){
		return compareTo(c)==0;
	}

	public String getContent(){
		return new StringBuffer(this.content).toString(); // avoiding reference issues
	}

	public int getLevel(){return this.level;}

	@Override
	public String toString(){
		return this.getColor()+this.content+Constants.ANSI_RESET;
	}

	private String getColor(){
		int oneThird=HARDCORE_CURSES.length/3;
		if(	level <  oneThird*1 ) return Constants.ANSI_GREEN;
		if(	level >= oneThird*2 ) return Constants.ANSI_RED;	
		else return Constants.ANSI_YELLOW;
	}
	

}
