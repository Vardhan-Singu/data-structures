import java.util.*;

public class StackDemo
{
    public static void main(String[] args)
    {
        Stack<String> commands = new Stack<>();
        commands.push("Insert: 'Hello'");
        commands.push("Insert: ','");
        commands.push("Insert: ' '");
        commands.push("Insert: 'World'");
        commands.push("Insert: '?'");
        commands.push("Delete: '?'");
        commands.push("Insert: '!'");

        // Print the stack, the top of the stack is the far right
        System.out.println(commands);

        // Simulate the user pressing undo 4 times
        for (int i = 0; i < 4; i++)
        {
            System.out.println("Undo "+commands.pop());
        }

        // Confirm 4 commands have been removed
        System.out.println(commands);

        /*
        LinkedListStack stack = new LinkedListStack();

        stack.push("Tom");
        stack.push("Diana");
        stack.push("Harry");

        while(!stack.empty())
        {
            System.out.println(stack.pop());
        }

        System.out.println("Expected: Harry Diana Tom");
        */
    }
}
