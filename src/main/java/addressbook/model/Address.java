package addressbook.model;

/**
 * Represents a labeled physical address associated with a contact.
 */
public class Address implements Matchable {

    private final String label;
    private final String streetAddress;
    private final String city;
    private final String state;
    private final String zip;

    /**
     * Creates a physical address.
     *
     * @param label descriptive label such as Home, Work, or PO Box
     * @param streetAddress street address
     * @param city city
     * @param state state
     * @param zip ZIP code
     */
    public Address(
            String label,
            String streetAddress,
            String city,
            String state,
            String zip
    ) {
        this.label = label;
        this.streetAddress = streetAddress;
        this.city = city;
        this.state = state;
        this.zip = zip;
    }

    public String getLabel() {
        return label;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getZipCode() {
        return zip;
    }

    /**
     * Determines whether any address field contains the search term.
     *
     * @param query search term
     * @return {@code true} if this address matches the query
     */
    @Override
    public boolean matches(String query) {
        if (query == null || query.isBlank()) {
            return false;
        }

        String normalizedQuery = query.toLowerCase();

        return label.toLowerCase().contains(normalizedQuery)
                || streetAddress.toLowerCase().contains(normalizedQuery)
                || city.toLowerCase().contains(normalizedQuery)
                || state.toLowerCase().contains(normalizedQuery)
                || zip.toLowerCase().contains(normalizedQuery);
    }

    /**
     * Serializes the address for persistent storage.
     *
     * @return serialized address
     */
    @Override
    public String toString() {
        return label + ":" + streetAddress + "|" + city + "|" + state + "|" + zip;
    }

    /**
     * Creates an address from its serialized representation.
     *
     * @param token serialized address
     * @return parsed address
     * @throws IllegalArgumentException if the token is malformed
     */
    public static Address fromString(String token) {
        String[] parts = token.split(":", 2);

        if (parts.length != 2) {
            throw new IllegalArgumentException("Malformed address: " + token);
        }

        String[] fields = parts[1].split("\\|", -1);

        if (fields.length != 4) {
            throw new IllegalArgumentException("Malformed address: " + token);
        }

        return new Address(
                parts[0],
                fields[0],
                fields[1],
                fields[2],
                fields[3]
        );
    }
}
