package editor;

import model.Contact;
import model.PhoneNumber;

import java.util.List;
import java.util.Scanner;

/**
 * Handles editing a contact's phone numbers.
 */
public class PhoneEditor {

    private static final Scanner scanner = new Scanner(System.in);

    public static void edit(Contact contact) {
        List<PhoneNumber> phones = contact.getPhoneNumbers();
        if (phones.isEmpty()) {
            System.out.println("No phone numbers to edit.");
            return;
        }

        for (int i = 0; i < phones.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, phones.get(i).getLabel());
        }

        System.out.print("Select phone number to edit: ");
        int index = Integer.parseInt(scanner.nextLine()) - 1;
        if (index < 0 || index >= phones.size()) {
            System.out.println("Invalid index.");
            return;
        }

        System.out.print("New label: ");
        String label = scanner.nextLine();
        System.out.print("New number: ");
        String number = scanner.nextLine();

        phones.set(index, new PhoneNumber(label, number));
        System.out.println("Phone number updated.");
    }
}


