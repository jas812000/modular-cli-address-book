package input;

import manager.AddressBookManager;
import model.Contact;
import display.ContactDisplayFormatter;

import java.util.List;
import java.util.Scanner;

/**
 * Handles displaying contact names and showing full details on selection.
 */
public class ContactDisplayer {

    private static final Scanner scanner = new Scanner(System.in);

    public static void showNamesAndSelect(AddressBookManager manager) {
        List<Contact> contacts = manager.getContacts();
        if (contacts.isEmpty()) {
            System.out.println("No contacts available.");
            return;
        }

        System.out.println("\n--- Contact Names ---");
        for (int i = 0; i < contacts.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, contacts.get(i).getFullName());
        }

        System.out.print("Enter number to view full contact or press Enter to skip: ");
        String input = scanner.nextLine().trim();

        if (!input.isBlank()) {
            try {
                int index = Integer.parseInt(input) - 1;
                if (index >= 0 && index < contacts.size()) {
                    Contact selected = contacts.get(index);
                    System.out.println("\n" + ContactDisplayFormatter.formatContact(selected));
                } else {
                    System.out.println("Invalid selection.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input.");
            }
        }
    }
}


