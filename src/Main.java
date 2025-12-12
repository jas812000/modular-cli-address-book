import input.InputSessionRunner;
import manager.AddressBookManager;
import io.AppPaths;

/**
 * Application entry point.
 *
 * Initializes the AddressBookManager with the configured data path
 * and starts the interactive input session.
 */
public class Main {
    public static void main(String[] args) {
        AddressBookManager manager = new AddressBookManager(AppPaths.ADDRESS_BOOK_FILE);
        InputSessionRunner.run(manager);
    }
}

