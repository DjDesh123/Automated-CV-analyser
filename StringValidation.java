import java.util.Scanner;

public class StringValidation {

    public static String ValidateString(String Prompt) {
        Scanner sc = new Scanner(System.in);
        String ValidInput;

        while (true) {
            System.out.println(Prompt);
            ValidInput = sc.nextLine();

            // the regex is basically just making sure that its only Words and numbers
            if (ValidInput.matches("[a-zA-Z0-9]{3,15}")) {
                // This will return the Valid input
                return ValidInput;
            } else {
                System.out.println("Invalid input! Please enter only letters (3-15 characters).");
            }


        }
    }
}
