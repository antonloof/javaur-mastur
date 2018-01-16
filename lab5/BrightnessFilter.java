public class BrightnessFilter implements ScalableFilter 
{
	public String getMenuName()
	{
		return "Brightness filter";
	}
	
	public void apply(int[][][] src, int[][][] dest, double scale)
	{
		for (int i = 0; i < src.length; i++)
		{
			for (int j = 0; j < src[i].length; j++)
			{
				for (int k = 0; k < 3; k++)
				{
					int newVal = src[i][j][k] + (int)(128 * scale);
					if (newVal < 0)
					{
						newVal = 0;
					}
					else if (newVal > 255)
					{
						newVal = 255;
					}
					dest[i][j][k] = newVal;
				}
			}
		}
	}
}