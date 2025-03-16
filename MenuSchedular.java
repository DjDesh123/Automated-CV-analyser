import java.util.Scanner;
public class MenuSchedular{
    private Scanner sc;

    public MenuSchedular(Scanner sc){
        this.sc =sc;
    }

    public void DisplayMenu() {
        System.out.println("\nWelcome to the Automated CV Analyzer!");
        System.out.println("1. Log in");
        System.out.println("2. Sign up");
        System.out.print("Enter your choice (1 or 2): ");
    }

    public int getUserChoice() {
        if (sc.hasNextInt()) {
            int choice = sc.nextInt();
            sc.nextLine(); // Consume newline
            return choice;
        } else {
            sc.nextLine(); // Consume invalid input
            return -1; // Return invalid choice
        }

    }

}
