package org.Nazanin.Dictionary;

import java.util.Scanner;

public class MainClass {
	public static void main(String[] args)
	{
		Dictionary dic = new Dictionary();
		Scanner sc = new Scanner(System.in);
		String answer;
		dic.readFromDictionary();
		System.out.println("Enter a word: ");
	    dic. userWord = sc.next();
		System.out.println("Did you mean ' " + dic.find(dic.getOffset(dic.userWord, dic.dictionary)) + " ' ? Y/N");
		dic.showSuggestions();
		answer = sc.next();
		while(true)
		{
			
		if(answer.equalsIgnoreCase("Y"))
		{
			sc.close();
			System.exit(0);
		}
		else if(answer.equalsIgnoreCase("N"))
		{
			
			for(String s : dic.suggestions)
			{
			int count=0;
			  System.out.println("Did you mean '"+ s +" ' ? Y/N");
		      answer = sc.next();
		      if(answer.equalsIgnoreCase("N"))
		      {
		    	  count ++;
		    	  if(count == dic.suggestions.size() - 1)
			      {
			    	  System.out.println("Tell me exactly what do you mean?! :D");
			    	  break;
			      }
		      }
		      else if(answer.equalsIgnoreCase("Y"))
		      {
		    	  sc.close();
				  return;
		      }
		      else
		      {
		    	  System.out.println("Invalid Input! Try again.");
					break;
		      }
		      
		    }
		
		}
		else
		{
			System.out.println("Invalid Input! Try again.");
			break;
		}
		
		}
	}

}
