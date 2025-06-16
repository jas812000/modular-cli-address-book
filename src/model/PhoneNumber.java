package model;

import model.Matchable;

/**
 * Represents a phone number with a label (e.g., Mobile, Home, Work).
 */
public class PhoneNumber implements Matchable {
    private final String label;
    private final String number;

    public PhoneNumber(String label, String number) {
        this.label = label;
        this.number = number;
    }

    public String getLabel() {
        return label;
    }

    public String getNumber() {
        return number;
    }

    @Override
    public boolean matches(String query) {
        if (query == null || query.isBlank()) return false;
        String q = query.toLowerCase();
        return label.toLowerCase().contains(q) || number.toLowerCase().contains(q);
    }

    @Override
    public String toString() {
        return label + ":" + number;
    }

    public static PhoneNumber fromString(String token) {
        String[] parts = token.split(":");
        return new PhoneNumber(parts[0], parts[1]);
    }
}
