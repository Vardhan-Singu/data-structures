import java.util.PriorityQueue;
import java.util.Scanner;
/**
 * Implement a to do list. Tasks have a priority between 
 * 1 and 9 (with 1 being most urgent), and a description.
 * When the user enters the command 'add priority description', 
 * the program adds a new task. When the user enters next, 
 * the program removes and prints the most urgent task. 
 * The quit command quits the program. 
 * Use a priority queue in your solution.
*/
public class ToDoList
{
    // Instance variable(s)
    private PriorityQueue<Task> tasks;

    /**
     * Constructor
    */
    public ToDoList()
    {
        // Complete this
        tasks = new PriorityQueue<>();
    }

    /**
     * Display a menu and accepts commands.
     */
    public void run()
    {
        String option = "";
        System.out.println("To Do List - Please enter an option");
        System.out.println("     add priority description (add a new task)");
        System.out.println("     next (remove and print most urgent task)");
        System.out.println("     quit (exit this program)");
        System.out.println();
        
        Scanner scanner = new Scanner(System.in);
        
        do
        {
            System.out.print("> ");
            option = scanner.nextLine();
            if (option.startsWith("add"))
            {
                addTask(option);
            } else if (option.equals("next"))
            {
                nextTask();
            }
        } 
        while (! option.equals("quit"));
    }
    
    /**
     * Parse the add option line.
     *
     * @param optionStr the option line
    */
    public void addTask(String optionStr)
    {
        // Complete this method
        // We are splitting this to 3 parts "add", "priority", "description"
        String[] parts = optionStr.split(" ", 3);
        if (parts.length < 3) {
            System.out.println("The priority must be an integer between 1 and 9.");
            return;
        }

        try 
        {
            int priority = Integer.parseInt(parts[1]);
            if (priority < 1 || priority > 9)
            {
                System.out.println("The priority must be an integer between 1 and 9.");
                return;
            }
            String description = parts[2];
            tasks.add(new Task(priority, description));
        }
        catch (NumberFormatException e) // I have no idea what this means but it makes it work I will look into this
        {
            System.out.println("The priority must be an integer between 1 and 9.");
        }
    }

    /**
     * Get the next highest priority task and
     * display the description to the user.
    */
    public void nextTask()
    {
        Task next = tasks.poll();
        
        // Complete this method
        if (next == null)
        {
            System.out.println("There are no tasks in the list.");
        }
        else
        {
            System.out.println(next.getDescription());
        }
    }
}