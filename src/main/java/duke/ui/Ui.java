package duke.ui;

import java.util.Scanner;

/**
 * Ui that deals with the interactions and responses with the user.
 */
public class Ui {
    private static final String lineDivider = "____________________________________________________________\n";
    private static final String greet = lineDivider + "Hello! I'm Mum!\nWhat can I do for you?\n"
            + "Type \"commands\" to get a list of all commands.\n" + lineDivider;
    private static final String goodBye = lineDivider + "Bye. Hope to see you again soon!\n" + lineDivider;
    private static final String addedTask = lineDivider + "Got it. I've added this task:\n"
            + "   %s\nNow you have %d tasks in the list.\n" + lineDivider;
    private static final String deleteTask = lineDivider + "Noted. I've removed this task:\n"
            + "%s\nNow you have %d tasks in the list\n" + lineDivider;
    private static final String markedTask = lineDivider + "Nice! I've marked this task as done:\n"
            + "[%s] %s\n" + lineDivider;
    private static final String unmarkedTask = lineDivider + "Ok, I've marked this task as not done yet:\n"
            + "[%s] %s\n" + lineDivider;

    /**
     * Empty constructor to initialise this ui.
     */
    public Ui() {

    }

    /**
     * Read user's input by line.
     *
     * @return return the input as a string.
     */
    public String readCommand() {
        Scanner cmd = new Scanner(System.in);
        while (cmd.hasNext()) {
            return cmd.nextLine();
        }
        return null;
    }

    /**
     * Print out error message for invalid filePath.
     */
    public static void showLoadingError() {
        System.out.println("Not a valid file. It cannot be loaded.");
    }

    /**
     * Greet the user when bot launches.
     */
    public static void showWelcome() {
        System.out.print(greet);
    }

    /**
     * Greet the user goodbye and stop running the bot.
     */
    public static void showGoodBye() {
        System.out.print(goodBye);
    }

    /**
     * Respond to user with the specific task that is just added into the list.
     *
     * @param output description of the task
     * @param n the number of tasks in list
     */
    public static void showAddResponse(String output, int n) {
        System.out.printf(addedTask, output, n);
    }

    /**
     * Respond to user about the task deleted from the list.
     *
     * @param output description of the task
     * @param n the number of tasks in list
     */
    public static void showDeleteResponse(String output, int n) {
        System.out.printf(deleteTask, output, n);
    }

    /**
     * Respond to user the task that is marked X in list.
     *
     * @param status the mark: X on the task
     * @param output description of the task
     */
    public static void showMarkRes(String status, String output) {
        System.out.printf(markedTask, status, output);
    }

    /**
     * Respond to user the task that is not marked with X in list.
     *
     * @param status unmark on the task
     * @param output description of the task
     */
    public static void showUnmarkRes(String status, String output) {
        System.out.printf(unmarkedTask, status, output);
    }

    /**
     * LineDivider for aesthetic purposes.
     * @return a string of line
     */
    public static String getLineDivider() {
        return lineDivider;
    }
}
