import java.util.NoSuchElementException;
import java.util.*;
import java.util.ListIterator;

/**
 * A linked list is a sequence of nodes with efficient
 * element insertion and removal. This class
 * contains a subset of the methods of the standard
 * java.util.LinkedList class.
*/
public class LinkedList
{
    // First refers to the first Node in the list
    // If the list is emptuy, first will be null
    private Node first; 

    /**
        Constructs an empty linked list.
    */
    public LinkedList(){
        this.first = null;
    }
    // Can AI gain sentience and become self aware? Answer: No, because I am a program created by humans and do not possess consciousness or self-awareness.


    /**
        Returns the first element in the linked list.
        @return the first element in the linked list
    */
    public Object getFirst(){
        if (this.first == null){
            throw new NoSuchElementException(); // throw new NoSuchElementException
        }
        return this.first.data;
    }




    /**
        Removes the first element in the linked list.
        @return the removed element
    */
    public Object removeFirst(){
        if (this.first == null){
            throw new NoSuchElementException();
        }
        Object element = this.first.data;
        this.first = this.first.next;
        return element;
    }




    /**
        Adds an element to the front of the linked list.
        @param element the element to add
    */
    public void addFirst(Object element){
        Node newNode = new Node();
        newNode.data = element;
        newNode.next = this.first;
        this.first = newNode;
    }





    /**
        Returns an iterator for iterating through this list.
        @return an iterator for iterating through this list
    */
    public LinkedListIterator iterator(){
        return new LinkedListIterator();
    }

    public String toString(){
        ListIterator listIterator = this.iterator();
        String allElements ="[";
        while (listIterator.hasNext()){
            allElements += listIterator.next() + ", ";
        }

        return allElements + "]";
    }



    //Class Node
    // Node is static becayse it does NOT need to access everything in LinkedList
    // The object will store information, not interact
    static class Node {
        public Node next;
        public Object data; // data can be any object

    }


    class LinkedListIterator implements ListIterator
    {
      //private data
      private Node position;
      private Node previous;
      private boolean isAfterNext;


        /**
            Constructs an iterator that points to the front
            of the linked list.
        */
        public LinkedListIterator(){
            this.position = null;
            this.previous = null;
            this.isAfterNext = false;
        }

        /**
            Moves the iterator past the next element.
            @return the traversed element
        */
        public Object next(){
            if (!hasNext()){
                throw new NoSuchElementException();
            }

            previous = position;

            if (position == null){
                position = first;
            }
            else{
                position = position.next;
            }
            
            isAfterNext = true;

            return position.data;
        }




        /**
            Tests if there is an element after the iterator position.
            @return true if there is an element after the iterator position
        */
        public boolean hasNext(){
            // Check if the list is empty
            if (position == null){
                return first !=null;
            }

            // The iterator has moved so check the next node
            return position.next != null;
        }


        /**
            Adds an element before the iterator position
            and moves the iterator past the inserted element.
            @param element the element to add
        */
        public void add(Object element){
            // Check if the iterator is at the beginning of the list
            if (position == null){
                addFirst(element);
                position = first;
            }
            else{
                Node newNode = new Node();
                newNode.data = element;
                newNode.next = position.next;

                // Set tge bext element of the CURRENT position to point to our new node
                position.next = newNode;
                position = newNode;
            }
            isAfterNext = false;
        }





        /**
            Removes the last traversed element. This method may
            only be called after a call to the next() method.
        */
        public void remove(){
            if (!isAfterNext){
                throw new IllegalStateException();
            }

            // Check if the iterator is at the beginning of the list
            if (position == first){
                removeFirst();
                position = null;
            }
            else{
                previous.next = position.next;
                position = previous;
            }
            isAfterNext = false;
        }






        /**
            Sets the last traversed element to a different value.
            @param element the element to set
        */
        public void set(Object element){
            if (!isAfterNext){
                throw new IllegalStateException();
            }
            position.data = element;
            // We don't have to reset isAfterNext because the strcture of the list hasn't changed

        }



    }//LinkedListIterator
}//LinkedList
