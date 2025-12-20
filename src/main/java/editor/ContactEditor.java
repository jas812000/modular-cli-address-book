package editor;

import display.ContactDisplayFormatter;
import manager.AddressBookManager;
import model.Contact;

import java.util.List;
import java.util.Scanner;

/**
 * Provides editing capabilities for an existing contact, delegating to modular field editors.
 */
public class ContactEditor {

    private static final Scanner scanner = new Scanner(System.in);

    public static void modifyContact(AddressBookManager manager) {
        List<Contact> contacts = manager.getContacts();
        if (contacts.isEmpty()) {
            System.out.println("No contacts to edit.");
            return;
        }

        // Display contact names
        System.out.println("\n--- Contact List ---");
        for (int i = 0; i < contacts.size(); i++) {
            System.out.printf("\nContact #%d\n%s\n", i + 1, contacts.get(i).getFullName());
        }

        System.out.print("\nEnter contact number to edit: ");
        int index;
        try {
            index = Integer.parseInt(scanner.nextLine()) - 1;
        } catch (NumberFormatException e) {
            System.out.println("Invalid input.");
            return;
        }

        if (index < 0 || index >= contacts.size()) {
            System.out.println("Invalid selection.");
            return;
        }

        Contact contact = contacts.get(index);
        System.out.println("\nSelected Contact:\n" + ContactDisplayFormatter.formatContact(contact));

        System.out.println("\nWhich part would you like to modify?");
        System.out.println("1. Name");
        System.out.println("2. Addresses");
        System.out.println("3. Phone Numbers");
        System.out.println("4. Email Addresses");
        System.out.print("Select an option: ");
        String option = scanner.nextLine();

        switch (option) {
            case "1" -> NameEditor.edit(contact);
            case "2" -> AddressEditor.edit(contact);
            case "3" -> PhoneEditor.edit(contact);
            case "4" -> EmailEditor.edit(contact);
            default -> System.out.println("Invalid choice.");
        }
    }
}

