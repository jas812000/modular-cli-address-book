package input;

import model.EmailAddress;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Prompts for multiple email addresses.
 */
public class EmailAddressPrompter {

    private static final Scanner scanner = new Scanner(System.in);

    public static List<EmailAddress> prompt() {
        List<EmailAddress> emails = new ArrayList<>();
        do {
            System.out.print("Enter email label (e.g. Personal, Work): ");
            String label = scanner.nextLine();

            System.out.print("Email address: ");
            String email = scanner.nextLine();

            emails.add(new EmailAddress(label, email));

        } while (PromptUtils.promptYesNo("Add another email address? (y/n): "));

        return emails;
    }
}
