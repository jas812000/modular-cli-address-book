package addressbook.input;

import addressbook.model.EmailAddress;

import java.util.ArrayList;
import java.util.List;

/**
 * Collects one or more email addresses for a contact.
 */
public final class EmailAddressPrompter {

    /**
     * Prevents instantiation because this class provides only static utility methods.
     */
    private EmailAddressPrompter() {
    }

    /**
     * Prompts the user for email addresses until no additional address is requested.
     *
     * @return entered email addresses
     */
    public static List<EmailAddress> prompt() {
        List<EmailAddress> emails = new ArrayList<>();

        do {
            emails.add(promptForEmailAddress());
        } while (PromptUtils.promptYesNo("Add another email address? (y/n): "));

        return emails;
    }

    /**
     * Prompts for a single labeled email address.
     *
     * @return entered email address
     */
    public static EmailAddress promptForEmailAddress() {
        String label = promptForLabel();

        System.out.print("Email address: ");
        String email = PromptUtils.readOperationLine().trim();

        return new EmailAddress(label, email);
    }

    /**
     * Prompts for an email address label.
     *
     * @return selected or custom email address label
     */
    private static String promptForLabel() {
        while (true) {
            System.out.println("Email type:");
            System.out.println("1. Personal");
            System.out.println("2. Work");
            System.out.println("3. Other");
            System.out.print("Select an option: ");

            int selection = PromptUtils.parseSelection(
                    PromptUtils.readOperationLine(),
                    3
            );

            if (selection == 0) {
                return "Personal";
            }

            if (selection == 1) {
                return "Work";
            }

            if (selection == 2) {
                return promptForCustomLabel("Custom email type: ");
            }

            System.out.println("Please select a valid option.");
        }
    }

    /**
     * Prompts until a nonblank custom label is entered.
     *
     * @param prompt prompt displayed to the user
     * @return custom label
     */
    private static String promptForCustomLabel(String prompt) {
        while (true) {
            System.out.print(prompt);
            String label = PromptUtils.readOperationLine().trim();

            if (!label.isBlank()) {
                return label;
            }

            System.out.println("Please enter a label.");
        }
    }
}
