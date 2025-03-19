// https://www.quora.com/How-do-I-clear-the-console-screen-in-Java is where i got this code do want to make it more origrnal tho
public class ScreenManager {

    // because you OS isnt gonna change nor do we want it to so its static
    private static final String Os = System.getProperty("os.name").toLowerCase();;

    // this stops people making objects as its uneeded
    private ScreenManager() {
        ;
    }


    // this will actually clear the screen
    public static void ClearScreen(){
        try{
            if(Os.contains("win")){
                // how this works is by telling the windows interpeter "cmd" to run cls and then end
                // we use inheritIO to redirect any input or output from the command prompt to the java program whislt we use .start to start the command prompt with the cls command and waitfor() which waits for the external process to happen before processing
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            }
            else{
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (Exception e) {
            System.err.println("Error clearing screen: " + e.getMessage());  // Handle any exceptions (e.g., IO or InterruptedException)

        }

    }
}


