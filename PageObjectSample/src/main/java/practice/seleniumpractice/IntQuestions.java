package practice.seleniumpractice;
import java.util.*;
import java.util.Collections;


public class IntQuestions {
	public static void main(String[] args) {
		IntQuestions iq = new IntQuestions();
		iq.checkSort();
		iq.countVowels();
		iq.vowelsFromNet();
		iq.CVowels();
	}

	public void checkSort(){
		System.out.println("Hi There");
		ArrayList<Integer> al = new ArrayList<Integer>();
		al.add(4); al.add(6); al.add(3); al.add(2); al.add(7);
		ArrayList<Integer> al1 = new ArrayList<Integer>(Arrays.asList(2,3,4,6,7));

		System.out.println(al);
		Collections.sort(al);
		System.out.println(al);

		if(al1.equals(al)) {
			System.out.println("List is sorted"); 
		}
		else {
			System.out.println("List is not sorted"); 
		}
	}

	public boolean sortedNew(List<String> list) {
		boolean sorted = true;        
		for (int i = 1; i < list.size(); i++) {
			if (list.get(i-1).compareTo(list.get(i)) > 0) sorted = false;
		}
		return sorted;
	}


	public void vowelsFromNet() {
		String message = "Check Vowels here io";
		long startTime = System.nanoTime();
		String tests[] = message.split(" ");
		int totalVowel = 0;
		for(String test : tests){
			int vowelCount = 0;
			test = test.toLowerCase();
			for(int i=0;i<test.length();i++){
				switch(test.charAt(i)){
				case 'a':
				case 'e':
				case 'i':
				case 'o':
				case 'u':
					vowelCount++;
				}

			}
			totalVowel = totalVowel+vowelCount;
		}
		long endTime   = System.nanoTime();
		long totalTime = (endTime-startTime);
		System.out.println("total vowels "+totalVowel + " and time taken using case is "+totalTime);
	}


	public void countVowels(){
		String myName= "Check Vowels here io";
		long startTime = System.nanoTime();
		String vowel="aeiou";
		int count =0;
		for(int i=0;i<=myName.length()-1;i++) {
			if(vowel.contains(String.valueOf(myName.charAt(i)))) {
				count++;
			}
		}
		long endTime   = System.nanoTime();
		long totalTime = (endTime-startTime);
		System.out.println("\n Count is " +count);
		System.out.println("Time Taken using String Contains " +totalTime);
	}

	public void CVowels() {
		String str1 = "Check Vowels here io";
		long startTime = System.nanoTime();

		char[] ch1 = str1.toCharArray();
		int count =0;

		for(int i=0;i<=str1.length()-1;i++) {
			if((ch1[i]=='a')|(ch1[i]=='e')|(ch1[i]=='i')|(ch1[i]=='o')|(ch1[i]=='u')) {
				count++;
			}

		}
		long endTime   = System.nanoTime();
		long totalTime = (endTime-startTime);
		System.out.println("\n Count is " +count);
		System.out.println("Time Taken using multiple OR condition " +totalTime);
	}

}
