package addressbook.io;

/**
 * Functional interface for converting an array of string tokens into a typed object.
 * Used to decouple file parsing from object construction
 *
 * @param <T> the target object type
 */
@FunctionalInterface
public interface LineParser<T> {

    /**
     * Parses an array of string tokens into an object of type T.
     *
     * @param tokens the tokens extracted from a line
     * @return a parsed object of type T
     */
    T parse(String[] tokens);
}

