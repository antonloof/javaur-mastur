public class HarmonicSum 
{

	public static double hSum(int n) 
	{
		double sum = 0;
		for (int i=1; i<=n; i++) {
			sum = sum + 1.0/i;
		}
		return sum;
	}

	public static void main(String[] args) 
	{
		int n = Integer.parseInt(args[0]);
		System.out.println("number of      Harmonic\nterms          sum");
		for (int i = 1; i <= n; i++)
		{
			double sum = hSum(i);
			int iLen = (int)(Math.log10(i) + 1);
			System.out.println(i + String.format("%" + (15 - iLen) + "s", "") + hSum(i));
		}
	}
}