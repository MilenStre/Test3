import java.util.ArrayList;
//
/**
 * saves the shared data between threads 
 * @author Shahar 
 *
 */
public class SharedData 
{
/**
 * array of numbers in SharedData7 
 */
	private ArrayList<Integer> array;
	/**
	 * boolean array in SharedData
	 */
	private boolean [] winArray;
	/**
	 * flag for the status of the threads (has found the numbers or not )
	 */
	private boolean flag;
	/**
	 * the number that we need to reach
	 */
	private final int b;
	
	/**
	 * Constructor for SharedData
	 * @param array   saves the array with numbers
	 * @param b   saves the number that we want to reach
	 */
	
	public SharedData(ArrayList<Integer> array, int b) {
		
		this.array = array;
		this.b = b;
	}

	/**s
	 * 
	 * @return returns a boolean array with the indexes of the numbers that have been chosen for the final answer
	 */
	public boolean[] getWinArray() 
	{
		return winArray;
	}
	//

	/**
	 * 
	 * @param winArray saves the indexes of the chosen numbers in a boolean array
	 */
	public void setWinArray(boolean [] winArray) 
	{
		this.winArray = winArray;
	}

	/**
	 * 
	 * @return returns the array with the numbers
	 */
	public ArrayList<Integer> getArray() 
	{
		return array;
	}

	/**
	 * 
	 * @return returns the number "b" that we want to reach
	 */
	public int getB() 
	{
		return b;
	}

	/**
	 * 
	 * @return returns the flag of sharedsData that indicates the status of a thread(finish or not)
	 */
	public boolean getFlag() 
	{
		return flag;
	}

	/**
	 * 
	 * @param flag indicates if we found the answer and finished
	 */
	public void setFlag(boolean flag) {
		this.flag = flag;
	}

}
