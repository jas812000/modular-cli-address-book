package addressbook.manager;

import addressbook.model.Contact;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class AddressBookManagerIntegrationTest {

    @TempDir
    Path tempDir;

    @Test
    void saveThenLoad_roundTrip_preservesContactCountAndNames()
            throws Exception {
        Path file = tempDir.resolve("address_book.csv");

        AddressBookManager firstManager =
                new AddressBookManager(file);

        firstManager.addContact(
                Contact.fromString(
                        "James,,Stevens;Home:123 Maple St|Chicago|IL|60601;Mobile:312-555-0101;Personal:james@example.com"
                                .split(";")
                )
        );

        firstManager.addContact(
                Contact.fromString(
                        "Maria,Elena,Garcia;Home:55 W Lake St|Chicago|IL|60601;Work:312-555-0199;Personal:maria@example.com"
                                .split(";")
                )
        );

        firstManager.saveContacts();

        AddressBookManager secondManager =
                new AddressBookManager(file);

        secondManager.loadContacts();

        assertEquals(2, secondManager.getContacts().size());
        assertEquals(
                "James Stevens",
                secondManager.getContacts().get(0).getFullName()
        );
        assertEquals(
                "Maria Elena Garcia",
                secondManager.getContacts().get(1).getFullName()
        );
    }

    @Test
    void removeContact_existingContact_removesContact() {
        Path file = tempDir.resolve("address_book.csv");
        AddressBookManager manager =
                new AddressBookManager(file);

        Contact contact = Contact.fromString(
                "James,,Stevens;Home:123 Maple St|Chicago|IL|60601;Mobile:312-555-0101;Personal:james@example.com"
                        .split(";")
        );

        manager.addContact(contact);

        assertTrue(manager.removeContact(contact));
        assertTrue(manager.getContacts().isEmpty());
    }

    @Test
    void getContacts_doesNotAllowExternalModification() {
        Path file = tempDir.resolve("address_book.csv");
        AddressBookManager manager =
                new AddressBookManager(file);

        manager.addContact(
                Contact.fromString(
                        "James,,Stevens".split(";")
                )
        );

        assertThrows(
                UnsupportedOperationException.class,
                () -> manager.getContacts().clear()
        );

        assertEquals(1, manager.getContacts().size());
    }
}
