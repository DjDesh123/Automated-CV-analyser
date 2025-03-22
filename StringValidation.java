import java.util.Scanner;

public class StringValidation {

    public static String ValidateString(String Prompt,Scanner sc) {
        String ValidInput;

        do{
            System.out.println(Prompt);
            ValidInput = sc.nextLine().trim();

            // the regex is basically just making sure that its only Words and numbers
            if (!ValidInput.matches("^[A-Za-z0-9 ]+$")) {
                System.out.println("Invalid input! Please do not enter special characters .");
            }


        }while(!ValidInput.matches("^[A-Za-z0-9 ]+$"));

        return ValidInput;
    }
}
