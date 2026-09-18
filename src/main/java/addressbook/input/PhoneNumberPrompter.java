package addressbook.input;

import addressbook.model.PhoneNumber;

import java.util.ArrayList;
import java.util.List;

/**
 * Collects one or more phone numbers for a contact.
 */
public final class PhoneNumberPrompter {

    /**
     * Prevents instantiation because this class provides only static utility methods.
     */
    private PhoneNumberPrompter() {
    }

    /**
     * Prompts the user for phone numbers until no additional number is requested.
     *
     * @return entered phone numbers
     */
    public static List<PhoneNumber> prompt() {
        List<PhoneNumber> phones = new ArrayList<>();

        do {
            phones.add(promptForPhoneNumber());
        } while (PromptUtils.promptYesNo("Add another phone number? (y/n): "));

        return phones;
    }

    /**
     * Prompts for a single labeled phone number.
     *
     * @return entered phone number
     */
    public static PhoneNumber promptForPhoneNumber() {
        String label = promptForLabel();
        String number = promptForNumber();

        return new PhoneNumber(label, number);
    }

    /**
     * Prompts for a phone number label.
     *
     * @return selected or custom phone number label
     */
    private static String promptForLabel() {
        while (true) {
            System.out.println("Phone type:");
            System.out.println("1. Mobile");
            System.out.println("2. Home");
            System.out.println("3. Work");
            System.out.println("4. Other");
            System.out.print("Select an option: ");

            int selection = PromptUtils.parseSelection(
                    PromptUtils.readOperationLine(),
                    4
            );

            if (selection == 0) {
                return "Mobile";
            }

            if (selection == 1) {
                return "Home";
            }

            if (selection == 2) {
                return "Work";
            }

            if (selection == 3) {
                return promptForCustomLabel("Custom phone type: ");
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
     * Prompts for a 10-digit phone number and formats it as XXX-XXX-XXXX.
     *
     * @return formatted phone number
     */
    private static String promptForNumber() {
        while (true) {
            System.out.print("Phone number: ");

            String input = PromptUtils.readOperationLine().trim();
            String digits = input.replaceAll("\\D", "");

            if (digits.length() == 10) {
                return digits.substring(0, 3)
                        + "-"
                        + digits.substring(3, 6)
                        + "-"
                        + digits.substring(6);
            }

            System.out.println("Please enter a valid 10-digit phone number.");
        }
    }
}
