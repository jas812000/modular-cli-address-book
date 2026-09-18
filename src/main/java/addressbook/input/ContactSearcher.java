package addressbook.input;

import addressbook.display.ContactDisplayFormatter;
import addressbook.manager.AddressBookManager;
import addressbook.model.Contact;

import java.util.List;

/**
 * Searches contacts by name, phone number, email address, or address content.
 */
public final class ContactSearcher {

    /**
     * Prevents instantiation because this class provides only static utility methods.
     */
    private ContactSearcher() {
    }

    /**
     * Prompts for a search term, displays matching contacts, and optionally
     * displays the full details of a selected match.
     *
     * @param manager manager containing the contacts to search
     */
    public static void searchAndSelect(AddressBookManager manager) {
        System.out.print("Search by name, phone, email, or address (or \"exit\" to leave): ");
        String query = PromptUtils.readOperationLine().trim();

        if (query.isBlank()) {
            System.out.println("Search cannot be blank.");
            return;
        }

        List<Contact> matches = manager.getContacts().stream()
                .filter(contact -> contact.matches(query))
                .toList();

        if (matches.isEmpty()) {
            System.out.println("No matches found.");
            return;
        }

        System.out.println("\n--- Matching Contacts ---");

        for (int i = 0; i < matches.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, matches.get(i).getFullName());
        }

        System.out.print("Enter number to view full contact, press Enter to skip, or \"exit\" to leave: ");
        String input = PromptUtils.readOperationLine().trim();

        if (input.isBlank()) {
            return;
        }

        int index = PromptUtils.parseSelection(input, matches.size());

        if (index == -1) {
            System.out.println("Invalid selection.");
            return;
        }

        System.out.println("\n" + ContactDisplayFormatter.formatContact(matches.get(index)));
        System.out.println("---------------");
    }
}
