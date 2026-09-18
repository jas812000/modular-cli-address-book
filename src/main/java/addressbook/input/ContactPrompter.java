package addressbook.input;

import addressbook.model.Contact;

/**
 * Collects the information required to create a contact.
 */
public final class ContactPrompter {

    /**
     * Prevents instantiation because this class provides only static utility methods.
     */
    private ContactPrompter() {
    }

    /**
     * Prompts the user for a contact's name, addresses, phone numbers,
     * and email addresses.
     *
     * @return newly created contact
     */
    public static Contact prompt() {
        String firstName = promptForRequiredName(
                "First name (or \"exit\" to leave): "
        );

        System.out.print("Middle name (optional): ");
        String middleName = PromptUtils.readOperationLine().trim();

        if (middleName.isBlank()) {
            middleName = null;
        }

        String lastName = promptForRequiredName("Last name: ");

        return new Contact(
                firstName,
                middleName,
                lastName,
                AddressPrompter.prompt(),
                PhoneNumberPrompter.prompt(),
                EmailAddressPrompter.prompt()
        );
    }

    /**
     * Prompts until a nonblank required name is entered.
     *
     * <p>The entered value is otherwise preserved, allowing compound,
     * hyphenated, and other valid name formats.</p>
     *
     * @param prompt prompt displayed to the user
     * @return entered nonblank name
     */
    private static String promptForRequiredName(String prompt) {
        while (true) {
            System.out.print(prompt);
            String name = PromptUtils.readOperationLine().trim();

            if (!name.isBlank()) {
                return name;
            }

            System.out.println("This field is required.");
        }
    }
}
