import java.util.Scanner;
public class IntValidation {
    // just a simple in checker its just makes the code more reusable and prevents duplication of code
    public static int ValidateInt(String Prompt) {
        Scanner sc = new Scanner(System.in);

        System.out.println(Prompt);

        while (true) {

            // in the scenario that an integer is not given
            if (!sc.hasNextInt()) {
                System.out.println("Invalid Input: Must be an integer");
            }
            else{
                return sc.nextInt();
            }


        }
    }





}
