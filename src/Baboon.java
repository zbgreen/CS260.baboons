import java.util.Random;

public class Baboon implements Runnable
{
	private final static int MAX = 1;
	private final static int GET_ON_TIME = 1000;
	private final static int CROSS_TIME = 4000;
	
	private volatile boolean side;//false = west , true = east
	private volatile boolean waiting;
	private volatile int index;//index of the array of Threads from main
	
	private Random random;
	private volatile Canyon canyon;
	
	/**
	 * Constructs baboon objects. Gets the side, index and canyon for the baboon.
	 * @param index  index of the array of Threads from main
	 * @param canyon  canyon object from main
	 */
	public Baboon(int index, Canyon canyon)
	{
		side = getSide();
		waiting = false;
		this.index = index;
		this.canyon = canyon;
	}
	
	//private methods
	//Uses random number to generate east or west
	private synchronized boolean getSide() 
	{
		random = new Random();
		int number = random.nextInt(MAX + 1);
		if (number == 0) side = false;
		else side = true;
		
		return side;
	}
	
	//prints message when baboon arrives
	private synchronized void arrive()
	{
		if (this.side == false)
			System.out.println("Baboon # " + this.index + " has arrived on the west side.");
		else 
			System.out.println("Baboon # " + this.index + " has arrived on the east side.");
	}
	
	/*
	 * Checks the rope for crossing baboons from the other direction and if there is
	 * currently a wait. If there is either of these the baboon sleeps for 1/10th of 
	 * a second and tries again.
	 */
	private synchronized void check() 
	{
		final int SLEEP = 100;
		boolean firstWait = true;
		while (canyon.checkRope(this.side) > 0 || canyon.checkWait(this.side))
		{
			try 
			{
				canyon.setWait(this.side);
				this.waiting = true;
				if (firstWait == true)
					System.out.println("Baboon # " + this.index + " is waiting.");
				firstWait = false;
				Thread.sleep(SLEEP);
			} 
			catch (InterruptedException e){
				e.printStackTrace();
			}
		}
		firstWait = true;
		if (this.waiting == true)
		{
			canyon.doneWait(this.side);
			this.waiting = false;
		}
		canyon.setGettingOn(this.side);
		canyon.setCrossing(this.side);
	}
	
	//Prints that the baboon is getting on and calls needed canyon methods.
	private synchronized void getOn() 
	{
		try 
		{
			if (this.side == false)
				System.out.println("Baboon # " + this.index + " is getting on the west side");
			else 
				System.out.println("Baboon # " + this.index + " is getting on the east side");
			//1 second waiting period to get on rope		
			Thread.sleep(GET_ON_TIME);
			canyon.doneGettingOn(this.side);
		} 
		catch (InterruptedException e){
			e.printStackTrace();
		}
	}
	
	//Prints that the baboon is crossing and calls needed canyon methods.
	private synchronized void crossing() 
	{
		try 
		{
			if (this.side == false)
				System.out.println("Baboon # " + this.index + " crossing east");
			else
				System.out.println("Baboon # " + this.index + " crossing west");
			Thread.sleep(CROSS_TIME);
			
			canyon.doneCrossing(this.side);
			if (this.side == false)
				System.out.println("Baboon # " + this.index + " is done");
			else 
				System.out.println("Baboon # " + this.index + " is done");
		} 
		catch (InterruptedException e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Run method from Runnable interface.
	 */
	@Override
	public synchronized void run() 
	{
		arrive();
		check();
		getOn();
		crossing();
	}
}