package addressbook.manager;

import addressbook.io.AppPaths;
import addressbook.io.FileLoader;
import addressbook.io.FileParser;
import addressbook.io.FileSaver;
import addressbook.io.LineParser;
import addressbook.model.Contact;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Central manager responsible for loading, saving, and maintaining
 * the in-memory list of contacts.
 *
 * Acts as the boundary between persistent storage and application logic.
 */
public class AddressBookManager {

    private final Path filePath;
    private final FileParser<Contact> contactParser;

    private List<Contact> contacts = new ArrayList<>();

    /** Production constructor (APP_DATA_DIR override supported via AppPaths). */
    public AddressBookManager() {
        Path baseDir = AppPaths.ensureBaseDirectoryExists();
        this.filePath = AppPaths.addressBookFile(baseDir);
        this.contactParser = new FileParser<>(";", Contact::fromString);
    }

    /** Test-friendly constructor: inject an explicit file path. */
    public AddressBookManager(Path filePath) {
        this.filePath = filePath;
        this.contactParser = new FileParser<>(";", Contact::fromString);
    }

    /**
     * Loads contacts from persistent storage into memory.
     * If no file exists, the address book starts empty.
     */
    public void loadContacts() throws IOException {
        if (!FileLoader.fileExists(filePath)) {
            // Start empty. No printing here (keeps manager test-friendly).
            contacts = new ArrayList<>();
            return;
        }

        List<String> lines = FileLoader.loadLines(filePath);
        contacts = contactParser.parseLines(lines);
    }

    /**
     * Serializes all contacts and writes them to persistent storage.
     */
    public void saveContacts() throws IOException {
        List<String> toWrite = contacts.stream().map(Contact::toString).toList();
        FileSaver.saveLines(filePath, toWrite);
    }

    public void addContact(Contact contact) {
        contacts.add(contact);
    }

    /** Expose a read-only view to prevent external mutation. */
    public List<Contact> getContacts() {
        return Collections.unmodifiableList(contacts);
    }

    /** Handy for testing/debugging. */
    public Path getFilePath() {
        return filePath;
    }
}

