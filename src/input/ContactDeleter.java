package input;

import manager.AddressBookManager;
import model.Contact;

import java.util.List;
import java.util.Scanner;

/**
 * Removes a full contact from the address book.
 */
public class ContactDeleter {

    public static void removeContact(AddressBookManager manager) {
        List<Contact> contacts = manager.getContacts();
        if (contacts.isEmpty()) {
            System.out.println("No contacts to delete.");
            return;
        }

        ContactViewer.display(contacts);
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nEnter contact number to delete: ");
        int index = Integer.parseInt(scanner.nextLine()) - 1;

        if (index < 0 || index >= contacts.size()) {
            System.out.println("Invalid contact number.");
            return;
        }

        System.out.println("Deleting: " + contacts.get(index).getFullName());
        contacts.remove(index);
        System.out.println("Contact deleted.");
    }
}


