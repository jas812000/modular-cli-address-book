package app;

import addressbook.input.InputSessionRunner;
import addressbook.manager.AddressBookManager;

/**
 * Application entry point.
 */
public class Main {
    public static void main(String[] args) {
        AddressBookManager manager = new AddressBookManager();
        InputSessionRunner.run(manager);
    }
}

