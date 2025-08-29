import java.util.LinkedList;
import java.util.ListIterator;

/**
 * This class supplies a utility method to reverse the entries in a linked list.
*/
public class ListUtil
{
    /**
     * Reverses the elements in a linked list
     *
     * @param strings the linked list to reverse
    */
    public static void reverse(LinkedList<String> strings)
    {
        int w = strings.size();
        LinkedList<String> reversestrings = new LinkedList();
        while (w!=0){
                reversestrings.add(strings.get(w-1));
                w--;
        }
        System.out.println(reversestrings);
    }
}