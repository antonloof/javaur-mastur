public class InverseHarmonicSum 
{
	public static void main(String[] args)
	{
		int target = Integer.parseInt(args[0]);
		double sum = 0;
		double lastSum = 0;
		for (int i = 1; i <= Integer.MAX_VALUE; i++) 
		{
			lastSum = sum;
			sum += 1d/i;
			if (sum > target && lastSum < target) {
				System.out.println("The number of terms is " + i);
				break;
			}
		}
	}
}