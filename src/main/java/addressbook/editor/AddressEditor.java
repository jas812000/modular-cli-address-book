package addressbook.editor;

import addressbook.input.AddressPrompter;
import addressbook.input.PromptUtils;
import addressbook.model.Address;
import addressbook.model.Contact;

import java.util.List;

/**
 * Handles adding and editing a contact's addresses.
 */
public final class AddressEditor {

    /**
     * Prevents instantiation because this class provides only static utility methods.
     */
    private AddressEditor() {
    }

    /**
     * Prompts the user to add an address or edit an existing address.
     *
     * @param contact contact whose addresses will be modified
     * @return {@code true} when an address is added or updated; otherwise {@code false}
     */
    public static boolean edit(Contact contact) {
        displayAddresses(contact.getAddresses());

        while (true) {
            System.out.println("\n1. Add Address");
            System.out.println("2. Edit Address");
            System.out.print("Select an option (or \"exit\" to leave): ");

            String option = PromptUtils.readOperationLine().trim();

            switch (option) {
                case "1" -> {
                    contact.addAddress(AddressPrompter.promptForAddress());
                    System.out.println("Address added.");
                    return true;
                }
                case "2" -> {
                    return editExisting(contact);
                }
                default -> System.out.println("Please select a valid option.");
            }
        }
    }

    /**
     * Prompts the user to select and replace an existing address.
     *
     * @param contact contact whose address will be updated
     * @return {@code true} when an address is updated; otherwise {@code false}
     */
    private static boolean editExisting(Contact contact) {
        List<Address> addresses = contact.getAddresses();

        if (addresses.isEmpty()) {
            System.out.println("No addresses to edit.");
            return false;
        }

        displayAddresses(addresses);

        int index = promptForSelection(addresses.size());

        contact.replaceAddress(index, AddressPrompter.promptForAddress());

        System.out.println("Address updated.");
        return true;
    }

    /**
     * Displays the contact's addresses.
     *
     * @param addresses addresses to display
     */
    private static void displayAddresses(List<Address> addresses) {
        System.out.println("\nAddresses:");

        if (addresses.isEmpty()) {
            System.out.println("None");
            return;
        }

        for (int i = 0; i < addresses.size(); i++) {
            Address address = addresses.get(i);

            System.out.printf(
                    "%d. %s - %s, %s, %s %s%n",
                    i + 1,
                    address.getLabel(),
                    address.getStreetAddress(),
                    address.getCity(),
                    address.getState(),
                    address.getZipCode()
            );
        }
    }

    /**
     * Prompts until a valid address selection is entered.
     *
     * @param itemCount number of available addresses
     * @return zero-based selected index
     */
    private static int promptForSelection(int itemCount) {
        while (true) {
            System.out.print(
                    "Select address number to edit (or \"exit\" to leave): "
            );

            int index = PromptUtils.parseSelection(
                    PromptUtils.readOperationLine(),
                    itemCount
            );

            if (index != -1) {
                return index;
            }

            System.out.println("Invalid selection.");
        }
    }
}
