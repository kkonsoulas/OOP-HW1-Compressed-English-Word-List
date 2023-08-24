# OOP HW1 - Compressed English Word-List

An English word-list implementation featuring insertion, deletion, word listing, searching, edit distance
searching and suffix searching based on the compressed trie data structure.

## Usage

| **Command** 	|                                                                   **Description**                                                                  	|                                                                                                                 **Print on Success**                                                                                                                	|    **Print on Failure**   	|
|:-----------:	|:--------------------------------------------------------------------------------------------------------------------------------------------------:	|:---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------:	|:-------------------------:	|
| -i word     	| Inserts word into Trie. Returns success if the word does not exist in the Trie and is successfully inserted into it. Otherwise it returns failure. 	| ADD word OK*                                                                                                                                                                                                                                        	| ADD word NOK*             	|
| -r word     	| Deletes the word word from the Trie. Returns success if the word exists in the Trie and is successfully deleted. Otherwise it returns failure.     	| RMV word OK*                                                                                                                                                                                                                                        	| RMV word NOK*             	|
| -f word     	| Searches for word in Trie.                                                                                                                         	| FND word OK*                                                                                                                                                                                                                                        	| FND word NOK*             	|
| -p          	| Prints the pre-order representation of Trie.                                                                                                       	|                                                                                                                                                                                                                                                     	|                           	|
| -d          	| Prints all the words of the stored dictionary in alphabetical order.                                                                               	|                                                                                                                                                                                                                                                     	|                           	|
| -w word X   	| Searches the Trie for all words with the same length with the word word which are far exactly X characters from the given word.                    	| The string “Distant words of W (X):” is printed followed by character line break, where W is the word and X is the distance of the searched words. Then the words that are X characters away from the given word are printed in alphabetical order. 	| Same behaviour as success 	|
| -s suffix   	| Searches the Trie for words containing the given suffix.                                                                                           	| The string “Words with suffix S:” followed by a newline character, where S is the given suffix.<br> Then the words containing the given ending are printed in alphabetical order.                                                                   	| Same behaviour as success 	|
| -q          	| Prints the string"Bye bye!” followed by a newline character and terminates the program.                                                            	|                                                                                                                                                                                                                                                     	|                           	|

## Build \& Testing

Gradle has been used as a build tool for this project. The following tasks have been provided :
 
- **./gradlew build** \# builds the application
- **./gradlew runTrie** \# runs the application
- **./gradlew jar** \# creates the jar file

For testing purposes a Makefile has been provided with the following commands : 

- **make** \# for running all the tests
- **make clean** \# for cleaning the produced .out files