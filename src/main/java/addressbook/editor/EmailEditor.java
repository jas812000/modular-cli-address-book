package addressbook.editor;

import addressbook.input.EmailAddressPrompter;
import addressbook.input.PromptUtils;
import addressbook.model.Contact;
import addressbook.model.EmailAddress;

import java.util.List;

/**
 * Handles adding and editing a contact's email addresses.
 */
public final class EmailEditor {

    /**
     * Prevents instantiation because this class provides only static utility methods.
     */
    private EmailEditor() {
    }

    /**
     * Prompts the user to add an email address or edit an existing email address.
     *
     * @param contact contact whose email addresses will be modified
     * @return {@code true} when an email address is added or updated; otherwise {@code false}
     */
    public static boolean edit(Contact contact) {
        displayEmailAddresses(contact.getEmailAddresses());

        while (true) {
            System.out.println("\n1. Add Email Address");
            System.out.println("2. Edit Email Address");
            System.out.print("Select an option (or \"exit\" to leave): ");

            String option = PromptUtils.readOperationLine().trim();

            switch (option) {
                case "1" -> {
                    contact.addEmailAddress(
                            EmailAddressPrompter.promptForEmailAddress()
                    );
                    System.out.println("Email address added.");
                    return true;
                }
                case "2" -> {
                    return editExisting(contact);
                }
                default -> System.out.println("Please select a valid option.");
            }
        }
    }

    /**
     * Prompts the user to select and replace an existing email address.
     *
     * @param contact contact whose email address will be updated
     * @return {@code true} when an email address is updated; otherwise {@code false}
     */
    private static boolean editExisting(Contact contact) {
        List<EmailAddress> emails = contact.getEmailAddresses();

        if (emails.isEmpty()) {
            System.out.println("No email addresses to edit.");
            return false;
        }

        displayEmailAddresses(emails);

        int index = promptForSelection(emails.size());

        contact.replaceEmailAddress(
                index,
                EmailAddressPrompter.promptForEmailAddress()
        );

        System.out.println("Email address updated.");
        return true;
    }

    /**
     * Displays the contact's email addresses.
     *
     * @param emails email addresses to display
     */
    private static void displayEmailAddresses(List<EmailAddress> emails) {
        System.out.println("\nEmail Addresses:");

        if (emails.isEmpty()) {
            System.out.println("None");
            return;
        }

        for (int i = 0; i < emails.size(); i++) {
            EmailAddress email = emails.get(i);

            System.out.printf(
                    "%d. %s - %s%n",
                    i + 1,
                    email.getLabel(),
                    email.getEmail()
            );
        }
    }

    /**
     * Prompts until a valid email address selection is entered.
     *
     * @param itemCount number of available email addresses
     * @return zero-based selected index
     */
    private static int promptForSelection(int itemCount) {
        while (true) {
            System.out.print(
                    "Select email number to edit (or \"exit\" to leave): "
            );

            int index = PromptUtils.parseSelection(
                    PromptUtils.readOperationLine(),
                    itemCount
            );

            if (index != -1) {
                return index;
            }

            System.out.println("Invalid selection.");
        }
    }
}
