package addressbook.input;

import addressbook.display.ContactDisplayFormatter;
import addressbook.manager.AddressBookManager;
import addressbook.model.Contact;

import java.util.List;

/**
 * Displays contacts and allows the user to select a contact for
 * detailed viewing.
 */
public final class ContactDisplayer {

    /**
     * Prevents instantiation because this class provides only static utility methods.
     */
    private ContactDisplayer() {
    }

    /**
     * Displays contact names and optionally displays the full details
     * of a selected contact.
     *
     * @param manager manager containing the contacts to display
     */
    public static void showNamesAndSelect(AddressBookManager manager) {
        List<Contact> contacts = manager.getContacts();

        if (contacts.isEmpty()) {
            System.out.println("No contacts available.");
            return;
        }

        System.out.println("\n--- Contact Names ---");

        for (int i = 0; i < contacts.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, contacts.get(i).getFullName());
        }

        System.out.print("Enter number to view full contact, press Enter to skip, or \"exit\" to leave: ");
        String input = PromptUtils.readOperationLine().trim();

        if (input.isBlank()) {
            return;
        }

        int index = PromptUtils.parseSelection(input, contacts.size());

        if (index == -1) {
            System.out.println("Invalid selection.");
            return;
        }

        Contact selected = contacts.get(index);
        System.out.println("\n" + ContactDisplayFormatter.formatContact(selected));
    }
}
