//Java refresher from http://stackoverflow.com/questions/10048899/string-to-char-array-java
import java.util.List;
import java.util.ArrayList;

public class FourB
{
	public static void main(String[] args)
	{
		try
		{
			//Get the size
			int size = Integer.parseInt(args[args.length-1]);
		
			//Build the string from argv
			StringBuilder insertArgs = new StringBuilder();
			for(int x = 0; x < args.length - 1; x++)
			{
				//Add the spaces back into the phrase
				if(x != args.length - 2)
				{
					insertArgs.append(args[x]+" ");
				}
				//Don't add the spaces if it is the last word in the phrase
				else
				{
					insertArgs.append(args[x]);
				}
			}
			
			//Set the built string to a String
			String builtString = insertArgs.toString();
			
			if(insertArgs.length() <= size)
			{
				System.out.println(builtString);
				return;
			} 
			
			//Output the result of the function
			System.out.println(Analyze(builtString,size));
		}
		catch (Exception e)
		{
			System.out.println("Missing parameters!");
		}
	}
	
	public static String Analyze(String str, int size)
	{	
		String result = "";
	
		//Initialize list of subCount objects and set the first element to the first substring
		List<subCount> countList = new ArrayList<subCount>();
		countList.add(new subCount(str.substring(0,size),0));
		
		//Loop through the string
		for(int x = 1; x <= str.length() - size; x++)
		{
			//Grab the substring
			String toCompare = str.substring(x,x+size);
			boolean matched = false;
			//Loop through countList
			for(int y = 0; y < countList.size(); y++)
			{
				//Break if there is a match
				if(countList.get(y).equivCheck(toCompare))
				{
					matched = true;
					break;
				}
			}
			//Make a new element if a new substring is found
			if(!matched)
				countList.add(new subCount(toCompare,x));
		}
		
		//Set flagging params
		int max = 0;
		int earliest = Integer.MAX_VALUE;
		
		subCount current = null;
		for(int i = 0; i < countList.size(); i++)
		{
			current = countList.get(i);
			if(current.recurrence >= max)
			{
				max = current.recurrence;
			}
		}
		
		for(int j = 0; j < countList.size(); j++)
		{
			current = countList.get(j);
			if(current.recurrence == max)
			{
				if(current.firstEncounter <= earliest)
				{
					earliest = current.firstEncounter;
					result = current.toString();
				}
			}
		}
		
		return result;
	}
	
	public static class subCount
	{
		//Data fields
		int recurrence;
		int firstEncounter;
		String subString = "";
		
		//Constructor
		public subCount(String sub, int position)
		{
			this.subString = sub;
			this.firstEncounter = position;
			this.recurrence = 1;	
		}
		
		//Comparing strings and counting recurrence
		public boolean equivCheck(String sub)
		{
			if(sub.equals(this.subString))
			{
				this.recurrence++;
				return true;
			}
			else
			{
				return false;
			}
		}
		//DEBUG!
		public void print()
		{
			System.out.println("Substring: " + this.subString + " Recurrence: " + this.recurrence + " Position: " + this.firstEncounter);
		}
		public String toString()
		{
			return subString;
		}
	}
}
