
import java.io.File;
import java.util.Scanner;
import java.util.Map.Entry;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Comparator;

public class WordCountSort {

	public static void selectionSort(String[] data) {
		int n = data.length;
		for (int i = 0; i < n - 1; i++) {
			int minIndex = i;
			for (int j = i + 1; j < n; j++) {
				if (data[minIndex].compareTo(data[j]) < 0) {
					minIndex = j;
				}
			}
			if (i != minIndex)
				swap(data, minIndex, i);

		}
	}

	public static void insertionSort(String[] data) {
		int n = data.length;
		for (int k = 1; k < n; k++) {
			String cur = data[k];
			int j = k;
			while (j > 0 && data[j - 1].compareTo(cur) > 0) {
				data[j] = data[j - 1];
				j--;
			}
			data[j] = cur;
		}
	}

	/** Merge two strings. See the textbook for explanation. **/
	public static void merge(String[] S1, String[] S2, String[] S) {
		int i = 0, j = 0;
		while (i + j < S.length) {
			if (j == S2.length || (i < S1.length && S1[i].compareTo(S2[j]) < 0))
				S[i + j] = S1[i++];
			else
				S[i + j] = S2[j++];
		}
	}

	public static void mergeSort(String[] S) {
		int n = S.length;
		if (n < 2)
			return;
		int mid = n / 2;
		// partition the string into two strings
		String[] S1 = Arrays.copyOfRange(S, 0, mid);
		String[] S2 = Arrays.copyOfRange(S, mid, n);
		mergeSort(S1);
		mergeSort(S2);
		merge(S1, S2, S);
	}
	
	public static void quickSort(String[] array, int low, int high) {
		
		if(low >= high){
			
			return;
			
		} 
		
		int middle = low + (high - low) / 2;
		String pivot = array[middle];
 
		// make left < pivot and right > pivot
		int i = low, j = high;
		while (i <= j) {
			while (array[i].compareTo(pivot) < 0) {
				i++;
			}
 
			while (array[j].compareTo(pivot) > 0) {
				j--;
			}
 
			if (i <= j) {
				String temp = array[i];
				array[i] = array[j];
				array[j] = temp;
				i++;
				j--;
			}
		}
 
		// recursively sort two sub parts
		if (low < j)
			quickSort(array, low, j);
 
		if (high > i)
			quickSort(array, i, high);

		
/*			int pivot = partition(array, start, end);
			
			quickSort(array, start, pivot - 1);
			quickSort(array, pivot + 1, end);*/
		
	}
	
	/*public static int partition(String[] array, int start, int end) {
		
		String pivot = array[end];
		
		int i = (start - 1);
		
		for (int j = start; j <= end - 1 ; j++) {
			
			if(array[j].compareTo(pivot) <= 0) {
				
				i++;
				
				String temp = array[i];
				array[i] = array[j];
				array[j] = temp;
				
			}
			
		}
		
		String temp = array[i+1];
		array[i+1] = array[end];
		array[end] = temp;
		
		return i + 1;
	}*/

	private static void swap(String[] array, int i, int j) {
		String tmp = array[i];
		array[i] = array[j];
		array[j] = tmp;
	}

	public static Entry<String, Integer> count_ARRAY_SORT(String[] tokens, String sortMethod) {
		int CAPACITY = 100000000;
		String[] words = new String[CAPACITY];
		int[] counts = new int[CAPACITY];
		if (sortMethod.equals("SELECT"))
			selectionSort(tokens);
		else if (sortMethod.equals("INSERT"))
			insertionSort(tokens);
		else if (sortMethod.equals("MERGE"))
			mergeSort(tokens);
		else if (sortMethod.equals("JAVA"))
			Arrays.sort(tokens);
		else if (sortMethod.equals("QUICK"))
			quickSort(tokens, 0, tokens.length - 1);
		else
			System.out.println(sortMethod + " sorting method does not exist.");

		int j = 0, k = 0;
		int len = tokens.length;
		while (j < len - 1) {
			int duplicates = 1;
			while (j < len - 2 && tokens[j].equals(tokens[j + 1])) {
				j++;
				duplicates++;
			}

			words[k] = tokens[j];
			counts[k] = duplicates;
			j++;
			k++;
		}

		/** get the max count **/
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

	static String[] readText(String PATH) throws Exception {
		Scanner doc = new Scanner(new File(PATH)).useDelimiter("[^a-zA-Z]+");
		// tokenize text. any characters other than English letters(a-z and A-Z
		// ) are delimiters.
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
	/*
	 * 	Data is 200
		MERGE method	 time=           7. Most popular word is of:97
		INSERT method	 time=          28. Most popular word is of:97
		SELECT method	 time=          32. Most popular word is of:97
		
		Data is 500
		MERGE method	 time=           9. Most popular word is of:220
		INSERT method	 time=          46. Most popular word is of:220
		SELECT method	 time=          58. Most popular word is of:220

		Data is 1k
		MERGE method	 time=          14. Most popular word is of:429
		INSERT method	 time=          94. Most popular word is of:429
		SELECT method	 time=         199. Most popular word is of:429

		Data is 5k
		MERGE method	 time=          38. Most popular word is of:2117
		INSERT method	 time=        6825. Most popular word is of:2117
		SELECT method	 time=        9574. Most popular word is of:2117
		
		Data is 10k
		MERGE method	 time=          48. Most popular word is of:4308
		INSERT method	 time=       37634. Most popular word is of:4308
		SELECT method	 time=       76394. Most popular word is of:4308
		
		Data is 100k
		MERGE method	 time=         783. Most popular word is of:41755
		
		Data is 1m
		MERGE method	 time=       12827. Most popular word is of:439027
		
		Data is 3.6m 
		MERGE method	 time=      118999. Most popular word is of:1420736
	  */
	public static void main(String[] args) throws Exception {
		String PATH = "/home/geng/eclipse-workspace/Lab2-254/src/dblp";
		String[] METHODS = { "QUICK", "MERGE", "SELECT","INSERT"};
		String[] DATASETS = { "200", "500", "1k", "5k", "10k", "100k", "1m", "" };

		String[] tokens;
		// run the experiments on different data sets
		for (int j = 0; j < 8; j++) {
			// run the experiments using different methods
			System.out.println("Data is " + DATASETS[j]);
			for (int i = 0; i < 2; i++) {
				tokens = readText(PATH + DATASETS[j] + ".txt");
				long startTime = System.currentTimeMillis();
				Entry<String, Integer> entry = count_ARRAY_SORT(tokens, METHODS[i]);
				long endTime = System.currentTimeMillis();
				String time = String.format("%12d", endTime - startTime);
				System.out.println(METHODS[i] + " method\t time=" + time + ". Most popular word is " + entry.getKey()
						+ ":" + entry.getValue());
			}
		}
	}
}
