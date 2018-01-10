package webSearch;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

public class search {

	public static void main(String[] args)
	{
		String words;
		String word[];
		int count1 = 0;
		Scanner input = new Scanner(System.in);
		
		
		System.out.println("Enter the word/words you want to search: ");
		words = input.nextLine();
		words = words.toLowerCase();
		
		//each word entered by the user is stored seperated in an array of word
		word = words.split("\\s+");
		
		//a Treemap is used to create 
		TreeMap<String, String> hm = new TreeMap<String, String>();
		
		for(int i = 0; i < word.length; i++){
			FileReader fr;
			try {
				//files for each word entered by the user 
				//is read to extract useful data 
				fr = new FileReader("C:\\Users\\Abdullah Butt\\Documents\\NetBeansProjects\\DSA\\src\\dir\\" + word[i] + ".txt");
				BufferedReader br = new BufferedReader(fr);
		        String sCurrentline1, tmp;
		        String sCurrentline2[];
				try {
					while((sCurrentline1 = br.readLine()) != null)
					{
						System.out.println(sCurrentline1);
						/*
							sCurrentline2 = sCurrentline1.split("\t");
						if(hm.containsKey(sCurrentline2[0])){
							tmp = hm.get(sCurrentline2[0]);
							count1=Integer.parseInt(tmp);
							count1++;
							hm.put(sCurrentline2[0], count1 + "\t" + sCurrentline2[4]); //+ "\t" + sCurrentline2[4]+ "\t" + sCurrentline2[1] + "\t" + sCurrentline2[2] + "\t" + sCurrentline2[3]);
					    }
						else{
							hm.put(sCurrentline2[0], 0 + "\t" + sCurrentline2[4]); // + "\t" + sCurrentline2[4]+ "\t" + sCurrentline2[1] + "\t" + sCurrentline2[2] + "\t" + sCurrentline2[3]);
						}
						*/
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					System.out.println("The file \"" + word[i] + "\" could not be read.");
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				System.out.println("The file name \"" + word[i] + "\" you entered was not found.");
			}
		}
		//System.out.println(hm);
	}	
}