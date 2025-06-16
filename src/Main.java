import input.InputSessionRunner;
import manager.AddressBookManager;
import io.AppPaths;

public class Main {
    public static void main(String[] args) {
        AddressBookManager manager = new AddressBookManager(AppPaths.ADDRESS_BOOK_FILE);
        InputSessionRunner.run(manager);
    }
}

