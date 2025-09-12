import java.util.*;
import java.io.*;
import java.util.TreeMap;
import java.io.File;



/**
 * Read all words from a file and add them to a map
 * whose keys are the first letters of the words and
 * whose values are sets of words that start with
 * that same letter.
 * Then print out the word sets in alphabetical order.
 * Use the Java 8 merge() feature.
*/
public class FirstLetterMap
{
    public static void main(String[] args)
    {
        String filename = "src/test1.txt";

        try (Scanner in = new Scanner(new File(filename)))
        {

            // Create your map here
            Map<Character, Set<String>> map = new TreeMap<>();

            

            while (in.hasNext())
            {
                String word = clean(in.next());
                Character c = word.charAt(0);
                /*
                Integer count  = map.get(c);
                if (count == null) {
                    count = 0;
                }
                else {
                    count++;
                }
                map.put(c, count);
                */
                // Update the map here
                // Use the Java 8 merge method
                map.merge(c, word, (oldValue, notPresentValue) -> oldValue + notPresentValue);
            }

            // Print the map here in this form
            // a: [a, able, aardvark]
            
        } catch (FileNotFoundException e)
        {
            System.out.println("Cannot open: " + filename);
        }
    }

    public static String clean(String s)
    {
        String r = "";
        for (int i = 0; i < s.length(); i++)
        {
            char c = s.charAt(i);
            if (Character.isLetter(c))
            {
                r = r + c;
            }
        }
        return r.toLowerCase();
    }

}
