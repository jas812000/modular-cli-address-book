package addressbook.input;

import addressbook.display.ContactDisplayFormatter;
import addressbook.editor.ContactEditor;
import addressbook.manager.AddressBookManager;

/**
 * Provides core options for creating and modifying contacts.
 * Delegates specific tasks like displaying and searching to dedicated classes.
 */
public class OptionsHandler {

    public static void createNewContact(AddressBookManager manager) {
        var newContact = ContactPrompter.prompt();
        manager.addContact(newContact);
        System.out.println("Contact added.");
    }

    public static void editOrDeleteDataPoint(AddressBookManager manager) {
        ContactEditor.modifyContact(manager);
    }

    /**
     * Display all contacts in a human-readable formatted style.
     */
    public static void displayFormattedContacts(AddressBookManager manager) {
        ContactDisplayFormatter.displayFormatted(manager.getContacts());
    }
}

