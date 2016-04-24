package problemGivingChange;

import java.util.LinkedList;






public class Subset
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
			if (elem == i)
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
}
