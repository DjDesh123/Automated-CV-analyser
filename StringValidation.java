import java.util.Scanner;

public class StringValidation {

    public static String ValidateString(String Prompt,Scanner sc) {
        String ValidInput;

        while (true) {
            System.out.println(Prompt);
            ValidInput = sc.nextLine();

            // the regex is basically just making sure that its only Words and numbers
            if (ValidInput.matches("^[A-Za-z0-9 ]+$")) {
                // This will return the Valid input
                return ValidInput;
            } else {
                System.out.println("Invalid input! Please do not enter special characters .");
            }


        }
    }
}
