package addressbook.input;

import addressbook.model.PhoneNumber;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Prompts for multiple phone numbers.
 */
public class PhoneNumberPrompter {

    private static final Scanner scanner = new Scanner(System.in);

    public static List<PhoneNumber> prompt() {
        List<PhoneNumber> phones = new ArrayList<>();
        do {
            System.out.print("Enter phone label (e.g. Mobile, Work): ");
            String label = scanner.nextLine();

            System.out.print("Phone number: ");
            String number = scanner.nextLine();

            phones.add(new PhoneNumber(label, number));

        } while (PromptUtils.promptYesNo("Add another phone number? (y/n): "));

        return phones;
    }
}

