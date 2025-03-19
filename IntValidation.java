import java.util.Scanner;
public class IntValidation {
    // just a simple in checker its just makes the code more reusable and prevents duplication of code
    public static int ValidateInt(String Prompt,Scanner sc ) {
        while (true) {
            System.out.println(Prompt);

            if (sc.hasNextInt()) {
                int userInput = sc.nextInt();
                sc.nextLine(); // Consume the newline character
                return userInput; // Return the valid integer
            } else {
                System.out.println("Invalid Input: Must be an integer.");
                sc.next(); // Clear the invalid input
            }
        }
    }





}
