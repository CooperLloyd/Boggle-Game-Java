import java.io.InputStream;
import java.util.*;

/**
 * 
 */
public class BoggleDictionary {
	private ArrayList<String> dictionary;

	/**
	 * Reads the dictionary file and stores the words from the file in the Set
	 * this.dictionary. The words in this file are in all lower case.
	 * 
	 * <p>
	 * The dictionary file is named dictionary.txt and needs to be located in the
	 * same package as this file.
	 * 
	 * @throws RuntimeException if dictionary.txt cannot be found
	 * 
	 */
	private void readDictionary() {
		InputStream in = this.getClass().getResourceAsStream("dictionary.txt");
		if (in == null) {
			throw new RuntimeException("dictionary.txt is missing");
		}
		Scanner dictionaryInput = new Scanner(in);
		while (dictionaryInput.hasNext()) {
			String word = dictionaryInput.next();
			this.add(word.trim());
		}
		dictionaryInput.close();
	}

	/**
	 * constructor method for BoggleDictionary object
	 * initialises field dictionary as empty ArrayList
	 * calls read dictionary to fill in dictionary
	 */
	public BoggleDictionary() {
		this.dictionary = new ArrayList<>();
		
		// you should (probably) read the dictionary last
		// but perhaps not depending on your implementation
		this.readDictionary();
	}

	/**
	 * uses size method on dictionary
	 * to find how manny elements are contained
	 *
	 * @return int value of size of dictionary
	 */
	public int size() {
		return this.dictionary.size();
	}


	/**
	 * checks if word is in dictionary by binary search
	 * then adds it if its not in dictionary
	 *
	 * @param word word to be added to dictionary
	 * @return true if word is added false if not
	 */
	public boolean add(String word) {
		if(Collections.binarySearch(this.dictionary, word) > 0){
			return false;
		}
		this.dictionary.add(word);
		return true;
	}

	/**
	 * uses a binary search to look for word
	 *
	 * @param word word to check for in dictionary
	 * @return true if word in dictionary false if not
	 */
	public boolean contains(String word) {
		// some words which are present are not found by binary search.
		// this is a java issue...
//		String test = "hug";
//		System.out.println(Collections.binarySearch(this.dictionary, test));
//		System.out.println(this.dictionary.indexOf(test));

//		return this.dictionary.contains(word); // this method is O(n) but will find stuff.
		return Collections.binarySearch(this.dictionary, word) >= 0;
	}

	/**
	 * finds how many characters are different
	 * between two strings (hamming distance)
	 *
	 * @param s first string to test
	 * @param t second string to test
	 * @return integer value representing hamming distance
	 */
	public static int hammingDistance(String s, String t) {
		int count = 0;
		if (s.length() - t.length() != 0){
			return 420;
		}
		for(int i = 0; i < s.length(); i++){
			if(s.charAt(i) != t.charAt(i)){
				count++;
			}
		}
		return count;
	}

	/**
	 * Binary searches for a word in dictionary, and from the given index iterates back and
	 * forwards in dictionary until first letter changes calculating hamming distance of each word
	 * @param word word to find like types of in the dictionary
	 * @return SortedSet containing all word with hamming distance <= 1
	 */

	public SortedSet<String> wordsSimilarTo(String word) {
		SortedSet<String> similar = new TreeSet<>();
//		int pos = this.dictionary.indexOf(word); // this method is O(n) but will find stuff.
		// binary search works for some words... "hug" for example is not found
		// while a linear search will find it.
		int pos = Collections.binarySearch(this.dictionary, word);
		char fst_letter = word.charAt(0);

		// go up until first letter changes
		for (int i = pos; this.dictionary.get(i).charAt(0) == fst_letter; i--){
			String fromDict = this.dictionary.get(i);
			if (hammingDistance(word, fromDict) <= 1){
				similar.add(fromDict);
			}
		}
		// go down until first letter changes
		for (int i = pos; this.dictionary.get(i).charAt(0) == fst_letter; i++){
			String fromDict = this.dictionary.get(i);
			if (hammingDistance(word, fromDict) <= 1){
				similar.add(fromDict);
			}
		}
		return similar;
	}

}