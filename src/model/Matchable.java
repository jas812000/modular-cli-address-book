package model;

/**
 * Represents an object that can be searched using a query string.
 */
public interface Matchable {
    /**
     * Checks if the object matches the given query string.
     *
     * @param query The search term to match.
     * @return true if the object contains the query in any relevant field.
     */
    boolean matches(String query);
}
