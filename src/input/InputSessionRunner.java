package input;

import manager.AddressBookManager;
import model.Contact;

import java.util.Scanner;

/**
 * Runs the address book session: load, interactively add/view/edit/delete contacts, and save.
 */
public class InputSessionRunner {

    public static void run(AddressBookManager manager) {
        try {
            manager.loadContacts();

            System.out.println("\nWelcome to the Address Book!");
            Scanner scanner = new Scanner(System.in);
            boolean keepGoing = true;

            while (keepGoing) {
                System.out.println("\n--- Menu ---");
                System.out.println("1. Add Contact");
                System.out.println("2. View Contacts");
                System.out.println("3. Edit Contact Info");
                System.out.println("4. Delete Contact");
                System.out.println("5. Search Contacts");
                System.out.println("6. Exit");
                System.out.print("Select an option: ");
                String choice = scanner.nextLine();

                switch (choice) {
                    case "1" -> OptionsHandler.createNewContact(manager);
                    case "2" -> ContactDisplayer.showNamesAndSelect(manager);
                    case "3" -> OptionsHandler.editOrDeleteDataPoint(manager);
                    case "4" -> ContactDeleter.removeContact(manager);
                    case "5" -> ContactSearcher.searchAndSelect(manager);
                    case "6" -> keepGoing = false;
                    default -> System.out.println("Invalid choice. Try again.");
                }
            }

            manager.saveContacts();
            System.out.println("\nAddress book saved. Goodbye!");

        } catch (Exception e) {
            System.err.println("Error during session: " + e.getMessage());
        }
    }
}

