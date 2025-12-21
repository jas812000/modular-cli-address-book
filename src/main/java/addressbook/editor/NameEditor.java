package addressbook.editor;

import addressbook.model.Contact;

import java.util.Scanner;

/**
 * Handles editing a contact's name (first, middle, last).
 */
public class NameEditor {

    private static final Scanner scanner = new Scanner(System.in);

    public static void edit(Contact contact) {
        System.out.print("New First Name: ");
        String first = scanner.nextLine();

        System.out.print("New Middle Name (blank for none): ");
        String middle = scanner.nextLine();
        if (middle.isBlank()) middle = null;

        System.out.print("New Last Name: ");
        String last = scanner.nextLine();

        contact.setName(first, middle, last);
        System.out.println("Name updated.");
    }
}


