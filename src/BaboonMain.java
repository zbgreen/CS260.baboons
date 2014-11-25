import java.util.Random;

/**
 * Class that contains the main method for Baboon and Canyon and creates threads to execute
 * the program.
 * @author Zachary Green 
 * @version 11/17/14
 */
public class BaboonMain 
{
	private final static int DELAY_MAX = 8000;
	private final static int OFFSET = 1000;
	private final static int BABOON_NUM = 30;
	
	private static Thread[] threads;
	private static Random random;
	private static Canyon canyon;
	
	/**
	 * Main method that creates baboon threads.
	 * @param args
	 */
	public static void main(String[] args) 
	{
		threads = new Thread[BABOON_NUM];
		random = new Random();
		canyon = new Canyon();
		
		for(int i = 0; i < BABOON_NUM; i++)
		{
			threads[i] = new Thread(new Baboon(i, canyon));
			int number = random.nextInt(DELAY_MAX);
			try 
			{//1-8 second delay to arrive
				Thread.sleep(number + OFFSET);
			} 
			catch (InterruptedException e){
				e.printStackTrace();
			}
			threads[i].start();
		}
	}
}