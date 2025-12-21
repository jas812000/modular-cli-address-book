package addressbook.editor;

import addressbook.model.Contact;
import addressbook.model.EmailAddress;

import java.util.List;
import java.util.Scanner;

/**
 * Handles editing a contact's email addresses.
 */
public class EmailEditor {

    private static final Scanner scanner = new Scanner(System.in);

    public static void edit(Contact contact) {
        List<EmailAddress> emails = contact.getEmailAddresses();
        if (emails.isEmpty()) {
            System.out.println("No email addresses to edit.");
            return;
        }

        for (int i = 0; i < emails.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, emails.get(i).getLabel());
        }

        System.out.print("Select email to edit: ");
        int index = Integer.parseInt(scanner.nextLine()) - 1;
        if (index < 0 || index >= emails.size()) {
            System.out.println("Invalid index.");
            return;
        }

        System.out.print("New label: ");
        String label = scanner.nextLine();
        System.out.print("New email: ");
        String email = scanner.nextLine();

        emails.set(index, new EmailAddress(label, email));
        System.out.println("Email updated.");
    }
}


