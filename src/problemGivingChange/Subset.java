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

		isInList(res.elementOut, newElem, true, true);
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

	public static void addUnicToList(LinkedList<Subset> subsetList, Subset toAdd)
	{
		for(Subset o: subsetList)
		{
			if (o.equals(toAdd))
			{
				return;
			}
		}
		subsetList.add(toAdd);
	}

	public String toString()
	{
		String res = this.elementIn.toString();

		return res;
	}

	@Override
	public boolean equals(Object obj)
	{
// TODO to improve
		Subset s = (Subset) obj;
		if (this.sum != s.sum)
		{
			return false;
		}
		if (this.elementOut.size() != s.elementOut.size())
		{
			return false;
		}
		for (int elem: this.elementOut)
		{
			if (!isInList(s.elementOut, elem, false, false))
			{
				return false;
			}
		}

		return true;
	}

// -----------------------------------------------
// Auxiliary methods
// -----------------------------------------------
	private static boolean isInList(LinkedList<Integer> list, int toCheck, boolean remove, boolean throwExceptionIfNot)
	{
		for(int i=0; i<list.size(); i++)
		{
			if (list.get(i) == toCheck)
			{
				if (remove)
				{
					list.remove(i);
				}
				return true;
			}
		}
		if (throwExceptionIfNot)
		{
			throw new RuntimeException();
		}
		return false;
	}
}