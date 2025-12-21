package addressbook.editor;

import addressbook.model.Address;
import addressbook.model.Contact;

import java.util.List;
import java.util.Scanner;

/**
 * Handles editing a contact's addresses.
 */
public class AddressEditor {

    private static final Scanner scanner = new Scanner(System.in);

    public static void edit(Contact contact) {
        List<Address> addresses = contact.getAddresses();
        if (addresses.isEmpty()) {
            System.out.println("No addresses to edit.");
            return;
        }

        for (int i = 0; i < addresses.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, addresses.get(i).getLabel());
        }

        System.out.print("Select address number to edit: ");
        int index = Integer.parseInt(scanner.nextLine()) - 1;
        if (index < 0 || index >= addresses.size()) {
            System.out.println("Invalid index.");
            return;
        }

        System.out.print("New label: ");
        String label = scanner.nextLine();
        System.out.print("Street address: ");
        String street = scanner.nextLine();
        System.out.print("City: ");
        String city = scanner.nextLine();
        System.out.print("State: ");
        String state = scanner.nextLine();
        System.out.print("Zip code: ");
        String zip = scanner.nextLine();

        addresses.set(index, new Address(label, street, city, state, zip));
        System.out.println("Address updated.");
    }
}

