import java.util.concurrent.ThreadLocalRandom;

public class Utils{

	public static int getRandInt(int bound){
		return ThreadLocalRandom.current().nextInt(bound);
	}

}
