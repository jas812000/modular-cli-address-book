package addressbook.input;

import addressbook.model.Address;

import java.util.ArrayList;
import java.util.List;

/**
 * Collects one or more addresses for a contact.
 */
public final class AddressPrompter {

    /**
     * Prevents instantiation because this class provides only static utility methods.
     */
    private AddressPrompter() {
    }

    /**
     * Prompts the user for addresses until no additional address is requested.
     *
     * @return entered addresses
     */
    public static List<Address> prompt() {
        List<Address> addresses = new ArrayList<>();

        do {
            addresses.add(promptForAddress());
        } while (PromptUtils.promptYesNo("Add another address? (y/n): "));

        return addresses;
    }

    /**
     * Prompts for a single address.
     *
     * @return entered address
     */
    public static Address promptForAddress() {
        String label = promptForLabel();

        System.out.print("Street address: ");
        String street = PromptUtils.readOperationLine().trim();

        System.out.print("City: ");
        String city = PromptUtils.readOperationLine().trim();

        System.out.print("State (2-letter abbreviation): ");
        String state = promptForState();

        System.out.print("ZIP Code: ");
        String zip = PromptUtils.readOperationLine().trim();

        return new Address(label, street, city, state, zip);
    }

    /**
     * Prompts for an address label.
     *
     * @return selected or custom address label
     */
    private static String promptForLabel() {
        while (true) {
            System.out.println("Address type:");
            System.out.println("1. Home");
            System.out.println("2. Work");
            System.out.println("3. Other");
            System.out.print("Select an option: ");

            int selection = PromptUtils.parseSelection(
                    PromptUtils.readOperationLine(),
                    3
            );

            if (selection == 0) {
                return "Home";
            }

            if (selection == 1) {
                return "Work";
            }

            if (selection == 2) {
                return promptForCustomLabel("Custom address type: ");
            }

            System.out.println("Please select a valid option.");
        }
    }

    /**
     * Prompts until a nonblank custom label is entered.
     *
     * @param prompt prompt displayed to the user
     * @return custom label
     */
    private static String promptForCustomLabel(String prompt) {
        while (true) {
            System.out.print(prompt);
            String label = PromptUtils.readOperationLine().trim();

            if (!label.isBlank()) {
                return label;
            }

            System.out.println("Please enter a label.");
        }
    }

    /**
     * Prompts for and validates a two-letter state abbreviation.
     *
     * @return uppercase state abbreviation
     */
    private static String promptForState() {
        while (true) {
            String state = PromptUtils.readOperationLine()
                    .trim()
                    .toUpperCase();

            if (state.matches("[A-Z]{2}")) {
                return state;
            }

            System.out.print("Enter a valid 2-letter state abbreviation: ");
        }
    }
}
