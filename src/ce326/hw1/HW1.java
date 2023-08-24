package ce326.hw1;
import java.util.Scanner;

public class HW1 {
	public static void main(String[] args){
		Trie dictionaryTrie = new Trie();
		Scanner dataIn = new Scanner(System.in);
		String command;
		String word;
		boolean boolResult;
//		int i=1;

		System.out.print("?: ");
		while(dataIn.hasNextLine()){
			command = dataIn.next();

			switch (command) {
				case "-i":
					word = dataIn.next(); //word to be inserted
					boolResult = dictionaryTrie.insert(word.toLowerCase());
					System.out.println("\nADD " + word + (boolResult ? " OK" : " NOK"));
					break;
				case "-r":
					word = dataIn.next();
					boolResult = dictionaryTrie.remove(word);
					System.out.println("\nRMV " + word + (boolResult ? " OK" : " NOK"));
					break;
				case "-f":
					word = dataIn.next();
					boolResult = dictionaryTrie.find(word.toLowerCase());
					System.out.println("\nFind of \"" + word + "\" was: " + boolResult);
					break;
				case "-p":
					dictionaryTrie.printPreOrder();
					break;
					case "-d":
					dictionaryTrie.dictionary();
					break;
					case "-w":
					word = dataIn.next();
					command = dataIn.next();
					dictionaryTrie.similarWords(word, Integer.parseInt(command));
					break;
				case "-s":
					word = dataIn.next();
					dictionaryTrie.wordsWithSuffix(word);
					break;
				case "-q":
					System.out.println("\nBye bye!");
					return;

			}

			System.out.print("?: ");


		}


	}
}
