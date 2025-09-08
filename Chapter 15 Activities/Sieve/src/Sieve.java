import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;  
import java.util.*;


/**
 * A program that implements the sieve of Eratosthenes.
*/
public class Sieve
{
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        System.out.println("Compute primes up to which integer?");
        int n = in.nextInt();
        // I need to insert all numbers from 2 to n into a list
        // Then I need to repeatedly remove multiples of each prime
        List<Integer> numbers = new ArrayList<>();
        for (int i=2; i<=n;i++){
            numbers.add(i);
        }
        System.out.println(numbers);
        int v = 2;
        while (v <= n){
            for (int i = 0; i < numbers.size(); i++){
                if (numbers.get(i) % v == 0)
                    numbers.remove(i);
            }
            v++;
            System.out.println(numbers);
        }
        
        
        
        // Your work goes here
        







    }
}
