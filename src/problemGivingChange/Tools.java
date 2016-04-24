package problemGivingChange;

import java.util.LinkedList;









/**
 * A class providing static methods for frequent algorithmic problems.
 */
public class Tools
{

/**
 * Given a set of integers X and an integer E, is there a non-empty subset that sums to E ?
 * With E = 20 et X = {8,12,5,3,6,9}, the sets : {12,8}, {12,5,3}, {8, 3, 9} are examples of solutions.
 * @param X: a set of integer values (coins)
 * @param E: a value
 * @return true if there exists a subset of X that sums to E, false otherwise
 */
	public static boolean subsetSum(int[] X, int E)
	{
		if (E <  0)	throw new RuntimeException();
		if (E == 0) return true;

		LinkedList<Subset> subsetList = new LinkedList<Subset>(), tmpSubSetList;

		for (int elem: X)										// Create the set of subset of size 1
		{
			if (elem > E)										//		Remove the coins that are bigger than E
			{
				continue;
			}
			if (elem == E)
			{
				System.out.println("Solution found: " + elem);
				return true;
			}
			Subset subset = Subset.create(elem, X, E);
			addUnicToList(subsetList, subset);
		}

System.out.println("Initial: " + subsetList);
		while(!subsetList.isEmpty())
		{
			tmpSubSetList = new LinkedList<Subset>();
			for (Subset subset: subsetList)						// For each subset of size n
			{
				for (int elemOutOfSubset: subset.getElemOut())	//		For each element out of the subset
				{
					Subset newSubset = Subset.create(subset, elemOutOfSubset);
					if (newSubset.getSum() > E)					//		Remove the coins that are bigger than E
					{
System.out.println("Out of order: " + newSubset);
						continue;
					}
					if (newSubset.getSum() == E)
					{
						System.out.println("Solution found: " + newSubset);
						return true;
					}
					tmpSubSetList.add(newSubset);				//		Create subset of size n+1
				}
			}
			subsetList		= tmpSubSetList;
			tmpSubSetList	= null;
		}
		return false;
	}

// -----------------------------------------------
// Auxiliary methods
// -----------------------------------------------
	private static void removeFromList(LinkedList<Integer> list, int toRemove)
	{
		for(int i=0; i<list.size(); i++)
		{
			if (list.get(i) == toRemove)
			{
				list.remove(i);
				return;
			}
		}
		throw new RuntimeException();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static void addUnicToList(LinkedList subsetList, Object toAdd)
	{
		for(Object o: subsetList)
		{
			if (o.equals(toAdd))
			{
				return;
			}
		}
		subsetList.add(toAdd);
	}

// -----------------------------------------------
// Auxiliary class
// -----------------------------------------------
public static class Subset
{
// -----------------------------------------------
// Attributes
// -----------------------------------------------
	private LinkedList<Integer> elementIn;
	private LinkedList<Integer> elementOut;
	private int					maxValue;
	private Integer				sum;

// -----------------------------------------------
// Private Constructor
// -----------------------------------------------
	private Subset(LinkedList<Integer> elementIn, LinkedList<Integer> elementOut, int maxValue)
	{
		if (elementIn != null)
		{
			this.elementIn	= new LinkedList<Integer>(elementIn);
		}
		else
		{
			this.elementIn	= new LinkedList<Integer>();
		}
		if (elementOut != null)
		{
			this.elementOut	= new LinkedList<Integer>(elementOut);
		}
		else
		{
			this.elementOut	= new LinkedList<Integer>();
		}
		this.maxValue = maxValue;
	}

	private Subset(Subset toClone)
	{
		this(toClone.elementIn, toClone.elementOut, toClone.maxValue);
		this.sum = (toClone.sum == null)? null : new Integer(toClone.sum);
	}

// -----------------------------------------------
// Public Constructor
// -----------------------------------------------
	public static Subset create(int elem, int[] totalElem, int maxValue)
	{
		Subset res = new Subset(null, null, maxValue);
		boolean found = false;

		for (Integer i: totalElem)
		{
			if ((elem == i) && (!found))
			{
				found = true;
				res.elementIn.add(elem);
				res.sum = elem;
			}
			else
			{
				res.elementOut.add(i);
			}
		}

		if (!found)
		{
			throw new RuntimeException();
		}

		return res;
	}

	public static Subset create(Subset subset, int newElem)
	{
		Subset res = new Subset(subset);

		removeFromList(res.elementOut, newElem);
		res.elementIn.add(newElem);
		res.sum += newElem;
		return res;
	}

// -----------------------------------------------
// Local methods
// -----------------------------------------------
	public int getSum()
	{
		return this.sum;
	}

	public LinkedList<Integer> getElemOut()
	{
		return this.elementOut;
	}

	public String toString()
	{
		String res = this.elementIn.toString();
		return res;
	}
}

}