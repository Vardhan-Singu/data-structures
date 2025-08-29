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
        
        /* The add method inserts an element at the iterator position
         * The iterator is positioned after the element that was added
         */
        iterator.add("Clint"); // TN|ST
        iterator.add("Bruce");
        System.out.println("Here is the updated staff list:");
        System.out.println(staff);
        
        /*The remove method can ONLY be called after calling next or previous */
        //iterator.remove(); this line would cause an IllegalStateException
        // The remove method removes the element returned after calling next or previous
        iterator.next(); // TNSCB|T
        iterator.remove(); // TNSC|T
        System.out.println("Here is the updated staff list:");
        System.out.println(staff);

        /*The set method updates the element returned by 
         * the last call to next ore previous
         */
        iterator.previous(); // TNS|CT
        iterator.set("Thor"); // TNS|CT becomes TNS|TT

        /* The hasNext methos is used to determine if there is a next node after the iterator.
         * The hasNext is often used in the condition of a while loop.
         */
        iterator = staff.listIterator(); // |TNST
        while (iterator.hasNext())
        {
            String n = iterator.next();
            if (n.equals("Natasha")) {
                iterator.remove(); // T|CTS
            }
        } //TCTS|

        /* Enchanced for loops work with linked lists */
        for (String n : staff)
        {
            System.out.print(n + " ");
        }
        System.out.println();

        while (iterator.hasNext()){
            String n = iterator.next();
            if (n.equals("Tony")){
                //staff.remove("Tony"); don't do staff.remove while iterating results in ConcurrentModificationException
                iterator.remove();
            }
        }

        for (String n : staff)
        {
            if (n.equals("Tyrone")){
                staff.add("T'Challa");
            }
        }

        System.out.println(staff);
    }
}
