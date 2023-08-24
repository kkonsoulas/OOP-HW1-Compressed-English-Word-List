package ce326.hw1;

public class Trie{
	TrieNode root;

	public Trie(){
		root = new TrieNode("",null);
	}

	public TrieNode getRoot(){
		return root;
	}

	public TrieNode findNode(String searchedWord){
		return getRoot().findNode(searchedWord);
	}

	public boolean find(String searchedWord){
		return findNode(searchedWord) != null;
	}

	public boolean insert(String newWord){
		TrieNode current = findNode(newWord);

		//Check if it exists and is already valid
		//if not valid make it
		if(current != null){
			if(current.getValid()){
				return false;
			}
			current.setValid(true);
			return true;
		}
		//

		current = getRoot();
		while(!newWord.isEmpty()){
			int i;
			for(i=0 ; i < newWord.length() && i< current.getWord().length() && newWord.charAt(i) == current.getWord().charAt(i); i++){
				//empty
			}

			if(i == newWord.length()){
				//newWord is a substring of the word so make a new parent node with it

				//Naming part
				String newNodeName = new String(current.getWord().substring(0,i)); //copy the common part
				current.setWord(current.getWord().substring(i));                   //rename current with the rest

				current.makeParent(new TrieNode(newNodeName ,true));
				return true;
			}
			else if(i == current.getWord().length()){
				//Word is a substring of newWord
				//make unique part of newWord a child if we cannot go deeper
				String uniquePart = new String(newWord.substring(i));
				TrieNode nextNode = current.getLetterNode(uniquePart.charAt(0));
				if(nextNode == null){
					nextNode = new TrieNode(newWord.substring(i),true);
					current.makeChild(nextNode);
					return true;
				}
				newWord = uniquePart;
				current = nextNode;
			}
			else/* if(i < newWord.length() && i < current.getWord().length())*/{
				//
				String common = new String(newWord.substring(0,i)); // extract common part of the word
				newWord = newWord.substring(i);                     // keep the unique
				current.setWord(current.getWord().substring(i));    // keep the unique

				TrieNode commonNode =  new TrieNode(common,false);
				current.makeParent(commonNode);
				commonNode.makeChild(new TrieNode(newWord,true));
				return true;
			}


		}
		return(false);
	}

	void printPreOrder(){
		System.out.print("\nPreOrder:");
		getRoot().printPreOrder();
		System.out.println();
	}

	public boolean remove(String word){
		TrieNode nodeForRemoval = findNode(word);
		if(nodeForRemoval == null){
			return false;
		}
		return nodeForRemoval.removeNode();
	}


	public void dictionary(){
		TrieNode current = getRoot();

		System.out.print("\n\n***** Dictionary *****\n");
		current.dictionary(current.getWord());

		System.out.println();
	}

	public void similarWords(String word ,int charsOff){
		if(charsOff < 0){
			return;
		}
		System.out.printf("\n\nDistant words of %s (%d):\n",word,charsOff);
		getRoot().similarWord(word,charsOff);
		System.out.println();
	}


	public void wordsWithSuffix(String suffix){
		System.out.println("\n\nWords with suffix "+suffix+":");
		getRoot().wordsWithSuffix(suffix);
		System.out.println();
	}

}
