package addressbook.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TokenParsingTest {

    @Test
    void address_parsesAndSerializes() {
        Address a = Address.fromString("Home:123 Maple St|Chicago|IL|60601");
        assertNotNull(a);
        assertTrue(a.toString().startsWith("Home:"));
        assertTrue(a.toString().contains("|"));
    }

    @Test
    void phoneNumber_parsesAndSerializes() {
        PhoneNumber p = PhoneNumber.fromString("Mobile:312-555-0101");
        assertNotNull(p);
        assertTrue(p.toString().startsWith("Mobile:"));
        assertTrue(p.toString().matches(".*\\d.*"));
    }

    @Test
    void emailAddress_parsesAndSerializes() {
        EmailAddress e = EmailAddress.fromString("Personal:james@example.com");
        assertNotNull(e);
        assertTrue(e.toString().startsWith("Personal:"));
        assertTrue(e.toString().contains("@"));
    }
}
