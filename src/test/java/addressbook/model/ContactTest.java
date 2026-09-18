package addressbook.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ContactTest {

    @Test
    void getFullName_withoutMiddleName_formatsCorrectly() {
        Contact contact = new Contact(
                "James",
                null,
                "Stevens",
                List.of(),
                List.of(),
                List.of()
        );

        assertEquals("James Stevens", contact.getFullName());
    }

    @Test
    void getFullName_withMiddleName_formatsCorrectly() {
        Contact contact = new Contact(
                "Maria",
                "Elena",
                "Garcia",
                List.of(),
                List.of(),
                List.of()
        );

        assertEquals("Maria Elena Garcia", contact.getFullName());
    }

    @Test
    void matches_blankQuery_returnsFalse() {
        Contact contact = new Contact(
                "Devon",
                null,
                "Nguyen",
                List.of(),
                List.of(),
                List.of()
        );

        assertFalse(contact.matches(null));
        assertFalse(contact.matches(""));
        assertFalse(contact.matches("   "));
    }

    @Test
    void matches_name_isCaseInsensitiveAndPartial() {
        Contact contact = new Contact(
                "Aisha",
                "Renee",
                "Johnson",
                List.of(),
                List.of(),
                List.of()
        );

        assertTrue(contact.matches("aisha"));
        assertTrue(contact.matches("RENEE"));
        assertTrue(contact.matches("john"));
        assertTrue(contact.matches("Aisha Renee"));
    }

    @Test
    void toStringAndFromString_roundTrip_preservesContactData() {
        Address address = Address.fromString(
                "Home:123 Maple St|Chicago|IL|60601"
        );
        PhoneNumber phone = PhoneNumber.fromString(
                "Mobile:312-555-0101"
        );
        EmailAddress email = EmailAddress.fromString(
                "Personal:james.stevens@example.com"
        );

        Contact original = new Contact(
                "James",
                null,
                "Stevens",
                List.of(address),
                List.of(phone),
                List.of(email)
        );

        Contact parsed = Contact.fromString(
                original.toString().split(";")
        );

        assertEquals("James", parsed.getFirstName());
        assertNull(parsed.getMiddleName());
        assertEquals("Stevens", parsed.getLastName());
        assertEquals("James Stevens", parsed.getFullName());

        assertEquals(1, parsed.getAddresses().size());
        assertEquals(1, parsed.getPhoneNumbers().size());
        assertEquals(1, parsed.getEmailAddresses().size());
    }

    @Test
    void constructor_defensivelyCopiesCollections() {
        List<Address> addresses = new ArrayList<>();
        addresses.add(
                new Address(
                        "Home",
                        "123 Maple St",
                        "Chicago",
                        "IL",
                        "60601"
                )
        );

        Contact contact = new Contact(
                "James",
                null,
                "Stevens",
                addresses,
                List.of(),
                List.of()
        );

        addresses.clear();

        assertEquals(1, contact.getAddresses().size());
    }

    @Test
    void collectionGetters_areUnmodifiable() {
        Contact contact = new Contact(
                "James",
                null,
                "Stevens",
                List.of(
                        new Address(
                                "Home",
                                "123 Maple St",
                                "Chicago",
                                "IL",
                                "60601"
                        )
                ),
                List.of(
                        new PhoneNumber(
                                "Mobile",
                                "312-555-0101"
                        )
                ),
                List.of(
                        new EmailAddress(
                                "Personal",
                                "james@example.com"
                        )
                )
        );

        assertThrows(
                UnsupportedOperationException.class,
                () -> contact.getAddresses().clear()
        );
        assertThrows(
                UnsupportedOperationException.class,
                () -> contact.getPhoneNumbers().clear()
        );
        assertThrows(
                UnsupportedOperationException.class,
                () -> contact.getEmailAddresses().clear()
        );
    }

    @Test
    void replacementMethods_replaceExistingContactData() {
        Contact contact = new Contact(
                "James",
                null,
                "Stevens",
                List.of(
                        new Address(
                                "Home",
                                "123 Maple St",
                                "Chicago",
                                "IL",
                                "60601"
                        )
                ),
                List.of(
                        new PhoneNumber(
                                "Mobile",
                                "312-555-0101"
                        )
                ),
                List.of(
                        new EmailAddress(
                                "Personal",
                                "old@example.com"
                        )
                )
        );

        contact.replaceAddress(
                0,
                new Address(
                        "Work",
                        "500 Main St",
                        "Dallas",
                        "TX",
                        "75201"
                )
        );
        contact.replacePhoneNumber(
                0,
                new PhoneNumber(
                        "Work",
                        "214-555-0199"
                )
        );
        contact.replaceEmailAddress(
                0,
                new EmailAddress(
                        "Work",
                        "new@example.com"
                )
        );

        assertEquals(
                "Work",
                contact.getAddresses().get(0).getLabel()
        );
        assertEquals(
                "214-555-0199",
                contact.getPhoneNumbers().get(0).getNumber()
        );
        assertEquals(
                "new@example.com",
                contact.getEmailAddresses().get(0).getEmail()
        );
    }

    @Test
    void addMethods_appendContactData() {
        Contact contact = new Contact(
                "James",
                null,
                "Stevens",
                List.of(),
                List.of(),
                List.of()
        );

        contact.addAddress(
                new Address(
                        "Vacation Home",
                        "200 Beach Road",
                        "Galveston",
                        "TX",
                        "77550"
                )
        );

        contact.addPhoneNumber(
                new PhoneNumber(
                        "Emergency",
                        "469-555-0201"
                )
        );

        contact.addEmailAddress(
                new EmailAddress(
                        "School",
                        "james@school.edu"
                )
        );

        assertEquals(1, contact.getAddresses().size());
        assertEquals(
                "Vacation Home",
                contact.getAddresses().get(0).getLabel()
        );

        assertEquals(1, contact.getPhoneNumbers().size());
        assertEquals(
                "469-555-0201",
                contact.getPhoneNumbers().get(0).getNumber()
        );

        assertEquals(1, contact.getEmailAddresses().size());
        assertEquals(
                "james@school.edu",
                contact.getEmailAddresses().get(0).getEmail()
        );
    }


    @Test
    void fromString_malformedName_throwsIllegalArgumentException() {
        assertThrows(
                IllegalArgumentException.class,
                () -> Contact.fromString(
                        new String[]{"James,Stevens"}
                )
        );
    }
}
