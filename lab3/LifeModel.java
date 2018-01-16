public class LifeModel  {
	private int width, height, gen;
	private boolean[][] world;

	public LifeModel (int w,int h) {
		width = w;
		height = h;
		world = new boolean[w][h];
	}

	public void randomPopulation(double fill) {
		for (int i = 0; i < width; i++)
		{
			for (int j = 0; j < height; j++)
			{
				world[i][j] = Math.random() < fill;
			}
		}
		System.out.println("LifeModel: Call to randomPopulation(" + fill + ")");
	}
	
	public void newGeneration() {
		gen++;
		boolean[][] newWorld = new boolean[width][height];
		for (int i = 0; i < width; i++)
		{
			for (int j = 0; j < height; j++)
			{
				int n = nrOfNeighbours(i, j);
				if (n == 2 && world[i][j])
				{
					newWorld[i][j] = true;
				}
				else if (n == 3) 
				{
					newWorld[i][j] = true;
				}
				else
				{
					newWorld[i][j] = false;
				}
				 
			}	
		} 
		System.out.println("LifeModel: Call #" + gen + " to newGeneration()");
		world = newWorld;
	}

	public void setCell(int i, int j, boolean b) {
		world[i][j] = b;
	}

	public boolean getCell(int i, int j) {
		if (0 <= i && i < 50 && 0 <= j && j < 50)		
			return world[i][j];
		return false;
	}

	public int getWidth() {
		System.out.println("LifeModel: Call to getWidth()");
		return width;
	}

	public int getHeight() {
		System.out.println("LifeModel: Call to getHeight()");
		return height;
	}
	
	public int getGen() {
		System.out.println("LifeModel: Call to getGen()");
		return gen;
	}

	private int nrOfNeighbours(int i, int j)
	{
		int sum = 0; 
		for (int ii = -1; i < 2; i++)
		{
			for (jj = -1; jj < 2; jj++)
			{
				if (!(i == 0 && j == 0)) 
				{
					sum += getCell(i, j) ? 1 : 0;
				}	
			}
		}
		return sum;
	}	
}
