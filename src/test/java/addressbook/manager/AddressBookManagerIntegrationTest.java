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
    void saveThenLoad_roundTrip_preservesContactCountAndNames() throws Exception {
        Path file = tempDir.resolve("address_book.csv");

        AddressBookManager m1 = new AddressBookManager(file);
        m1.addContact(Contact.fromString("James,,Stevens;Home:123 Maple St|Chicago|IL|60601;Mobile:312-555-0101;Personal:james@example.com".split(";")));
        m1.addContact(Contact.fromString("Maria,Elena,Garcia;Home:55 W Lake St|Chicago|IL|60601;Work:312-555-0199;Personal:maria@example.com".split(";")));
        m1.saveContacts();

        AddressBookManager m2 = new AddressBookManager(file);
        m2.loadContacts();

        assertEquals(2, m2.getContacts().size());
        assertEquals("James Stevens", m2.getContacts().get(0).getFullName());
        assertEquals("Maria Elena Garcia", m2.getContacts().get(1).getFullName());
    }
}
