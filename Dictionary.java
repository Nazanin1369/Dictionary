package dictionary;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Dictionary 
{

	public static void main(String args)
	{
		Dictionary dic = new Dictionary();
		dic.readFromDictionary();
		
	}
	
	public void readFromDictionary()
	{
		String path = "C:/Users/Nazanin/en-US/en-US.txt";
		try{
			FileReader fr = new FileReader(path);
			BufferedReader in = new BufferedReader(fr);
			String word;
			while(( word = in.readLine()) != null )
			{
				System.out.println(word);
			}
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
		
	}
}
