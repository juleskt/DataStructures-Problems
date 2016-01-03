import java.io.*;

public class FourA 
{
	public static void main(String[] args) 
	{
		try 
		{	//Takes first arg, assumes it is a text file
			System.out.println(binarySum(args[0]));
		}
		catch (Exception E)
		{
			System.out.println("Specify the file name");
		}
	}

	public static int binarySum(String fileName)
	{
		//Initialize the sum
		int ans = 0;
		
		try
		{
			//Initialize streams
			FileInputStream fstream = new FileInputStream(fileName);
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			
			//Read in lines from the file	
			while ((strLine = br.readLine()) != null)   
			{
				//Sum the binary results in decimal
				ans += Integer.parseInt(strLine,2);
			}
			//Close the stream
			in.close();
			  
		}
		catch (Exception e)
		{
			System.out.println("Handled...?");			
		}
	    return ans;
	}
}
