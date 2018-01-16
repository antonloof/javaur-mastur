class SharpFilter implements ImageFilter
{
	public String getMenuName()
	{
		return "Sharp filter";
	}
	public void apply(int[][][] src, int[][][] dest)
	{
		for (int i = 0; i < src.length; i++)
		{
			for (int j = 0; j < src[i].length; j++)
			{
				for (int k = 0; k < 3; k++)
				{
					if (i == 0 || i == src.length - 1 || j == 0 || j == src[i].length - 1)
					{
						dest[i][j][k] = src[i][j][k];
					}
					else
					{
						dest[i][j][k] = 5 * src[i][j][k] - src[i - 1][j][k] - src[i + 1][j][k] - src[i][j - 1][k] - src[i][j + 1][k];
						if (dest[i][j][k] > 255)
						{
							dest[i][j][k] = 255;
						}
						else if (dest[i][j][k] < 0)
						{
							dest[i][j][k] = 0;
						}
					}
				}
			}
		}
	}
}