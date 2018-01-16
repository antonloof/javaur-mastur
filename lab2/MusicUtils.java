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

	public static double[] average(double[] t1, double[] t2)
	{
		double[] res = new double[t1.length];
		for (int i = 0; i < t1.length; i++)
		{
			res[i] = (t1[i] + t2[i]) / 2;
		}
		return res;
	}

	public static double[] harmonic(int pitch, double duration)
	{
		return average(average(note(pitch + 12, duration), note(pitch - 12, duration)), note(pitch, duration));
	}

}
