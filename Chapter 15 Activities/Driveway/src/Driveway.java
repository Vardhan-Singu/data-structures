import java.util.Stack;
import java.util.Scanner;

/**
 * Class for simulating a driveway and a street, using stacks
 * of cars with license plate numbers as representation.
*/
public class Driveway
{
    /**
      * Stack representing the cars in the driveway.
    */
    private Stack<Integer> driveway;
    /**
      * Stack representing the cars in the street.
    */
    private Stack<Integer> street;

    /**
      * Constructor.
    */
    public Driveway()
    {
        // Complete the constructor
        driveway = new Stack<>();
        street = new Stack<>();
    }

    /**
      * Add the given license plate to the driveway.
      *
      * @param licensePlate number of license plate.
    */
    public void add(int licensePlate)
    {
        // Complete this method
        driveway.push(licensePlate); //so push basically means add to the top of the stack
        print();
    }

    /**
      * Remove the given license plate from the driveway.
      *
      * @param licensePlate number of license plate.
    */
    public void remove(int licensePlate)
    {
        // Complete this method
        while (!driveway.isEmpty()){
          int topCar = driveway.peek(); //looks at the top car without removing it
          if (topCar == licensePlate){
            driveway.pop(); //removes the top car
            break;
          }
          else{
            street.push(driveway.pop()); //move the car to the street
          }
        }
        while (!street.isEmpty()){
          driveway.push(street.pop()); //move the cars back to the driveway
        }
      }

    /**
      * Prints the driveway and street details to the screen.
    */
    public void print()
    {
        System.out.println("In Driveway, starting at first in (one license plate per line):");
        // Print the cars in the driveway here
        for (int car : driveway) {
          System.out.println(car);
        }

        System.out.println("In Street, starting at first in (one license plate per line):");
        // Print the cars in the street here
        for (int car : street) {
          System.out.println(car);
        }

    }
}
