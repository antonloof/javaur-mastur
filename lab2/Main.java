import java.io.File;
import java.util.*;
import java.io.FileNotFoundException;

public class Main 
{	
	public static void main(String[] args) 
	{
		SoundDevice device = new SoundDevice();
		Song song = new Song(10);
		Scanner s = new Scanner(System.in).useLocale(Locale.US);
		while (true)
		{
			try 
			{
				int note = s.nextInt();
				double duration = s.nextDouble();
				song.add(MusicUtils.harmonic(note, duration));
			} catch (Exception e) 
			{
				break;
			}
		}
		song.save(device.getFormat(), new File("twotones.wav"));
	} 
}
