package addressbook.model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ContactTest {

    @Test
    void getFullName_withoutMiddleName_formatsCorrectly() {
        Contact c = new Contact("James", null, "Stevens", List.of(), List.of(), List.of());
        assertEquals("James Stevens", c.getFullName());
    }

    @Test
    void getFullName_withMiddleName_formatsCorrectly() {
        Contact c = new Contact("Maria", "Elena", "Garcia", List.of(), List.of(), List.of());
        assertEquals("Maria Elena Garcia", c.getFullName());
    }

    @Test
    void matches_blankQuery_returnsFalse() {
        Contact c = new Contact("Devon", null, "Nguyen", List.of(), List.of(), List.of());
        assertFalse(c.matches(null));
        assertFalse(c.matches(""));
        assertFalse(c.matches("   "));
    }

    @Test
    void matches_name_isCaseInsensitiveAndPartial() {
        Contact c = new Contact("Aisha", "Renee", "Johnson", List.of(), List.of(), List.of());
        assertTrue(c.matches("aisha"));
        assertTrue(c.matches("RENEE"));
        assertTrue(c.matches("john"));
        assertTrue(c.matches("Aisha Renee"));
    }

    @Test
    void toString_and_fromString_roundTrip_preservesNamesAndCounts() {
        Address addr = Address.fromString("Home:123 Maple St|Chicago|IL|60601");
        PhoneNumber phone = PhoneNumber.fromString("Mobile:312-555-0101");
        EmailAddress email = EmailAddress.fromString("Personal:james.stevens@example.com");

        Contact original = new Contact(
                "James", null, "Stevens",
                List.of(addr),
                List.of(phone),
                List.of(email)
        );

        String serialized = original.toString();
        String[] tokens = serialized.split(";");

        Contact parsed = Contact.fromString(tokens);

        assertEquals("James", parsed.getFirstName());
        assertNull(parsed.getMiddleName());
        assertEquals("Stevens", parsed.getLastName());

        assertEquals(1, parsed.getAddresses().size());
        assertEquals(1, parsed.getPhoneNumbers().size());
        assertEquals(1, parsed.getEmailAddresses().size());

        assertEquals("James Stevens", parsed.getFullName());
    }
}
