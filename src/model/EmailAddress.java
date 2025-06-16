package model;
import model.Matchable;

/**
 * Represents an email address with a label (e.g., Personal, Work).
 */
public class EmailAddress implements Matchable {
    private final String label;
    private final String email;

    public EmailAddress(String label, String email) {
        this.label = label;
        this.email = email;
    }

    public String getLabel() {
        return label;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public boolean matches(String query) {
        if (query == null || query.isBlank()) return false;
        String q = query.toLowerCase();
        return label.toLowerCase().contains(q) || email.toLowerCase().contains(q);
    }

    @Override
    public String toString() {
        return label + ":" + email;
    }

    public static EmailAddress fromString(String token) {
        String[] parts = token.split(":");
        return new EmailAddress(parts[0], parts[1]);
    }
}



