/**
* Reverses Integer values and states the lenght of a string
*
* @author  Van Nguyen
* @version 1.0
* @since   2024-05-05
*/

import java.io.*;
import java.util.*;

public class StrLengthAndReverseInt {
    // Method to reverse an integer
    public static int ReverseInt(int someNumber) {
        return reverseHelper(someNumber, 0);
    }

    // Recursive helper method to reverse an integer
    private static int reverseHelper(int number, int reversed) {
        if (number == 0) {
            return reversed;
        } else {
            return reverseHelper(number / 10, reversed * 10 + number % 10);
        }
    }

    // Method to calculate the length of a string recursively
    public static int StrLength(String someString) {
        if (someString.equals("")) {
            return 0;
        } else {
            return 1 + StrLength(someString.substring(1));
        }
    }

    public static void main(String[] args) {
        Scanner console = new Scanner(System.in); // Scanner for console input
        int choice = 0; // Variable to store user choice
        
        // Loop to ensure valid input for operation choice
        while (choice != 1 && choice != 2) {
            System.out.println("Choose an operation:");
            System.out.println("1: Reverse Integer");
            System.out.println("2: Find String Length");

            // Check if user input is an integer
            if (console.hasNextInt()) {
                choice = console.nextInt(); // Read integer choice
                if (choice != 1 && choice != 2) {
                    System.out.println("Invalid choice. Please enter 1 or 2.");
                }
            } else {
                System.out.println("Invalid input. Please enter an integer.");
                console.next(); // Consume the non-integer input
            }
        }

        // Try-Catch block to handle file input/output exceptions
        try {
            // File paths
            String in = "./input.txt";
            String out = "./output.txt";
            File input = new File(in);
            File output = new File(out);

            // Scanner for file input and writer for file output
            Scanner scanner = new Scanner(input);
            FileWriter writer = new FileWriter(output);

            // Reading from the input file
            while (scanner.hasNextLine()) {
                String fileLine = scanner.nextLine(); // Read line
                
                // Skip empty lines
                if (fileLine.trim().isEmpty()) {
                    continue;
                }

                switch (choice) {
                    case 1: // Option 1: Reverse Integer
                        try {
                            int fileInteger = Integer.parseInt(fileLine);
                            int reversedInt = ReverseInt(fileInteger);
                            writer.write("Original Int: " + fileLine + "\n");
                            writer.write("Reversed Int: " + reversedInt + "\n\n");
                            System.out.println("Original Int: " + fileLine);
                            System.out.println("Reversed Int: " + reversedInt);
                            System.out.println();
                        } catch (NumberFormatException e) {
                            writer.write("Invalid input: " + fileLine + " is not an integer.\n\n");
                            System.out.println("Invalid input: " + fileLine + " is not an integer.");
                            System.out.println();
                        }
                        break;
                    case 2: // Option 2: Find String Length
                        int length = StrLength(fileLine);
                        writer.write("Input String: " + fileLine + "\n");
                        writer.write("String Length: " + length + "\n\n");
                        System.out.println("Input String: " + fileLine);
                        System.out.println("String Length: " + length);
                        System.out.println();
                        break;
                }
            }

            // Close resources to prevent leaks
            scanner.close();
            writer.close();
            console.close();

        } catch (IOException e) {
            System.out.println("File could not be found. Please fix the file path.");
        }
    }
}

