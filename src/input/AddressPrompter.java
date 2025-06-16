package input;

import model.Address;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Prompts for multiple addresses.
 */
public class AddressPrompter {

    private static final Scanner scanner = new Scanner(System.in);

    public static List<Address> prompt() {
        List<Address> addresses = new ArrayList<>();
        do {
            System.out.print("Enter address label (e.g. Home, Work): ");
            String label = scanner.nextLine();

            System.out.print("Street address: ");
            String street = scanner.nextLine();

            System.out.print("City: ");
            String city = scanner.nextLine();

            System.out.print("State: ");
            String state = scanner.nextLine();

            System.out.print("ZIP Code: ");
            String zip = scanner.nextLine();

            addresses.add(new Address(label, street, city, state, zip));

        } while (PromptUtils.promptYesNo("Add another address? (y/n): "));

        return addresses;
    }
}
