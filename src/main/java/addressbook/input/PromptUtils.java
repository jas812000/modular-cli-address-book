package addressbook.input;

import java.util.Scanner;

/**
 * Provides shared console-input operations used throughout the application.
 *
 * <p>This class owns the application's single {@link Scanner} for
 * {@code System.in}, preventing competing scanners from reading from the
 * same input stream.</p>
 */
public final class PromptUtils {

    private static final Scanner SCANNER = new Scanner(System.in);

    /**
     * Prevents instantiation because this class provides only static utility methods.
     */
    private PromptUtils() {
    }

    /**
     * Reads the next line of console input.
     *
     * @return the entered line
     */
    public static String readLine() {
        return SCANNER.nextLine();
    }

    /**
     * Reads input for an interactive operation and cancels the operation
     * when the user enters {@code exit}.
     *
     * @return the entered line
     * @throws InputCancelledException if the user enters {@code exit}
     */
    public static String readOperationLine() {
        String input = readLine();

        if (input.trim().equalsIgnoreCase("exit")) {
            throw new InputCancelledException();
        }

        return input;
    }

    /**
     * Prompts the user for a yes-or-no response until a valid response
     * is entered.
     *
     * @param message prompt displayed to the user
     * @return {@code true} for yes; {@code false} for no
     * @throws InputCancelledException if the user enters {@code exit}
     */
    public static boolean promptYesNo(String message) {
        while (true) {
            System.out.print(message);

            String response = readOperationLine().trim().toLowerCase();

            if (response.equals("y") || response.equals("yes")) {
                return true;
            }

            if (response.equals("n") || response.equals("no")) {
                return false;
            }

            System.out.println("Please enter y or n.");
        }
    }

    /**
     * Parses a one-based numeric selection and converts it to a zero-based
     * index.
     *
     * @param input entered selection
     * @param itemCount number of available items
     * @return zero-based index, or {@code -1} if the selection is invalid
     */
    public static int parseSelection(String input, int itemCount) {
        try {
            int index = Integer.parseInt(input.trim()) - 1;
            return index >= 0 && index < itemCount ? index : -1;
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
