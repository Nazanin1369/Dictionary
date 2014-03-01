package org.Nazanin.Dictionary;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class Dictionary {
	HashMap<Character, Long> dictionary = new HashMap<Character, Long>();
	RandomAccessFile rf;
	String userWord;
	List<String> suggestions = new ArrayList<String>();

	public HashMap<Character, Long> getDictionary() {
		return dictionary;
	}

	public void setDictionary(HashMap<Character, Long> dictionary) {
		this.dictionary = dictionary;
	}

	public boolean isInputValid(String input) 
	{
		return input != null && input.length() > 0;
	}

	public boolean isListValid(List data) 
	{
		return (data != null && data.size() > 0);
	}

	public void readFromDictionary() {
		String path = "C:/Users/Nazanin/en-US/en-US.dic";
		try {
			rf = new RandomAccessFile(path, "r");
			String word;
			while ((word = rf.readLine()) != null) {
				Character c = word.toLowerCase().charAt(0);
				if (!dictionary.containsKey(c)) {
					dictionary.put(c, rf.getFilePointer());
				}

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Printing hash map
	 */

	public void printHashMap(HashMap<Character, Long> hash) {
		for (Character s : hash.keySet()) {
			String key = s.toString();
			String value = hash.get(s).toString();
			System.out.println(key + " " + value);
		}
	}

	/**
	 * Searching the hash to find the first character of the user input and
	 * return the offset to start searching from that.
	 */
	public Long getOffset(String input, HashMap<Character, Long> hash) {
		Character c = input.charAt(0);
		Long value = hash.get(c);
		return value;
	}

	/**
	 * Get all possible edits of given word by swapping, inserting and deleting
	 * 
	 * @param word
	 * @return
	 */
	public List<String> getEdits(String word) {
		List<String> edits = new ArrayList<String>();
		int wordLen = word.length();

		// Swapping i with i+1
		for (int i = 1; i < wordLen - 1; i++) {
			edits.add(word.substring(0, i) + word.charAt(i + 1)
					+ word.charAt(i) + word.substring(i + 2));
		}

		// deleting one char, skipping i
		for (int i = 0; i < wordLen; i++) {
			edits.add(word.substring(0, i) + word.substring(i + 1));
		}

		// inserting one char
		for (int i = 0; i < wordLen + 1; i++) {
			for (char j = 'a'; j <= 'z'; j++)
				edits.add(word.substring(0, i) + j + word.substring(i));
		}

		return edits;

	}

	/**
	 * For given word, return closest match correct spelling
	 * 
	 * @param word
	 * @return
	 */

	public String find(Long offset)
	{
		List<String> edits = getEdits(userWord);
		String target = "";
		if (!isInputValid(userWord))
		{
			return null;
		}	

		try {
				String word;
				rf.seek(offset);

				while ((word = rf.readLine()) != null)
				{
					//first
					if (word.equals(userWord))
					{
						target = word;
						if(! suggestions.contains(word))
						{
							suggestions.add(target);
						}
						
					}
					
					//Second
					if (edits.size() > 0) 
					{
						for (String edit : edits) 
						{
							if (word.equals(edit)) 
							{
								target = edit;
								if(! suggestions.contains(word))
								{
									suggestions.add(target);
								}
							}
						}
					}
	
					//Third
					if (suggestions.size() <= 0) 
					{
						List<String> tmpEdits = null;
						for (String edit : edits) 
						{
							tmpEdits = getEdits(edit);
							for (String tmpEdit : tmpEdits) 
							{
								if (word.equals(tmpEdit)) 
								{
									target = tmpEdit;
									if(! suggestions.contains(word))
									{
										suggestions.add(target);
									}
								}
							}
						}
					}


			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		
		return target;
	}

	/**
	 * For two words returns the Levenshtein distance.
	 * @param s1
	 * @param s2
	 * @return
	 */
	public int countLD(String s1, String s2) {
		LevenshteinDistance ld = new LevenshteinDistance();
		return ld.computeLD(s1, s2);
	}


	public void showSuggestions()
	{
		System.out.println("Suggestions:");
	    for(String t : suggestions)
		{
			System.out.println(t);
		}
	}
}
