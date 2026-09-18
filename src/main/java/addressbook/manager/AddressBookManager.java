package addressbook.manager;

import addressbook.io.AppPaths;
import addressbook.io.FileLoader;
import addressbook.io.FileParser;
import addressbook.io.FileSaver;
import addressbook.model.Contact;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Manages the in-memory collection of contacts and coordinates
 * loading and saving contacts through the persistence layer.
 */
public class AddressBookManager {

    private final Path filePath;
    private final FileParser<Contact> contactParser;

    private List<Contact> contacts = new ArrayList<>();

    /**
     * Creates a manager using the application's configured data directory.
     * The {@code APP_DATA_DIR} environment variable may override the
     * default data directory.
     */
    public AddressBookManager() {
        Path baseDir = AppPaths.ensureBaseDirectoryExists();
        this.filePath = AppPaths.addressBookFile(baseDir);
        this.contactParser = new FileParser<>(";", Contact::fromString);
    }

    /**
     * Creates a manager using an explicitly provided address book file.
     * This constructor supports isolated filesystem testing.
     *
     * @param filePath path to the address book file
     */
    public AddressBookManager(Path filePath) {
        this.filePath = filePath;
        this.contactParser = new FileParser<>(";", Contact::fromString);
    }

    /**
     * Loads contacts from persistent storage into memory.
     * If the address book file does not exist, the manager starts
     * with an empty contact collection.
     *
     * @throws IOException if the address book file cannot be read
     */
    public void loadContacts() throws IOException {
        if (!FileLoader.fileExists(filePath)) {
            contacts = new ArrayList<>();
            return;
        }

        List<String> lines = FileLoader.loadLines(filePath);
        contacts = contactParser.parseLines(lines);
    }

    /**
     * Serializes the current contacts and writes them to persistent storage.
     *
     * @throws IOException if the address book file cannot be written
     */
    public void saveContacts() throws IOException {
        List<String> toWrite = contacts.stream()
                .map(Contact::toString)
                .toList();

        FileSaver.saveLines(filePath, toWrite);
    }

    /**
     * Adds a contact to the address book.
     *
     * @param contact contact to add
     */
    public void addContact(Contact contact) {
        contacts.add(contact);
    }

    /**
     * Removes the specified contact from the address book.
     *
     * @param contact contact to remove
     * @return {@code true} if the contact was present and removed
     */
    public boolean removeContact(Contact contact) {
        return contacts.remove(contact);
    }

    /**
     * Returns an unmodifiable view of the contacts in the address book.
     *
     * @return unmodifiable list of contacts
     */
    public List<Contact> getContacts() {
        return Collections.unmodifiableList(contacts);
    }

    /**
     * Returns the file used for address book persistence.
     *
     * @return address book file path
     */
    public Path getFilePath() {
        return filePath;
    }
}
