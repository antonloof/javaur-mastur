public class MusicUtils
{
	public static double[] sine(double freq, double duration) 
	{
		int n = (int)(duration * SoundDevice.SAMPLING_RATE);
		double[] a = new double[n];
		double dx = 2 * Math.PI * freq / SoundDevice.SAMPLING_RATE;
		for (int i = 0; i < n; i++) 
		{
			a[i] = Math.sin(i * dx);
		}
		return a;
	}
	
	public static double[] pluck(double freq, double duration)
	{
		int n = (int) (duration * SoundDevice.SAMPLING_RATE);
		double[] data = new double[n];
		int p = (int)(SoundDevice.SAMPLING_RATE / freq);
		for (int i = 0; i < p && i < n; i++)
		{
			data[i] = -1 + Math.random() * 2;
		}
		
		for (int i = p; i < n; i++)
		{
			data[i] = 0.498 * (data[i - p] + data[i - (p - 1)]);
		}
		return data;
	}
	
	public static double[] note(int pitch, double duration)
	{
		return pluck(440 * Math.pow(2, pitch / 12), duration);
	}
}