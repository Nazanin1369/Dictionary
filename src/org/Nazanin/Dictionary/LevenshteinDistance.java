package org.Nazanin.Dictionary;

public class LevenshteinDistance {
	public int computeLD(String s, String t)
	{
		//Step 1
	
		int cost = 0;
		int i;
		int j;
		
		if(s.length() == 0)
		{
			return t.length();
		}
		
		if(t.length() == 0)
		{
			return s.length();
		}
		
		int[][] matrix = new int[s.length()+1][t.length()+1];
		
		/*
		 * Step 2
		 */
		 
		for( i=0; i <= s.length() ; i++)
		{
			matrix[i][0] = 	i;
			
		}
		
		for( j=0; j <= t.length() ; j++)
		{
			matrix[0][j] = j;
		}
		
		/*
		 * Step 3
		 */
		
		for ( i = 1; i <= s.length(); i++) 
		{
		      char one = s.charAt(i-1);

		      // Step 4

		      for ( j = 1; j <= t.length(); j++) 
		      {
		    	  char two = t.charAt(j-1);

		        // Step 5

		        if (one == two) {
		          cost = 0;
		        }
		        else {
		          cost = 1;
		        }
		        matrix[i][j] = Minimum (matrix[i-1][j]+1, matrix[i][j-1]+1, matrix[i-1][j-1] + cost);
		      }
		}
		
		return matrix[s.length()][t.length()];
	}
	
	public int Minimum(int a, int b, int c)
	{
		int min=a;
		if(a*b*c == 0 )
		{
			min = 0;
		}
		
		if(b < min)
		{
			min = b;
		}
		 if(c < min)
		 {
			 min = c;
		 }
		 
		 return min;
		
	}

}
