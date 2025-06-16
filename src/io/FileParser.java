package io;

import java.util.ArrayList;
import java.util.List;

/**
 * Parses a list of lines into objects using a delimiter and a mapping function.
 *
 * @param <T> the type of object to return
 */
public class FileParser<T> {

    private final String delimiterRegex;
    private final LineParser<T> parser;

    /**
     * Creates a parser using a delimiter and a line parser.
     *
     * @param delimiterRegex regex to split lines (e.g., ",", "\\s+", ";")
     * @param parser logic to convert split tokens to an object
     */
    public FileParser(String delimiterRegex, LineParser<T> parser) {
        this.delimiterRegex = delimiterRegex;
        this.parser = parser;
    }

    /**
     * Parses all lines into a list of objects.
     *
     * @param lines list of raw lines from file
     * @return list of parsed objects
     */
    public List<T> parseLines(List<String> lines) {
        List<T> result = new ArrayList<>();
        for (String line : lines) {
            if (line.isBlank()) continue;
            String[] tokens = line.split(delimiterRegex);
            result.add(parser.parse(tokens));
        }
        return result;
    }
}

