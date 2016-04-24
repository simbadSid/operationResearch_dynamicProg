package problemKukulkan;









public class Pyramid
{
// -----------------------------------------------
// Attributes
// -----------------------------------------------
	private int		n;			// Number of rows
	private int[][]	t;			// t[x][y]: the number of robbers at (x, y)
	private int[][]	f;			// f[x][y]: the value of maximum number of robbers
								//	eaten at (x, y) from top of the pyramid

// -----------------------------------------------
// Constructor
// -----------------------------------------------
	public Pyramid(int[][] t)
	{
		this.n = t.length;
		this.t = new int[n][];
		this.f = new int[n][];
		for (int y=0; y<n; y++)
		{
			this.t[y] = new int[y+1];
			this.f[y] = new int[y+1];
			for (int x=0; x<=y; x++)
			{
				this.t[y][x] = t[y][x];
			}
		}
    }

// -----------------------------------------------
// Local methods
// -----------------------------------------------
	/**
	 * Solve the maximum number of eaten robbers
	 */
	public void findTheSnake()
	{
		f[0][0] = t[0][0];
		for (int y=1; y<n; y++)
		{
			for (int x=0; x<=y; x++)
			{
				f[y][x] = t[y][x] + this.getPreviousMax(y, x);
			}
		}
	}

	/**
	 * @return the optimal value
	 */
	public int getSol()
	{
		int res = -1;

		for (int i:f[n-1])
		{
			if (i > res)
			{
				res = i;
			}
		}
		return res;
	}

// -----------------------------------------------
// Auxiliary methods
// -----------------------------------------------
	private Integer getPreviousMax(int y, int x)
	{
		if ((x < 0) || (y <= 0) || (y >= n) || (x > y))
		{
			throw new RuntimeException("Parameters out of the pyramid: n = " + n + ", x = " + x + ", y = " + y);
		}

		Integer left = null, right = null;

		if (x > 0)	left	= this.f[y-1][x-1];
		if (x < y)	right	= this.f[y-1][x];
System.out.println("Max[x = " + x + "][y = " + y + "] = Max(" + left+ ", " + right + ")");

		if (left	== null)	return right;
		if (right	== null)	return left;
		else					return Math.max(left, right);
	}
}