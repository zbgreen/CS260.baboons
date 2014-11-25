/**
 * This class serves as the canyon that the baboons cross.
 * @author Zachary Green
 * @version 11/17/14
 */
public class Canyon 
{
	private volatile int baboonCrossingEast;
	private volatile int baboonCrossingWest;
	private volatile boolean isGettingOnEast;
	private volatile boolean isGettingOnWest;
	private volatile boolean waitEast;
	private volatile boolean waitWest;
	
	/**
	 * This constructor initializes all variables to 0 or false.
	 */
	public Canyon()
	{
		baboonCrossingEast = 0;
		baboonCrossingWest = 0;
		isGettingOnEast = false;
		isGettingOnWest = false;
		waitEast = false;
		waitWest = false;
	}
	
	/**
	 * Checks if a baboon is getting on the same side
	 * @param side  false = west, true = east 
	 * @return  false = not getting on, true = is getting on 
	 */
	public synchronized boolean checkGettingOn(boolean side)
	{	
		if (side == false)
			return isGettingOnWest;
		else
			return isGettingOnEast;
	}
	
	/**
	 * Sets the isGettingOnWest/East to true
	 * @param side  false = west, true = east 
	 */
	public synchronized void setGettingOn(boolean side)
	{
		if (side == false)
			isGettingOnWest = true;
		else
			isGettingOnEast = true;
	}
	
	/**
	 * Sets the isGettingOnWest/East to false
	 * @param side  false = west, true = east 
	 */
	public synchronized void doneGettingOn(boolean side)
	{
		if (side == false)
			isGettingOnWest = false;
		else
			isGettingOnEast = false;
	}
	
	/**
	 * Checks the rope if a baboon from the other side is crossing
	 * @param side  false = west, true = east 
	 * @return  false = not crossing, true = is crossing
	 */
	public synchronized int checkRope(boolean side)
	{
		if (side == false)
		{
			return baboonCrossingWest;
		}
		else
		{
			return baboonCrossingEast;
		}
	}
	
	/**
	 * Sets baboonCrossingEast/West to true
	 * @param side  false = west, true = east 
	 */
	public synchronized void setCrossing(boolean side)
	{
		if (side == false)
			baboonCrossingEast++;
		else
			baboonCrossingWest++;
	}
	
	/**
	 * Sets baboonCrossingEast/West to false
	 * @param side  false = west, true = east 
	 */
	public synchronized void doneCrossing(boolean side)
	{
		if (side == false)
			baboonCrossingEast--;
		else
			baboonCrossingWest--;
	}
	
	/**
	 * Gets the amount of baboons crossing
	 * @param side  false = west, true = east 
	 * @return  number of baboons crossing
	 */
	public synchronized int getCrossingNum(boolean side)
	{
		if (side == false)
			return baboonCrossingWest;
		else
			return baboonCrossingEast;
	}

	/**
	 * Checks to see if a baboon is waiting
	 * @param side  false = west, true = east 
	 * @return  false = not waiting, true = is waiting  
	*/
	public synchronized boolean checkWait(boolean side)
	{
		if (side = false)
			return waitWest;
		else
			return waitEast;
	}
	
	/**
	 * Sets waitEast/West to true
	 * @param side  false = west, true = east 
	 */
	public synchronized void setWait(boolean side) 
	{
		if (side = false)
			waitEast = true;
		else
			waitWest = true;
	}
	
	/**
	 * Sets waitEast/West to false
	 * @param side  false = west, true = east 
	 */
	public synchronized void doneWait(boolean side)
	{
		if (side = false)
			waitEast = false;
		else
			waitWest = false;
	}
}