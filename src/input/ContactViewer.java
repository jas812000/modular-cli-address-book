
package input;

import model.Contact;

import java.util.List;

/**
 * Displays all contacts in a simple numbered list.
 */
public class ContactViewer {

    public static void display(List<Contact> contacts) {
        if (contacts.isEmpty()) {
            System.out.println("No contacts to display.");
            return;
        }

        System.out.println("\n--- Contact List ---");
        for (int i = 0; i < contacts.size(); i++) {
            System.out.println("\nContact #" + (i + 1));
            System.out.println(contacts.get(i).getFullName());
        }
    }
}

