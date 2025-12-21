package addressbook.io;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FileIoIntegrationTest {

    @TempDir
    Path tempDir;

    @Test
    void saveThenLoad_roundTrip_preservesLines() throws Exception {
        Path file = tempDir.resolve("address_book.csv");

        List<String> lines = List.of(
                "James,,Stevens;Home:123 Maple St|Chicago|IL|60601;Mobile:312-555-0101;Personal:james@example.com",
                "Maria,Elena,Garcia;Home:55 W Lake St|Chicago|IL|60601;Work:312-555-0199;Personal:maria@example.com"
        );

        FileSaver.saveLines(file, lines);
        List<String> loaded = FileLoader.loadLines(file);

        assertEquals(lines, loaded);
    }

    @Test
    void fileExists_falseForMissingFile() {
        Path missing = tempDir.resolve("missing.csv");
        assertFalse(FileLoader.fileExists(missing));
    }
}
