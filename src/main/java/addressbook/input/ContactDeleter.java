package addressbook.input;

import addressbook.manager.AddressBookManager;
import addressbook.model.Contact;

import java.util.List;

/**
 * Handles interactive removal of contacts from the address book.
 */
public final class ContactDeleter {

    /**
     * Prevents instantiation because this class provides only static utility methods.
     */
    private ContactDeleter() {
    }

    /**
     * Displays the available contacts, prompts the user to select one,
     * and delegates removal to the address book manager.
     *
     * @param manager manager containing the contacts
     */
    public static void removeContact(AddressBookManager manager) {
        List<Contact> contacts = manager.getContacts();

        if (contacts.isEmpty()) {
            System.out.println("No contacts to delete.");
            return;
        }

        ContactViewer.display(contacts);

        int index;

        while (true) {
            System.out.print(
                    "\nEnter contact number to delete (or \"exit\" to leave): "
            );

            index = PromptUtils.parseSelection(
                    PromptUtils.readOperationLine(),
                    contacts.size()
            );

            if (index != -1) {
                break;
            }

            System.out.println("Invalid contact number.");
        }

        Contact contact = contacts.get(index);

        System.out.println("Deleting: " + contact.getFullName());

        if (manager.removeContact(contact)) {
            System.out.println("Contact deleted.");
        }
    }
}
