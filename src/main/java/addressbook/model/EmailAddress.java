package addressbook.model;

/**
 * Represents a labeled email address associated with a contact.
 */
public class EmailAddress implements Matchable {

    private final String label;
    private final String email;

    /**
     * Creates an email address.
     *
     * @param label descriptive label such as Personal or Work
     * @param email email address
     */
    public EmailAddress(String label, String email) {
        this.label = label;
        this.email = email;
    }

    /**
     * Returns the email address label.
     *
     * @return email address label
     */
    public String getLabel() {
        return label;
    }

    /**
     * Returns the email address.
     *
     * @return email address
     */
    public String getEmail() {
        return email;
    }

    /**
     * Determines whether the label or email address contains the search term.
     *
     * @param query search term
     * @return {@code true} if this email address matches the query
     */
    @Override
    public boolean matches(String query) {
        if (query == null || query.isBlank()) {
            return false;
        }

        String normalizedQuery = query.toLowerCase();

        return label.toLowerCase().contains(normalizedQuery)
                || email.toLowerCase().contains(normalizedQuery);
    }

    /**
     * Serializes the email address for persistent storage.
     *
     * @return serialized email address
     */
    @Override
    public String toString() {
        return label + ":" + email;
    }

    /**
     * Creates an email address from its serialized representation.
     *
     * @param token serialized email address
     * @return parsed email address
     * @throws IllegalArgumentException if the token is malformed
     */
    public static EmailAddress fromString(String token) {
        String[] parts = token.split(":", 2);

        if (parts.length != 2) {
            throw new IllegalArgumentException("Malformed email address: " + token);
        }

        return new EmailAddress(parts[0], parts[1]);
    }
}

