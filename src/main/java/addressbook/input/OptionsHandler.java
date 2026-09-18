package addressbook.input;

import addressbook.display.ContactDisplayFormatter;
import addressbook.editor.ContactEditor;
import addressbook.manager.AddressBookManager;
import addressbook.model.Contact;

/**
 * Coordinates high-level contact creation and editing operations.
 */
public final class OptionsHandler {

    /**
     * Prevents instantiation because this class provides only static utility methods.
     */
    private OptionsHandler() {
    }

    /**
     * Prompts for a new contact, adds it to the address book,
     * and displays the completed contact.
     *
     * @param manager manager responsible for the address book
     */
    public static void createNewContact(AddressBookManager manager) {
        Contact newContact = ContactPrompter.prompt();

        manager.addContact(newContact);

        System.out.println(
                "\nContact added:\n"
                        + ContactDisplayFormatter.formatContact(newContact)
        );
    }

    /**
     * Starts the interactive contact-editing workflow.
     *
     * @param manager manager containing the contacts
     */
    public static void editContact(AddressBookManager manager) {
        ContactEditor.modifyContact(manager);
    }
}
