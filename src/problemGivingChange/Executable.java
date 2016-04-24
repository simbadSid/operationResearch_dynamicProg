package problemGivingChange;

import java.io.FileReader;
import java.util.Scanner;









/**
 * A class to perform your own tests
 */
public class Executable
{
// -----------------------------------------------
// Attributes
// -----------------------------------------------
	public static final String	defaultInputFile = "resource/defaultProblemInstance";

// -----------------------------------------------
// Local methods
// -----------------------------------------------
	public static void main(String[] args)
	{
		String fileName;

		if (args.length == 0)	fileName = defaultInputFile;
		else					fileName = args[0];

		ProblemInstance pb  = parseInputFile(fileName);
		System.out.println("Problem = " + pb);
		boolean test = Tools.subsetSum(pb.change, pb.objective);
		if (!test)
		{
			System.out.println("\t no solution");
		}
	}

// -----------------------------------------------
// Auxiliary methods
// -----------------------------------------------
	private static ProblemInstance parseInputFile(String fileName)
	{
		ProblemInstance res;

		try
		{
			Scanner sc				= new Scanner(new FileReader(fileName));
			int		objective		= sc.nextInt();
			int		nbrChangeCoin	= sc.nextInt();
			res						= new ProblemInstance(objective, nbrChangeCoin);
			for (int c=0; c<nbrChangeCoin; c++)
			{
				res.change[c] = sc.nextInt();
			}

			sc.close();
		}
		catch(Exception e)
		{
//				e.printStackTrace();
//				System.exit(0);
// TODO: because your system does not accept additional input files
res = new ProblemInstance(20, 6);
res.change[0] = 8;
res.change[1] = 12;
res.change[2] = 5;
res.change[3] = 3;
res.change[4] = 6;
res.change[5] = 9;
		}

		return res;
	}

	private static class ProblemInstance
	{
		public int		objective;
		public int[]	change;

		public ProblemInstance(int objective, int nbrChangeCoin)
		{
			this.objective	= objective;
			this.change		= new int[nbrChangeCoin];
		}

		public String toString()
		{
			String res = "";

			res += "\n\t- Objective = " + this.objective;
			res += "\n\t- Coin list = ";
			for (int coin: this.change)
			{
				res += coin + ", ";
			}

			return res;
		}
	}
}