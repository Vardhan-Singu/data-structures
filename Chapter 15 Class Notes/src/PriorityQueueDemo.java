import java.util.PriorityQueue;
import java.util.Queue;


/**
 * This program demonstrates a priority queue of to-do items. The
 * most important to-do items are removed first.
*/
public class PriorityQueueDemo
{
    public static void main(String[] args)
    {
        Queue<String> students = new PriorityQueue<>();
        students.add("Neel");
        students.add("Jonathan");
        students.add("Cam");
        students.add("Kaitlyn");
        students.add("Dylan");
        // The next highest priority object is moved to the front of the queue
        // When the head of the queue is removed
        while (students.size() > 0){
            System.out.println(students.remove());
        }


        // Create a to-do list
        // The WorkOrder class has a priority and description
        Queue<WorkOrder> toDo = new priorityQueue<>();

        // Lower priority is more important
        toDo.add(new WorkOrder(3, "Water Plants"));
        toDo.add(new WorkOrder(2, "Make Dinner"));
        toDo.add(new WorkOrder(9, "Play Video Games"));
        toDo.add(new WorkOrder(1, "Study for the Chapter 15 Test"));

        // Objects are NOT stored in priority order
        System.out.println("To-Do List: " + toDo);

        // Objects will be removed in priority order
        while (!toDo.isEmpty()){
            System.out.println(toDo.remove());
        }
    }
}
