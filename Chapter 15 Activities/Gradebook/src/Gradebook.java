import java.util.Scanner;
import java.util.Map;
import java.util.TreeMap;

/**
 * A program to add, remove, modify or print
 * student names and grades.
*/
public class Gradebook
{
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);

        Map<String, String> grades = new TreeMap<>();

        boolean done = false;
        while(!done)
        {
            System.out.println("A)dd R)emove M)odify P)rint Q)uit");
            String input = in.next().toUpperCase();
            String grade = in.nextLine().toUpperCase();
            if (input.equals("Q")) {
                done = true;
            }
            else if (input.equals("A")) {
                System.out.print("Enter student name: ");
                input = in.nextLine();
                System.out.print("Enter student grade: ");
                grade = in.nextLine();
                grades.put(input, grade);
            }
            else if (input.equals("R")) {
                System.out.print("Enter student name: ");
                input = in.nextLine();
                grades.remove(input);
            }
            else if (input.equals("M")) {
                System.out.print("Enter student name: ");
                input = in.nextLine();
                System.out.print("Enter student's new grade: ");
                grade = in.nextLine();
                grades.put(input, grade);
            }
            else if (input.equalsIgnoreCase("P")) {
                for(String key : grades.keySet()) {
                    System.out.println(key + ": " + grades.get(key));
                }
            }
            else {
                done = true;
            }
        }
    }
}