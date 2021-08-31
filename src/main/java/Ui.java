import java.util.Scanner;

/**
 * This is a Ui class, which encapsulates all interactions with the user.
 */
public class Ui {
    private static final String LINE = "\n" +
            "————————————————————————————————————————————————————————————\n";

    /**
     * Retrieves input with a Scanner object.
     * @return The input string read by a Scanner object.
     */
    public String getInput() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    /**
     * Prints the starting display to the user.
     */
    public void printStartDisplay() {
        print(logo());
        print(this.getGreeting());
    }

    /**
     * Prints the ending display to the user.
     */
    public void printEndDisplay() {
        print(this.getBye());
    }

    /**
     * Prints string between 2 lines.
     * @param s The string to be printed.
     */
    public void print(String s) {
        System.out.print(LINE + s + LINE);
    }

    public String logo() {
        String logo = "\t\t\t\t \t—————      —————\n"
                + "\t\t\t\tM\t|      \\/      |\tM\n"
                + "\t\t\t\tO\t|              |\tO\n"
                + "\t\t\t\tR\t|    |\\  /|    |\tR\n"
                + "\t\t\t\tG\t|    | \\/ |    |\tG\n"
                + "\t\t\t\tA\t|    |    |    |\tA\n"
                + "\t\t\t\tN\t|    |    |    |\tN\n"
                + "\t\t\t\t \t ————      ————";
        return logo;
    }


    public String getGreeting() {
        String greeting = "Hello, my name is Morgan, and I'm your personal task assistant.\n"
                + " What can I do for you today?";
        return greeting;
    }

    public String getBye() {
        String bye = "Bye. Hope to see you again soon.";
        return bye;
    }
}
