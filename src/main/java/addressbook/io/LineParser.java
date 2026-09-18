package addressbook.io;

/**
 * Defines parsing behavior for converting string tokens into a typed object.
 *
 * @param <T> target object type
 */
@FunctionalInterface
public interface LineParser<T> {

    /**
     * Parses string tokens into an object.
     *
     * @param tokens tokens extracted from a line
     * @return parsed object
     */
    T parse(String[] tokens);
}
