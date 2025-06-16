package display;

import model.Contact;
import model.Address;
import model.PhoneNumber;
import model.EmailAddress;

import java.util.List;

/**
 * Utility class for displaying contacts in a readable, structured format.
 */
public class ContactDisplayFormatter {

    private static final String INDENT = "     "; // 5 spaces
    private static final String SEPARATOR = "---------------";

    /**
     * Displays a list of contacts in a clean, indented format.
     *
     * @param contacts List of contacts to display.
     */
    public static void displayFormatted(List<Contact> contacts) {
        for (Contact contact : contacts) {
            System.out.println(formatContact(contact));
            System.out.println(SEPARATOR);
        }
    }

    /**
     * Formats a single contact into the structured display string.
     *
     * @param contact The contact to format.
     * @return The formatted string.
     */
    public static String formatContact(Contact contact) {
        StringBuilder sb = new StringBuilder();

        // 1. Full name (single line)
        sb.append(contact.getFullName()).append("\n");

        // 2. Addresses
        for (Address addr : contact.getAddresses()) {
            sb.append(addr.getLabel()).append("\n");
            sb.append(INDENT).append(addr.getStreetAddress()).append("\n");
            sb.append(INDENT)
                    .append(addr.getCity()).append(", ")
                    .append(addr.getState()).append(" ")
                    .append(addr.getZip()).append("\n");
        }

        // 3. Phone Numbers
        for (PhoneNumber phone : contact.getPhoneNumbers()) {
            sb.append(phone.getLabel()).append("\n");
            sb.append(INDENT).append(phone.getNumber()).append("\n");
        }

        // 4. Emails
        for (EmailAddress email : contact.getEmailAddresses()) {
            sb.append(email.getLabel()).append("\n");
            sb.append(INDENT).append(email.getEmail()).append("\n");
        }

        return sb.toString();
    }
}

