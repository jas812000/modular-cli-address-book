package addressbook.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TokenParsingTest {

    @Test
    void address_parsesAndSerializes() {
        Address address = Address.fromString(
                "Home:123 Maple St|Chicago|IL|60601"
        );

        assertEquals("Home", address.getLabel());
        assertEquals("123 Maple St", address.getStreetAddress());
        assertEquals("Chicago", address.getCity());
        assertEquals("IL", address.getState());
        assertEquals("60601", address.getZipCode());
        assertEquals(
                "Home:123 Maple St|Chicago|IL|60601",
                address.toString()
        );
    }

    @Test
    void address_malformedToken_throwsIllegalArgumentException() {
        assertThrows(
                IllegalArgumentException.class,
                () -> Address.fromString("Home:123 Maple St|Chicago")
        );
    }

    @Test
    void phoneNumber_parsesAndSerializes() {
        PhoneNumber phone = PhoneNumber.fromString(
                "Mobile:312-555-0101"
        );

        assertEquals("Mobile", phone.getLabel());
        assertEquals("312-555-0101", phone.getNumber());
        assertEquals(
                "Mobile:312-555-0101",
                phone.toString()
        );
    }

    @Test
    void phoneNumber_matchesDigits_ignoresFormatting() {
        PhoneNumber phone = new PhoneNumber(
                "Emergency",
                "512-555-0199"
        );

        assertTrue(phone.matches("5125550199"));
        assertTrue(phone.matches("5550199"));
        assertTrue(phone.matches("emergency"));
        assertFalse(phone.matches("2145550100"));
    }


    @Test
    void phoneNumber_malformedToken_throwsIllegalArgumentException() {
        assertThrows(
                IllegalArgumentException.class,
                () -> PhoneNumber.fromString("Mobile")
        );
    }

    @Test
    void emailAddress_parsesAndSerializes() {
        EmailAddress email = EmailAddress.fromString(
                "Personal:james@example.com"
        );

        assertEquals("Personal", email.getLabel());
        assertEquals("james@example.com", email.getEmail());
        assertEquals(
                "Personal:james@example.com",
                email.toString()
        );
    }

    @Test
    void emailAddress_malformedToken_throwsIllegalArgumentException() {
        assertThrows(
                IllegalArgumentException.class,
                () -> EmailAddress.fromString("james@example.com")
        );
    }
}
