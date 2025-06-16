package input;

import display.ContactDisplayFormatter;
import manager.AddressBookManager;
import model.Contact;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Allows the user to search contacts by name, phone, email, or address content.
 */
public class ContactSearcher {

    private static final Scanner scanner = new Scanner(System.in);

    public static void searchAndSelect(AddressBookManager manager) {
        System.out.print("Search by name, phone, email, or address: ");
        String query = scanner.nextLine().trim().toLowerCase();

        List<Contact> matches = manager.getContacts().stream()
                .filter(c ->
                        c.getFullName().toLowerCase().contains(query) ||
                                c.getPhoneNumbers().stream().anyMatch(p -> p.getNumber().toLowerCase().contains(query)) ||
                                c.getEmailAddresses().stream().anyMatch(e -> e.getEmail().toLowerCase().contains(query)) ||
                                c.getAddresses().stream().anyMatch(a -> a.toString().toLowerCase().contains(query))
                )
                .collect(Collectors.toList());

        if (matches.isEmpty()) {
            System.out.println("No matches found.");
            return;
        }

        System.out.println("\n--- Matching Contacts ---");
        for (int i = 0; i < matches.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, matches.get(i).getFullName());
        }

        System.out.print("Enter number to view full contact or press Enter to skip: ");
        String input = scanner.nextLine().trim();
        if (!input.isBlank()) {
            try {
                int index = Integer.parseInt(input) - 1;
                if (index >= 0 && index < matches.size()) {
                    System.out.println("\n" + ContactDisplayFormatter.formatContact(matches.get(index)));
                    System.out.println("---------------");
                }
                else {
                    System.out.println("Invalid selection.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input.");
            }
        }
    }
}