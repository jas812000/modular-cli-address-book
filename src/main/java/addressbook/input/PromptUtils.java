package addressbook.input;

import java.util.Scanner;

/**
 * Shared utility class for common user prompts.
 * Centralizes repeated prompt logic to keep input classes simple.
 */
public class PromptUtils {

    private static final Scanner scanner = new Scanner(System.in);

    public static boolean promptYesNo(String message) {
        System.out.print(message);
        String response = scanner.nextLine().trim().toLowerCase();
        return response.equals("y") || response.equals("yes");
    }
}

