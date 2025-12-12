package manager;

import io.FileLoader;
import io.FileParser;
import io.FileSaver;
import io.LineParser;
import model.Contact;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;


/**
 * Central manager responsible for loading, saving, and maintaining
 * the in-memory list of contacts.
 *
 * Acts as the boundary between persistent storage and application logic.
 */
public class AddressBookManager {
    private final Path filePath;
    private List<Contact> contacts = new ArrayList<>();

    public AddressBookManager(Path filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads contacts from persistent storage into memory.
     * If no file exists, the address book starts empty.
     */
    public void loadContacts() throws IOException {
        if (!FileLoader.fileExists(filePath)) {
            System.out.println("No address book found. Starting empty.");
            return;
        }

        List<String> lines = FileLoader.loadLines(filePath);
        LineParser<Contact> parser = Contact::fromString;
        FileParser<Contact> contactParser = new FileParser<>(";", parser);
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

    public List<Contact> getContacts() {
        return contacts;
    }
}

