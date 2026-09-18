package addressbook.model;

/**
 * Defines behavior for domain objects that can be searched using
 * a query string.
 */
public interface Matchable {

    /**
     * Determines whether this object contains the supplied search term
     * in any searchable field.
     *
     * @param query search term
     * @return {@code true} if this object matches the query
     */
    boolean matches(String query);
}
