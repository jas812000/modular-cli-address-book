package addressbook.input;

import addressbook.manager.AddressBookManager;

/**
 * Runs the interactive address book session from initial loading
 * through final persistence.
 */
public final class InputSessionRunner {

    /**
     * Prevents instantiation because this class provides only static utility methods.
     */
    private InputSessionRunner() {
    }

    /**
     * Loads the address book, processes menu selections, and saves the
     * contacts when the user exits normally.
     *
     * @param manager manager responsible for the address book
     */
    public static void run(AddressBookManager manager) {
        try {
            manager.loadContacts();
            System.out.println("\nWelcome to the Address Book!");

            boolean keepGoing = true;

            while (keepGoing) {
                System.out.println("\n--- Main Menu ---");
                System.out.println("1. Add Contact");
                System.out.println("2. View Contacts");
                System.out.println("3. Edit Contact Info");
                System.out.println("4. Delete Contact");
                System.out.println("5. Search Contacts");
                System.out.println("6. Exit");
                System.out.print("Select an option: ");

                String choice = PromptUtils.readLine().trim();

                try {
                    switch (choice) {
                        case "1" -> OptionsHandler.createNewContact(manager);
                        case "2" -> ContactDisplayer.showNamesAndSelect(manager);
                        case "3" -> OptionsHandler.editContact(manager);
                        case "4" -> ContactDeleter.removeContact(manager);
                        case "5" -> ContactSearcher.searchAndSelect(manager);
                        case "6" -> keepGoing = false;
                        default -> System.out.println("Invalid choice. Try again.");
                    }
                } catch (InputCancelledException e) {
                    System.out.println("\nOperation cancelled. Returning to main menu.");
                }
            }

            manager.saveContacts();
            System.out.println("\nAddress book saved. Goodbye!");

        } catch (Exception e) {
            System.err.println("Error during session: " + e.getMessage());
        }
    }
}
