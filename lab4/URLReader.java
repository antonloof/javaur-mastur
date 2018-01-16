import java.net.URL;
import java.io.InputStreamReader;
import java.util.Scanner;

public class URLReader
{

	public static final String nordpoolURL = 
		"http://wwwdynamic.nordpoolspot.com/marketinfo/consumption/sweden/consumption.cgi?interval=last8";

	public static void getEightDays(int[][] data) throws Exception
	{
		URL url = new URL(nordpoolURL);
		Scanner in = new Scanner(new InputStreamReader(url.openStream()));
		while (in.findInLine("00-01") == null) in.nextLine();
		in.nextLine(); // skip the line where the pattern was found
		
		for (int i = 0; i < 24; i++) 
		{
			for (int j = 0; j < 8; j++)
			{
				String s = in.findInLine("[0-9]+|-");
				if (!s.equals("-"))
				{
					data[i][j] = Integer.parseInt(s);
				}
				else
				{
					data[i][j] = 0;
				}
				in.nextLine();
			}
			in.nextLine();
			in.nextLine();
			in.nextLine();
		}
	}
		
	public static void main(String[] args) throws Exception 
	{
		int[][] data = new int[24][8];
		getEightDays(data);
		for (int i = 0; i < 24; i++) 
		{
			for (int j = 0; j < 8; j++)
			{
				System.out.print(data[i][j] + " ");
			}
			System.out.println();
		}
	}
}
