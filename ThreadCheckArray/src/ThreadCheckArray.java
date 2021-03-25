import java.util.ArrayList;
//
/**
 * in this class the threads looking for the numbers that build the required sum 
 * @author Shahar
 *
 */
public class ThreadCheckArray implements Runnable 
{
	/**ss
	 * flag for the status of the threads(has found the numbers or not )
	 */
	private boolean flag;
	/**
	 * boolean array in SharedData
	 */
	private boolean [] winArray;
	/**
	 * holds the shared data(arrays,b,flag)
	 */
	SharedData sd;
	/**
	 * array of numbers in SharedData 
	 */
	private ArrayList<Integer> array;
	/**
	 * the number that we need to reach
	 */
	int b;
	//
	/**s
	 * Constructor of ThreadCheckArray
	 * @param sd   holds the shared data
	 */
	public ThreadCheckArray(SharedData sd) 
	{
		this.sd = sd;	
		synchronized (sd) 
		{
			array = sd.getArray();
			b = sd.getB();
		}		
		winArray = new boolean[array.size()];
	}
	
	/**
	 * recursive function that checks if the answer was found and continue if not
	 * @param n  represent array size -1
	 * @param b  represent the sum that we need to reach
	 */
	void rec(int n, int b)
	{
		synchronized (sd) 
		{
			if (sd.getFlag())
				return;
		}	
		if (n == 1)
		{
			if(b == 0 || b == array.get(n-1))
			{
				flag = true;
				synchronized (sd) 
				{
					sd.setFlag(true);
				}			
			}
			if (b == array.get(n-1))
				winArray[n-1] = true;
			return;
		}
		
		rec(n-1, b - array.get(n-1));
		if (flag)
			winArray[n-1] = true;
		synchronized (sd) 
		{
			if (sd.getFlag())
				return;
		}	
		rec(n-1, b);
	}

	/**
	 * every thread looking for the numbers in the array that sums up the number "b"
	 */
	public void run() {
		if (array.size() != 1)
			if (Thread.currentThread().getName().equals("thread1"))
				rec(array.size()-1, b - array.get(array.size() - 1));
			else 
				rec(array.size()-1, b);
		if (array.size() == 1)
			if (b == array.get(0) && !flag)
			{
				winArray[0] = true;
				flag = true;
				synchronized (sd) 
				{
					sd.setFlag(true);
				}
			}
		if (flag)
		{
			if (Thread.currentThread().getName().equals("thread1"))
				winArray[array.size() - 1] = true;
			synchronized (sd) 
			{
				sd.setWinArray(winArray);
			}	
		}
	}
}
