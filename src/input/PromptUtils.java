package input;

import java.util.Scanner;

/**
 * Utility class for common user prompts.
 */
public class PromptUtils {

    private static final Scanner scanner = new Scanner(System.in);

    public static boolean promptYesNo(String message) {
        System.out.print(message);
        String response = scanner.nextLine().trim().toLowerCase();
        return response.equals("y") || response.equals("yes");
    }
}

