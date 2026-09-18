package addressbook.display;

import addressbook.model.Address;
import addressbook.model.Contact;
import addressbook.model.EmailAddress;
import addressbook.model.PhoneNumber;

/**
 * Formats contacts in a readable, structured format.
 */
public final class ContactDisplayFormatter {

    private static final String INDENT = "     ";
    private static final String NEW_LINE = System.lineSeparator();

    /**
     * Prevents instantiation because this class provides only static utility methods.
     */
    private ContactDisplayFormatter() {
    }

    /**
     * Formats a contact for structured console display.
     *
     * <p>Each address, phone number, and email address is displayed
     * as a separate block for readability.</p>
     *
     * @param contact contact to format
     * @return formatted contact information
     */
    public static String formatContact(Contact contact) {
        StringBuilder sb = new StringBuilder();

        sb.append(contact.getFullName()).append(NEW_LINE);

        for (Address address : contact.getAddresses()) {
            appendBlockSeparator(sb);

            sb.append(address.getLabel()).append(NEW_LINE);
            sb.append(INDENT)
                    .append(address.getStreetAddress())
                    .append(NEW_LINE);
            sb.append(INDENT)
                    .append(address.getCity())
                    .append(", ")
                    .append(address.getState())
                    .append(" ")
                    .append(address.getZipCode())
                    .append(NEW_LINE);
        }

        for (PhoneNumber phone : contact.getPhoneNumbers()) {
            appendBlockSeparator(sb);

            sb.append(phone.getLabel()).append(NEW_LINE);
            sb.append(INDENT)
                    .append(phone.getNumber())
                    .append(NEW_LINE);
        }

        for (EmailAddress email : contact.getEmailAddresses()) {
            appendBlockSeparator(sb);

            sb.append(email.getLabel()).append(NEW_LINE);
            sb.append(INDENT)
                    .append(email.getEmail())
                    .append(NEW_LINE);
        }

        return sb.toString();
    }

    /**
     * Adds a blank line before the next contact-information block.
     *
     * @param sb builder containing the formatted contact
     */
    private static void appendBlockSeparator(StringBuilder sb) {
        sb.append(NEW_LINE);
    }
}
