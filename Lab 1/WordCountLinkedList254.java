
import java.io.File;
import java.util.Scanner;
import java.util.Map.Entry;
import java.util.AbstractMap;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Map;
import java.util.HashMap;

public class WordCountLinkedList254 {
	public static Entry<String, Integer> count_ARRAY(String[] tokens) {
		//complexity: n^2
		int CAPACITY = 10000000;
		String[] words = new String[CAPACITY];
		int[] counts = new int[CAPACITY];
		for (int j = 0; j < tokens.length; j++) {
			String token = tokens[j];
			for (int i = 0; i < CAPACITY; i++) {
				if (words[i] == null) {
					words[i] = token;
					counts[i] = 1;
					break;
				} else if (words[i].equals(token))
					counts[i] = counts[i] + 1;
			}
		}

		int maxCount = 0;
		String maxWord = "";
		for (int i = 0; i < CAPACITY & words[i] != null; i++) {
			if (counts[i] > maxCount) {
				maxWord = words[i];
				maxCount = counts[i];
			}
		}
		return new AbstractMap.SimpleEntry<String, Integer>(maxWord, maxCount);
	}

	public static Entry<String, Integer> count_LINKED_LIST(String[] tokens) {	
		//complexity: n*m^2
		/*Algorithm 1 is slow is due to the get(i) method, it loads the list again in every nesting for loop*/
		LinkedList<Entry<String, Integer>> list = new LinkedList<Entry<String, Integer>>();
		for (int j = 0; j < tokens.length; j++) {
			String word = tokens[j];
			boolean found = false;
			for (int i = 0; i < list.size(); i++) {
				Entry<String, Integer> e = list.get(i);

				if (word.equals(e.getKey())) {
					e.setValue(e.getValue() + 1);
					list.set(i, e);
					found = true;
					break;
				}
			}
			if (!found)
				list.add(new AbstractMap.SimpleEntry<String, Integer>(word, 1));
		}

		int maxCount = 0;
		String maxWord = "";
		for (int i = 0; i < list.size(); i++) {
			int count = list.get(i).getValue();
			if (count > maxCount) {
				maxWord = list.get(i).getKey();
				maxCount = count;
			}
		}return new AbstractMap.SimpleEntry<String, Integer>(maxWord, maxCount);
	}

	public static Entry<String, Integer> count_LINKED_LIST_improved(String[] tokens){
		//complexity: nm
		LinkedList<Entry<String, Integer>> list = new LinkedList<Entry<String, Integer>>();
		ListIterator<Entry<String,Integer>> iterator = list.listIterator();
		for (int j = 0; j < tokens.length; j++) {
			String word = tokens[j];
			boolean found = false;

			while(iterator.hasNext()) {
				Entry<String,Integer> e = iterator.next();
				if (e.getKey().equals(word)) {
					e.setValue(e.getValue() + 1);
					iterator.set(e);
					found = true;
					break;
				}
			
			}
			if (!found) {
				list.add(new AbstractMap.SimpleEntry<String, Integer>(word, 1));
			}
			iterator = list.listIterator();	
			//restart the iterator in every loop
		}

		iterator = list.listIterator();
		int maxCount = 0;
		String maxWord = "";
		while(iterator.hasNext()){
			Entry<String,Integer> temp = iterator.next();
			String word = temp.getKey();
			int count = temp.getValue();
			if (count > maxCount) {
				maxWord = word;
				maxCount = count;
			}
		}return new AbstractMap.SimpleEntry<String, Integer>(maxWord, maxCount);
		
	}
	
	
	
	static String[] readText(String PATH) throws Exception {
		Scanner doc = new Scanner(new File(PATH)).useDelimiter("[^a-zA-Z]+");
		int length = 0;
		while (doc.hasNext()) {
			doc.next();
			length++;
		}

		String[] tokens = new String[length];
		Scanner s = new Scanner(new File(PATH)).useDelimiter("[^a-zA-Z]+");
		length = 0;
		while (s.hasNext()) {
			tokens[length] = s.next().toLowerCase();
			length++;
		}
		doc.close();

		return tokens;
	}

	public static Entry<String, Integer> Bonus(String[] tokens) {	
		//complexity: n*m^2
		/*Algorithm 1 is slow is due to the get(i) method, it loads the list again in every nesting for loop*/
		Map<String,Integer> hashtable = new HashMap<>();
		for (int j = 0; j < tokens.length; j++) {
			String word = tokens[j];
				if(hashtable.containsKey(word)) {
					hashtable.put(word, hashtable.get(word) + 1);
				}else {
					hashtable.put(word, 1);
				}			
		}

		int maxCount = 0;
		String maxWord = "";
		for (Entry<String, Integer> entry: hashtable.entrySet()) {
			if(maxCount<entry.getValue()) {
				maxCount = entry.getValue();
				maxWord = entry.getKey();
			}
		}
		return new AbstractMap.SimpleEntry<String, Integer>(maxWord, maxCount);
	}
	
	//					dblp200	dblp500	dblp1k	dblp5k	dblp10k	dblp100k	dblp1m	dblp3.6m
	//Linked-list time	171		522		2252	203458	1065147	---			---		---
	//Array       time	17		53		144		7453	23033	3166464		---		---
	//Improved    time	23		37		70		946		3384	88744		2036028	12952632
	//Bonus		  time											129
	
	
	public static void main(String[] args) throws Exception {

		String PATH = "/home/geng/eclipse-workspace/Lab1 -254/src/dblp100k.txt";
		String[] tokens = readText(PATH);
		long startTime = System.currentTimeMillis();
		Entry<String, Integer> entry = Bonus(tokens);
		long endTime = System.currentTimeMillis();
		String time = String.format("%12d", endTime - startTime);
		System.out.println("time\t" + time + "\t" + entry.getKey() + ":" + entry.getValue());

		/*tokens = readText(PATH);
		startTime = System.currentTimeMillis();
		entry = count_ARRAY(tokens);
		endTime = System.currentTimeMillis();
		time = String.format("%12d", endTime - startTime);
		System.out.println("time\t" + time + "\t" + entry.getKey() + ":" + entry.getValue());
		
		tokens = readText(PATH);
		startTime = System.currentTimeMillis();
		entry = count_LINKED_LIST_improved(tokens);
		endTime = System.currentTimeMillis();
		time = String.format("%12d", endTime - startTime);
		System.out.println("improved time\t" + time + "\t" + entry.getKey() + ":" + entry.getValue());*/
	}

}
