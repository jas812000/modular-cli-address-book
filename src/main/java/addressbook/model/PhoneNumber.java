package addressbook.model;

/**
 * Represents a labeled phone number associated with a contact.
 */
public class PhoneNumber implements Matchable {

    private final String label;
    private final String number;

    /**
     * Creates a phone number.
     *
     * @param label descriptive label such as Mobile, Home, or Work
     * @param number phone number
     */
    public PhoneNumber(String label, String number) {
        this.label = label;
        this.number = number;
    }

    /**
     * Returns the phone number label.
     *
     * @return phone number label
     */
    public String getLabel() {
        return label;
    }

    /**
     * Returns the phone number.
     *
     * @return phone number
     */
    public String getNumber() {
        return number;
    }

    /**
     * Determines whether the label or phone number contains the search term.
     * Phone-number comparisons ignore formatting characters.
     *
     * @param query search term
     * @return {@code true} if this phone number matches the query
     */
    @Override
    public boolean matches(String query) {
        if (query == null || query.isBlank()) {
            return false;
        }

        String normalizedQuery = query.trim().toLowerCase();

        if (label.toLowerCase().contains(normalizedQuery)) {
            return true;
        }

        String queryDigits = normalizedQuery.replaceAll("\\D", "");
        String numberDigits = number.replaceAll("\\D", "");

        return !queryDigits.isEmpty() && numberDigits.contains(queryDigits);
    }

    /**
     * Serializes the phone number for persistent storage.
     *
     * @return serialized phone number
     */
    @Override
    public String toString() {
        return label + ":" + number;
    }

    /**
     * Creates a phone number from its serialized representation.
     *
     * @param token serialized phone number
     * @return parsed phone number
     * @throws IllegalArgumentException if the token is malformed
     */
    public static PhoneNumber fromString(String token) {
        String[] parts = token.split(":", 2);

        if (parts.length != 2) {
            throw new IllegalArgumentException("Malformed phone number: " + token);
        }

        return new PhoneNumber(parts[0], parts[1]);
    }
}
