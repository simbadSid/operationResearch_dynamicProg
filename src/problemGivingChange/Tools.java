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
			Subset.addUnicToList(subsetList, subset);
		}

		while(!subsetList.isEmpty())
		{
			tmpSubSetList = new LinkedList<Subset>();
			for (Subset subset: subsetList)							// For each subset of size n
			{
				for (int elemOutOfSubset: subset.getElemOut())		//		For each element out of the subset
				{
					Subset newSubset = Subset.create(subset, elemOutOfSubset);
					if (newSubset.getSum() > E)						//		Remove the coins that are bigger than E
					{
						continue;
					}
					if (newSubset.getSum() == E)
					{
						System.out.println("Solution found: " + newSubset);
						return true;
					}
					Subset.addUnicToList(tmpSubSetList, newSubset);	//		Create subset of size n+1
				}
			}
			subsetList		= tmpSubSetList;
			tmpSubSetList	= null;
		}
		return false;
	}


// -----------------------------------------------
// Auxiliary class
// -----------------------------------------------


}