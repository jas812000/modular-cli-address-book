package addressbook.editor;

import addressbook.input.PromptUtils;
import addressbook.model.Contact;

/**
 * Handles editing a contact's first, middle, and last name.
 */
public final class NameEditor {

    /**
     * Prevents instantiation because this class provides only static utility methods.
     */
    private NameEditor() {
    }

    /**
     * Prompts for replacement name values and updates the contact.
     *
     * @param contact contact whose name will be updated
     * @return {@code true} when the contact is updated
     */
    public static boolean edit(Contact contact) {
        System.out.print("New First Name: ");
        String first = PromptUtils.readOperationLine().trim();

        System.out.print("New Middle Name (blank for none): ");
        String middle = PromptUtils.readOperationLine().trim();

        if (middle.isBlank()) {
            middle = null;
        }

        System.out.print("New Last Name: ");
        String last = PromptUtils.readOperationLine().trim();

        contact.setName(first, middle, last);

        System.out.println("Name updated.");
        return true;
    }
}
