package addressbook.io;

import addressbook.model.Contact;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FileParserTest {

    @Test
    void parseLines_skipsBlankLines() {
        FileParser<String> parser =
                new FileParser<>(";", tokens -> tokens[0]);

        List<String> lines = List.of(
                "one;two",
                "",
                "three;four"
        );

        List<String> result = parser.parseLines(lines);

        assertEquals(2, result.size());
        assertEquals("one", result.get(0));
        assertEquals("three", result.get(1));
    }

    @Test
    void parseLines_parsesContactsUsingSemicolonDelimiter() {
        FileParser<Contact> parser =
                new FileParser<>(";", Contact::fromString);

        List<String> lines = List.of(
                "James,,Stevens;Home:123 Maple St|Chicago|IL|60601;Mobile:312-555-0101;Personal:james@example.com",
                "Maria,Elena,Garcia;Home:55 W Lake St|Chicago|IL|60601;Work:312-555-0199;Personal:maria@example.com"
        );

        List<Contact> contacts = parser.parseLines(lines);

        assertEquals(2, contacts.size());
        assertEquals(
                "James Stevens",
                contacts.get(0).getFullName()
        );
        assertEquals(
                "Maria Elena Garcia",
                contacts.get(1).getFullName()
        );
    }

    @Test
    void parseLines_malformedRecord_skipsRecordAndContinues() {
        FileParser<Contact> parser =
                new FileParser<>(";", Contact::fromString);

        List<String> lines = List.of(
                "James,,Stevens;Mobile:312-555-0101",
                "Malformed",
                "Maria,Elena,Garcia;Personal:maria@example.com"
        );

        List<Contact> contacts = parser.parseLines(lines);

        assertEquals(2, contacts.size());
        assertEquals(
                "James Stevens",
                contacts.get(0).getFullName()
        );
        assertEquals(
                "Maria Elena Garcia",
                contacts.get(1).getFullName()
        );
    }
}
