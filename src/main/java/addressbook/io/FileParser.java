package addressbook.io;

import java.util.ArrayList;
import java.util.List;

/**
 * Converts raw file lines into typed domain objects using delimiter-based
 * tokenization and delegated object construction.
 *
 * @param <T> type produced by the parser
 */
public class FileParser<T> {

    private final String delimiterRegex;
    private final LineParser<T> parser;

    /**
     * Creates a file parser using the supplied delimiter and token parser.
     *
     * @param delimiterRegex regular expression used to split each line
     * @param parser parser used to convert tokens into an object
     */
    public FileParser(String delimiterRegex, LineParser<T> parser) {
        this.delimiterRegex = delimiterRegex;
        this.parser = parser;
    }

    /**
     * Parses valid, nonblank lines into typed objects.
     * Malformed lines are skipped without preventing valid records
     * from being loaded.
     *
     * @param lines raw file lines
     * @return successfully parsed objects
     */
    public List<T> parseLines(List<String> lines) {
        List<T> result = new ArrayList<>();

        for (String line : lines) {
            if (line.isBlank()) {
                continue;
            }

            try {
                String[] tokens = line.split(delimiterRegex);
                result.add(parser.parse(tokens));
            } catch (IllegalArgumentException | IndexOutOfBoundsException e) {
                System.err.println("Skipping malformed record: " + line);
            }
        }

        return result;
    }
}
