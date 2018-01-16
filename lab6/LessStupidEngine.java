import java.util.*;

public class LessStupidEngine implements GuessEngine
{ 
	private List<Code> codes;
	private Code newGuess;
	private Code[] oldGuesses;
	private Reply[] oldReplies; 
	int guessCount;

	public LessStupidEngine() {
		oldGuesses = new Code[10];
		oldReplies = new Reply[10];
	}
			
	public void init() 
	{ 
		// testing
		/*
		Code secret = new Code(Color.BLUE, Color.BLUE,Color.YELLOW,Color.YELLOW);
		Code s1 = new Code(Color.BLUE, Color.WHITE, Color.BLUE, Color.WHITE);
		
		Code g0 = new Code(Color.BLUE, Color.YELLOW, Color.WHITE, Color.RED);
		Code g1 = new Code(Color.BLUE, Color.BLUE, Color.BLUE, Color.BLUE);
		Code g2 = new Code(Color.YELLOW, Color.YELLOW, Color.BLUE, Color.BLUE);
		Code g3 = new Code(Color.YELLOW, Color.BLUE, Color.YELLOW, Color.BLUE);
		Code g4 = new Code(Color.WHITE, Color.WHITE, Color.BLUE, Color.RED);
		
		System.out.println(simulateAnswer(s1, g4));
		System.out.println(simulateAnswer(secret, g0));
		System.out.println(simulateAnswer(secret, g1));
		System.out.println(simulateAnswer(secret, g2));
		System.out.println(simulateAnswer(secret, g3));
		System.out.println(simulateAnswer(secret, secret));
		*/
		guessCount = 0;
		codes = new LinkedList<Code>();
		Color[] cols = new Color[4];
		for (Color c0 : Color.values())
		{
			for (Color c1 : Color.values())
			{
				for (Color c2 : Color.values())
				{
					for (Color c3 : Color.values()) 
					{
						cols[0] = c0;
						cols[1] = c1;
						cols[2] = c2;
						cols[3] = c3;
						codes.add(new Code(cols));
					}
				}
			}
		}
		int index = (int)(Math.random() * codes.size());
		newGuess = codes.get(index);
	}

	public void answerNewGuess(Reply reply) throws ContradictionException 
	{
		oldGuesses[guessCount] = newGuess;
		oldReplies[guessCount] = reply;
		Iterator<Code> i = codes.iterator();
		while (i.hasNext())
		{
			Code code = i.next();
			if (!simulateAnswer(code, newGuess).equals(reply))
			{
				i.remove();
			}
		}
		guessCount++;
		if (codes.size() == 0)
		{
			throw new ContradictionException("Du e kass");
		}
		int index = (int)(Math.random() * codes.size());
		newGuess = codes.get(index);
	}
	
	private Reply simulateAnswer(Code secret, Code guess)
	{
		int cows = 0, bulls = 0;
		boolean[] usedColors = {false, false, false, false};
		
		for (int i = 0; i < 4; i++)
		{
			if (secret.getColor(i) == guess.getColor(i))
			{
				bulls++;
				usedColors[i] = true;
			}
		}
		
		for (int i = 0; i < 4; i++)
		{
			for (int j = 0; j < 4; j++)
			{
				if (secret.getColor(i) == guess.getColor(j) && !usedColors[j])
				{
					cows++;
					usedColors[j] = true;
				}
			}
		}
		
		return new Reply(bulls, cows);
	}
	
	public Code getNewGuess() {return newGuess;}

	public String explainContradiction(Code secret) 
	{
		for (int i = 0; i < guessCount; i++)
		{
			Reply correctReply = simulateAnswer(secret, oldGuesses[i]);
			if (!correctReply.equals(oldReplies[i]))
			{
				return "When I guessed " + oldGuesses[i] + ", you answered " + oldReplies[i] + ", the correct answer should have been " + correctReply + ".";
			}
		}
		return "Sorry, I cannot explain."; 
	}

	public boolean moreGuessesAllowed() {return guessCount < oldGuesses.length;}

	public int oldGuesses() {return guessCount;}

	public Code getOldGuess(int i) {return oldGuesses[i];}

	public Reply getOldReply(int i) {return oldReplies[i];}
}