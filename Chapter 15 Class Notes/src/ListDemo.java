import java.util.LinkedList;
import java.util.ListIterator;

/**
 * This program demonstrates the LinkedList class
 * and ListIterator class.
*/
public class ListDemo
{
    public static void main(String[] args)
    {
        /* The addLast method can be used to populate a list. */
        LinkedList<String> staff = new LinkedList<>();
        staff.addLast("Tony");
        staff.addLast("Natasha");
        staff.addLast("Steve");
        staff.addLast("Tyrone");

        System.out.println("Here is the staff list:");
        System.out.println(staff);

        // The list is currently: TNST
        /*
         * The lsitIterator method creates a new list iterator.
         * that is positioned at the head of the list.
         * The | is used ti reoresent the iterator's position.
         */
        ListIterator<String> iterator = staff.listIterator(); //|TNST

        /* The next method advances the iterator OVER the next element in the list.
         */
        iterator.next(); // T|NST
        // The next method also returns the element that the iterator passes over.
        String avenger = iterator.next();
        System.out.println(avenger + " is an Avenger.");

        
        
    }
}
