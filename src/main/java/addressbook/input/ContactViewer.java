package addressbook.input;

import addressbook.model.Contact;

import java.util.List;

/**
 * Displays contacts in a simple numbered list for quick selection
 * and overview.
 */
public final class ContactViewer {

    /**
     * Prevents instantiation because this class provides only static utility methods.
     */
    private ContactViewer() {
    }

    /**
     * Displays the supplied contacts as a numbered list of names.
     *
     * @param contacts contacts to display
     */
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
