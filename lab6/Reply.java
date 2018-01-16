public class Reply 
{
	private int bulls, cows;

	public Reply(int bulls, int cows) 
	{
		this.bulls = bulls;
		this.cows = cows;
	}

	public int getBulls() {return bulls;}

	public int getCows() {return cows;}

	public boolean equals(Object other) 
	{
		boolean res = false;
		if (other instanceof Reply) 
		{		
			Reply r = (Reply)other;
			res = bulls == r.getBulls() && cows == r.getCows();
		}
		return res;
	}
	
	public String toString() 
	{
		return bulls + " " + cows;
	}

}
