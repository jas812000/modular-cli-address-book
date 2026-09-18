package addressbook.editor;

import addressbook.input.PhoneNumberPrompter;
import addressbook.input.PromptUtils;
import addressbook.model.Contact;
import addressbook.model.PhoneNumber;

import java.util.List;

/**
 * Handles adding and editing a contact's phone numbers.
 */
public final class PhoneEditor {

    /**
     * Prevents instantiation because this class provides only static utility methods.
     */
    private PhoneEditor() {
    }

    /**
     * Prompts the user to add a phone number or edit an existing phone number.
     *
     * @param contact contact whose phone numbers will be modified
     * @return {@code true} when a phone number is added or updated; otherwise {@code false}
     */
    public static boolean edit(Contact contact) {
        displayPhoneNumbers(contact.getPhoneNumbers());

        while (true) {
            System.out.println("\n1. Add Phone Number");
            System.out.println("2. Edit Phone Number");
            System.out.print("Select an option (or \"exit\" to leave): ");

            String option = PromptUtils.readOperationLine().trim();

            switch (option) {
                case "1" -> {
                    contact.addPhoneNumber(
                            PhoneNumberPrompter.promptForPhoneNumber()
                    );
                    System.out.println("Phone number added.");
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
     * Prompts the user to select and replace an existing phone number.
     *
     * @param contact contact whose phone number will be updated
     * @return {@code true} when a phone number is updated; otherwise {@code false}
     */
    private static boolean editExisting(Contact contact) {
        List<PhoneNumber> phones = contact.getPhoneNumbers();

        if (phones.isEmpty()) {
            System.out.println("No phone numbers to edit.");
            return false;
        }

        displayPhoneNumbers(phones);

        int index = promptForSelection(phones.size());

        contact.replacePhoneNumber(
                index,
                PhoneNumberPrompter.promptForPhoneNumber()
        );

        System.out.println("Phone number updated.");
        return true;
    }

    /**
     * Displays the contact's phone numbers.
     *
     * @param phones phone numbers to display
     */
    private static void displayPhoneNumbers(List<PhoneNumber> phones) {
        System.out.println("\nPhone Numbers:");

        if (phones.isEmpty()) {
            System.out.println("None");
            return;
        }

        for (int i = 0; i < phones.size(); i++) {
            PhoneNumber phone = phones.get(i);

            System.out.printf(
                    "%d. %s - %s%n",
                    i + 1,
                    phone.getLabel(),
                    phone.getNumber()
            );
        }
    }

    /**
     * Prompts until a valid phone number selection is entered.
     *
     * @param itemCount number of available phone numbers
     * @return zero-based selected index
     */
    private static int promptForSelection(int itemCount) {
        while (true) {
            System.out.print(
                    "Select phone number to edit (or \"exit\" to leave): "
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
