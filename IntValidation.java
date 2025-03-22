import java.util.Scanner;
public class IntValidation {
    // just a simple in checker its just makes the code more reusable and prevents duplication of code
    public static int ValidateInt(String Prompt,Scanner sc ) {
        do{
            System.out.println(Prompt);

            if (!sc.hasNextInt()) {
                System.out.println("Invalid Input: Must be an integer.");
                // Clear the invalid input
                sc.next();
            }
        }while(!sc.hasNextInt());

        int UserInput = sc.nextInt();

        // Consume the newline character
        sc.nextLine();

        // Return the valid integer
        return UserInput;
    }





}
