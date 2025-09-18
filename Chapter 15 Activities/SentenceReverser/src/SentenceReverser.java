import java.util.Scanner;
import java.util.Stack;

/**
 * Class for reversing the order of a sentence.
*/
public class SentenceReverser
{
    /**
     * Reverses the given sentence.
     *
     * @param sentence Sentence to be reversed.
     * @return reversed sentence.
    */
    public static String reverse(String sentence)
    {
    	Scanner scanner = new Scanner(sentence);
    	
        // Complete this method. Use a Stack.
        Stack<String> stack = new Stack<>();
        String finalSentence = "";
        while (scanner.hasNext()){
            String currentWord = inputReader.next();
            wordStack.push(currentWord);

            if (currentWord.endsWith(".")) {
                String reversedPart = "";
                boolean isFirstWord = true;

                while (!wordStack.isEmpty()) {
                    String poppedWord = wordStack.pop();  // get the top word from the stack

                    // remove the period if it’s there
                    if (poppedWord.endsWith(".")) {
                        poppedWord = poppedWord.substring(0, poppedWord.length() - 1);
                    }
                }

                if (isFirstWord) {
                    String firstLetter = poppedWord.substring(0, 1).toUpperCase();
                    String restOfWord = poppedWord.substring(1).toLowerCase();
                    reversedPart += firstLetter + restOfWord;
                    isFirstWord = false;
                }
                else {
                    String lowerCaseWord = poppedWord.toLowerCase();
                    reversedPart += " " + lowerCaseWord;
                }
                reversedPart += ".";
                finalSentence += reversedPart + " ";
            }
            return finalSentence.trim();
        }
    }
}
