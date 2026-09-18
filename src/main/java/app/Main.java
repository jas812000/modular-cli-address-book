package app;

import addressbook.input.InputSessionRunner;
import addressbook.manager.AddressBookManager;

/**
 * Application entry point for the Modular CLI Address Book.
 */
public final class Main {

    private Main() {
        // Application entry-point class
    }

    /**
     * Starts the address book application.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        AddressBookManager manager = new AddressBookManager();
        InputSessionRunner.run(manager);
    }
}
