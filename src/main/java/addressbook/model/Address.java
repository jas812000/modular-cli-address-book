package addressbook.model;

import addressbook.model.Matchable;

/**
 * Represents a physical address with a label (e.g., Home, Work, PO Box).
 */
public class Address implements Matchable {
    private final String label;
    private final String streetAddress;
    private final String city;
    private final String state;
    private final String zip;

    public Address(String label, String streetAddress, String city, String state, String zip) {
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

    public String getZip() {
        return zip;
    }

    @Override
    public boolean matches(String query) {
        if (query == null || query.isBlank()) return false;
        String q = query.toLowerCase();
        return label.toLowerCase().contains(q) ||
                streetAddress.toLowerCase().contains(q) ||
                city.toLowerCase().contains(q) ||
                state.toLowerCase().contains(q) ||
                zip.toLowerCase().contains(q);
    }

    @Override
    public String toString() {
        return label + ":" + streetAddress + "|" + city + "|" + state + "|" + zip;
    }

    public static Address fromString(String token) {
        String[] parts = token.split(":", 2);
        String[] fields = parts[1].split("\\|");
        return new Address(parts[0], fields[0], fields[1], fields[2], fields[3]);
    }
}



