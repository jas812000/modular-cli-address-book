package addressbook.editor;

import addressbook.display.ContactDisplayFormatter;
import addressbook.input.PromptUtils;
import addressbook.manager.AddressBookManager;
import addressbook.model.Contact;

import java.util.List;

/**
 * Provides editing capabilities for existing contacts and delegates
 * individual fields to their corresponding editors.
 */
public final class ContactEditor {

    /**
     * Prevents instantiation because this class provides only static utility methods.
     */
    private ContactEditor() {
    }

    /**
     * Prompts the user to select a contact and the portion of that
     * contact to modify.
     *
     * @param manager manager containing the contacts
     */
    public static void modifyContact(AddressBookManager manager) {
        List<Contact> contacts = manager.getContacts();

        if (contacts.isEmpty()) {
            System.out.println("No contacts to edit.");
            return;
        }

        System.out.println("\n--- Contact List ---");

        for (int i = 0; i < contacts.size(); i++) {
            System.out.printf(
                    "%nContact #%d%n%s%n",
                    i + 1,
                    contacts.get(i).getFullName()
            );
        }

        System.out.print(
                "\nEnter contact number to edit (or \"exit\" to leave): "
        );

        int index = PromptUtils.parseSelection(
                PromptUtils.readOperationLine(),
                contacts.size()
        );

        if (index == -1) {
            System.out.println("Invalid selection.");
            return;
        }

        Contact contact = contacts.get(index);

        System.out.println(
                "\nSelected Contact:\n"
                        + ContactDisplayFormatter.formatContact(contact)
        );

        System.out.println("\nWhich part would you like to modify?");
        System.out.println("1. Name");
        System.out.println("2. Addresses");
        System.out.println("3. Phone Numbers");
        System.out.println("4. Email Addresses");
        System.out.print("Select an option (or \"exit\" to leave): ");

        String option = PromptUtils.readOperationLine().trim();

        boolean updated;

        switch (option) {
            case "1" -> updated = NameEditor.edit(contact);
            case "2" -> updated = AddressEditor.edit(contact);
            case "3" -> updated = PhoneEditor.edit(contact);
            case "4" -> updated = EmailEditor.edit(contact);
            default -> {
                System.out.println("Invalid choice.");
                updated = false;
            }
        }

        if (updated) {
            System.out.println(
                    "\nUpdated Contact:\n"
                            + ContactDisplayFormatter.formatContact(contact)
            );
        }
    }
}
