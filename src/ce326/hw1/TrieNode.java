package ce326.hw1;

import java.util.Locale;

public class TrieNode {
	String word;
	boolean valid;
	TrieNode[] letterNode;
	TrieNode parentNode;

	//core
	public TrieNode(String word, TrieNode parentNode){
		this.word = word.toLowerCase();
		this.parentNode = parentNode;
		letterNode = new TrieNode[26];
	}

	public TrieNode(String word,TrieNode parentNode,boolean valid){
		this(word,parentNode);
		this.valid = valid;
		letterNode = new TrieNode[26];
	}

	public TrieNode(String word,boolean valid){
		this.word = word;
		this.valid = valid;
		letterNode = new TrieNode[26];
	}

	/*void rename(String newWord){
		word = newWord.toLowerCase();
	}*/

	String getWord(){
		return word;
	}

	void setWord(String word){
		this.word = word;
	}

	TrieNode getParentNode(){
		return parentNode;
	}

	boolean getValid(){
		return valid;
	}

	void setValid(boolean valid){
		this.valid = valid;
	}

	void setParentNode(TrieNode parentNode){
		this.parentNode = parentNode;
	}
	//


	TrieNode getLetterNode(char letter){
		return letterNode[Character.getNumericValue(letter) - 10];
	}

	TrieNode getLetterNode(int index){
		return letterNode[index];
	}

	TrieNode[] getLetterNodeArray(){
		return letterNode;
	}

	void setLetterNode(char letter,TrieNode newNode){
		letterNode[Character.getNumericValue(letter) - 10] = newNode;
	}

	boolean hasChildren(){
		int i;
		for(i=0 ;i < getLetterNodeArray().length ;i++){
			if(getLetterNode(i) != null){
				return true;
			}
		}
		return false;
	}

	int calcChildren(){
		int i,children;
		for(i=0,children=0 ; i < getLetterNodeArray().length ;i++){
			if(getLetterNode(i) != null){
				children++;
			}
		}
		return children;
	}

	char firstWordChar(){
		return this.getWord().charAt(0);
	}

	void makeParent(TrieNode newParent){
		this.getParentNode().makeChild(newParent);
		newParent.makeChild(this);
	}

	void makeChild(TrieNode child){
		this.setLetterNode(child.firstWordChar(),child);
		child.setParentNode(this);
	}

	TrieNode detatch(){
		getParentNode().setLetterNode(firstWordChar(),null);
		return this;
	}


	void printPreOrder(){

		int i;
		if(getValid()){
			System.out.print(getWord()+"# "); //print parent
		}
		else{
			System.out.print(getWord()+" "); //print parent

		}
		for(i=0 ; i < getLetterNodeArray().length ;i++){
			if(getLetterNode(i) != null){
				getLetterNode(i).printPreOrder();
			}
		}

	}

	void printWholeWord(){
		if(this.valid){
			System.out.print(constructWholeWord());
		}
	}

	private String constructWholeWord(){
		String res ="";
		if(getParentNode() != null){
			res = getParentNode().constructWholeWord();
		}
		return res.concat(getWord());
	}

	void dictionary(String str){
		int i;
		if(getValid()){
			System.out.println(str + getWord());
		}

		for(i=0 ; i < getLetterNodeArray().length ;i++){
			if(getLetterNode(i) != null){
				getLetterNode(i).dictionary(str + getWord());
			}
		}
	}

	void similarWord(String word ,int charsOff){
		int wordLength = word.length();
		String nodeWord = getWord();
		int nodeWordLength = nodeWord.length();
		int i,j;


		if(nodeWordLength > wordLength){
			//too big, abort
			return;
		}

		//calc the chars off
		for(i=0,j=0 ;i< nodeWordLength && j <= charsOff ;i++){
			if(word.charAt(i) != nodeWord.charAt(i)){
				j++;
			}
		}
		charsOff -= j;
		//

		if(charsOff == -1){
			//nodeWord has too many chars off, abort
			return;
		}

		if(nodeWordLength < wordLength){
			//we need to go deeper

			//we may have less available chars to be off
			for(i=0 ;i<getLetterNodeArray().length ;i++){
				if(getLetterNode(i) != null){
					getLetterNode(i).similarWord(word.substring(nodeWordLength),charsOff);
				}
			}
		}
		else{ //nodeWordLength == wordLength
			//we found a possible match
			if(charsOff == 0 && getValid()){
				printWholeWord();
				System.out.println();
			}
		}

	}


	TrieNode findNode(String searchedWord){
		TrieNode current = this;
		while(current != null){

			int i;
			for(i=0 ; i < searchedWord.length() && i< current.getWord().length() && searchedWord.charAt(i) == current.getWord().charAt(i); i++);
			if(i == current.getWord().length() && i == searchedWord.length()){
				return current; //true
			}
			else if(i < current.getWord().length() /*&& i <= searchedWord.length()*/){
				//SearchedWord doesn't match
				return null;
			}
			else{ // i >= current.getWord().length()
				//SearchedWord matches but is bigger
				//go to next node
				searchedWord = searchedWord.substring(i);//not ready
				current = current.getLetterNode(searchedWord.charAt(0));
			}

		}
		return null;
	}


	boolean endsWith(String suffix){
		int i,j;


		TrieNode current = this;
		for(i=suffix.length() - 1,j=getWord().length()-1; i>=0 ;i--,j--){
			if(j==-1){
				current = current.getParentNode();
				j =  current.getWord().length() -1;
			}
			if(suffix.charAt(i) != current.getWord().charAt(j)){
				return false;
			}
		}
		return true;
	}

	void wordsWithSuffix(String suffix){
		//lazy implementation

		if(getValid() && endsWith(suffix)){
			printWholeWord();
			System.out.println();
		}
		int i;
		for(i=0 ;i<getLetterNodeArray().length ;i++){
			if(getLetterNode(i) != null){
				getLetterNode(i).wordsWithSuffix(suffix);
			}
		}
	}

	boolean removeNode(){

		if(!getValid()){
			//if it is not even a valid word
			return false;
		}

		int children = calcChildren();

		if(children != 0){
			//if nodeForRemoval has children
			setValid(false);
			if(children == 1 ){
				//compress with child
				int i;
				for(i=0 ;getLetterNode(i) == null ;i++); // find child

				//add node's for removal word to it's child
				getLetterNode(i).setWord( getWord() + getLetterNode(i).getWord());
				//delete nodeForRemoval by replacing it
				getParentNode().makeChild(getLetterNode(i));
			}
			return true;
		}

		//nodeForRemoval has no children
		TrieNode parent = getParentNode();
		detatch();

		//fixing Trie process

		if(parent.getValid() || parent.getParentNode() == null){
			//if parent Valid word or root then we are all good
			return true;
		}

		//parent not Valid word or root (subject to deletion)

		//calculate  remaining parent's children (till 2)
		int i;
		for(i=0,children=0 ; i < parent.getLetterNodeArray().length && children<2 ;i++){
			if(parent.getLetterNode(i) != null){
				children++;
			}
		}
		//

		switch(children){
			//case 0:
				//removed node was the only child
				//We will never enter here because parent cannot be Valid or root so it could not exist previously with only one child
				//return true;
			case 1:
				//parent has another child other than the removed node
				//parent not valid or root, it must be compressed

				//find parent's child
				for(i=0 ;parent.getLetterNode(i) == null ;i++);
				//add parent word to it's child
				parent.getLetterNode(i).setWord( parent.getWord() + parent.getLetterNode(i).getWord());
				//"delete" parent by replacing it
				parent.getParentNode().makeChild(parent.getLetterNode(i));
				break;
			case 2:
				//parent has (at least) another 2 children, so we are fine;
				//return true;

		}

		return true; // we will never get here (just for the compiler)
	}


}
