package problemKukulkan;

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
	public static final String	defaultInputFile = "resource/defaultPyramid";

// -----------------------------------------------
// Local methods
// -----------------------------------------------
	public static void main(String[] args)
	{
		String fileName;

		if (args.length == 0)	fileName = defaultInputFile;
		else					fileName = args[0];

		int[][] t = parseInputFile(fileName);
		Pyramid pyramid = new Pyramid(t);
		pyramid.findTheSnake();
		System.out.println("Result = " + pyramid.getSol());
	}

// -----------------------------------------------
// Auxiliary methods
// -----------------------------------------------
	private static int[][] parseInputFile(String fileName)
	{
		int[][] res = null;
		try
		{
			Scanner sc = new Scanner(new FileReader(fileName));
			int n = sc.nextInt();
			res = new int[n][];
			for (int y=0; y<n; y++)
			{
				res[y] = new int[y+1];
				for (int x=0; x<=y; x++)
				{
					res[y][x] = sc.nextInt();
				}
			}

			sc.close();
		}
		catch(Exception e)
		{
//			e.printStackTrace();
//			System.exit(0);
// TODO: because your system does not accept additional input files
res = new int[4][4];
res [0][0] = 3;
res [1][0] = 7;	res [1][1] = 4;
res [2][0] = 2;	res [2][1] = 4;	res [2][2] = 6;
res [3][0] = 8;	res [3][1] = 5;	res [3][2] = 9;	res [3][3] = 3;
		}

		return res;
	}
}
