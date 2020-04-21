/*
FourTupleTransformation.java 
Created by: Lucas Noritomi-Hartwig
Created on: April 17, 2020
Last edited on: April 20, 2020
Starting with a four-tuple of nonnegative integers (a,b,c,d), this program 
repeatedly applies this rule: (a,b,c,d) → (|a−b|,|b−c|,|c−d|,|d−a|).
That is, it replaces a by |a−b|, b by |b−c|, c by |c−d|, and d by |d−a|.
The program also prints the number of steps until either a or b or c or d reach 0.
*/
package fourtupletransformation;

import static java.lang.Math.abs;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class FourTupleTransformation {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Prompting user for input four-tuple (initially as String in order to separate integers)
        String input = JOptionPane.showInputDialog("Enter a four-tuple of non-negative integers:");

        // Separating numbers
        String[] numbersString = input.split(" ");
        
        // Checking input size
        if (numbersString.length == 4) {
            ArrayList<Integer> numbers = new ArrayList();
            try {
                for(String stringValue : numbersString) {
                    // Converting String Array to Integers and storing them in numbers ArrayList
                    numbers.add(Integer.parseInt(stringValue));
                }
                if (numbers.get(0) >= 0 && numbers.get(1) >= 0 && numbers.get(2) >= 0 &&
                        numbers.get(3) >= 0) {
                    numbers.add(0); // Adding step counter to numbers ArrayList
                    transform(numbers); // Calling method to transform four-tuple

                    // Creating substring for proper output format
                    String output = numbers.toString().substring(1, numbers.toString().length() - 4);

                    // Output
                    System.out.println("Number of recursion = " + numbers.get(4) + "(" + output + ")");
                } else { // Exception in case not all numbers are non-negative
                    System.out.println("Please input a four-tuple of non-negative integers."); 
                }
            } catch(NumberFormatException nfe) { // Exception in case not all separations are parsable
                System.out.println("Please input a four-tuple of non-negative integers.");
            }          
        } else { // Exception in case there were not four separate elements inputted
            System.out.println("Please input a four-tuple of non-negative integers.");
        }
    }
    
    public static ArrayList <Integer> transform(ArrayList<Integer> numbers) {
        // Initializing fixed Integers for comparison and manipulation
        int a = numbers.get(0);
        int b = numbers.get(1);
        int c = numbers.get(2);
        int d = numbers.get(3);
        // Recursive function transforming the four-tuple unit at least one element is 0
        if (a == 0 || b == 0 || c == 0 || d == 0) { // Base case where any, if not all numbers are 0
            return numbers;
        } else { // Case where none of the numbers are 0
            numbers.set(0, abs(a - b));
            numbers.set(1, abs(b - c));
            numbers.set(2, abs(c - d));
            numbers.set(3, abs(d - a));
            numbers.set(4, numbers.get(4) + 1); // Incrementing number of steps
            return transform(numbers);
        }
    }
}
