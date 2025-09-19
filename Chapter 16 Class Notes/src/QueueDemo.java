import java.util.Queue;
import java.util.*;
import java.util.LinkedList;

public class QueueDemo
{
    public static void main(String[] args)
    {
        // Create a print queue (using a linked list)
        Queue<String> jobs = new LinkedList<>();

        // Add some print jobs
        jobs.add("Connor: Chatper 2 Expense Report");
        jobs.add("Connor: Recipe for banana bread");
        jobs.add("Katherine: Top Secret Document");

        System.out.println("Printing : " + jobs.remove());

        // Add some more print jobs
        jobs.add("Vardhan: Grocery List");
        jobs.add("Katherine: Really top secret document");
        jobs.add("Katherine: Can I get fired from this?");
        
        System.out.println("Printing " + jobs.remove());
        System.out.println("Printing " + jobs.remove());


        jobs.add("Boss: Ketherine Termination Letter");

        // Print the rest of the jobs in the queue
        while (!jobs.isEmpty())
        {
            System.out.println("Printing " + jobs.remove());
        }
        /*
        CircularArrayQueue queue = new CircularArrayQueue();

        queue.add("Tom");
        queue.add("Diana");
        queue.add("Harry");
        System.out.println(queue.remove()); // remove Tom
        queue.add("Romeo");
        System.out.println(queue.remove()); // remove Diana
        queue.add("Juliet");
        queue.add("Maria");

        while(!queue.empty())
        {
            System.out.println(queue.remove());
        }
        
        System.out.println("Expected output: Tom, Diana, Harry, Romeo, Juliet, Maria");
        */
    }
}
