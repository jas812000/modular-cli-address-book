package input;

import model.Contact;

import java.util.Scanner;

/**
 * Handles input for the overall Contact object.
 */
public class ContactPrompter {

    private static final Scanner scanner = new Scanner(System.in);

    public static Contact prompt() {
        System.out.print("First name: ");
        String firstName = scanner.nextLine().trim();

        System.out.print("Middle name (optional): ");
        String middleName = scanner.nextLine().trim();
        if (middleName.isBlank()) middleName = null;

        System.out.print("Last name: ");
        String lastName = scanner.nextLine().trim();

        return new Contact(
                firstName,
                middleName,
                lastName,
                AddressPrompter.prompt(),
                PhoneNumberPrompter.prompt(),
                EmailAddressPrompter.prompt()
        );
    }
}
