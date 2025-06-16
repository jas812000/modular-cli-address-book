package model;

import java.util.*;

/**
 * Represents a full contact entry in the address book.
 * Supports multiple named addresses, phone numbers, and emails.
 */
public class Contact implements Matchable {

    private String firstName;
    private String middleName; // nullable
    private String lastName;

    private final List<Address> addresses;
    private final List<PhoneNumber> phoneNumbers;
    private final List<EmailAddress> emailAddresses;

    public Contact(String firstName, String middleName, String lastName,
                   List<Address> addresses,
                   List<PhoneNumber> phoneNumbers,
                   List<EmailAddress> emailAddresses) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.addresses = addresses;
        this.phoneNumbers = phoneNumbers;
        this.emailAddresses = emailAddresses;
    }

    /**
     * Sets a new name for the contact.
     */
    public void setName(String firstName, String middleName, String lastName) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
    }

    /**
     * Returns the contact's full name as a single formatted string.
     */
    public String getFullName() {
        if (middleName == null || middleName.isBlank()) {
            return firstName + " " + lastName;
        }
        return firstName + " " + middleName + " " + lastName;
    }

    @Override
    public boolean matches(String query) {
        if (query == null || query.isBlank()) return false;
        String q = query.toLowerCase();

        boolean nameMatch = getFullName().toLowerCase().contains(q) ||
                firstName.toLowerCase().contains(q) ||
                (middleName != null && middleName.toLowerCase().contains(q)) ||
                lastName.toLowerCase().contains(q);

        boolean addressMatch = addresses.stream().anyMatch(a -> a.matches(q));
        boolean phoneMatch = phoneNumbers.stream().anyMatch(p -> p.matches(q));
        boolean emailMatch = emailAddresses.stream().anyMatch(e -> e.matches(q));

        return nameMatch || addressMatch || phoneMatch || emailMatch;
    }

    @Override
    public String toString() {
        String namePart = firstName + "," + (middleName == null ? "" : middleName) + "," + lastName;

        String addressPart = String.join(";", addresses.stream().map(Address::toString).toList());
        String phonePart = String.join(";", phoneNumbers.stream().map(PhoneNumber::toString).toList());
        String emailPart = String.join(";", emailAddresses.stream().map(EmailAddress::toString).toList());

        return namePart + ";" + addressPart + ";" + phonePart + ";" + emailPart;
    }

    public static Contact fromString(String[] tokens) {
        String[] nameParts = tokens[0].split(",");
        String first = nameParts[0];
        String middle = nameParts[1].isBlank() ? null : nameParts[1];
        String last = nameParts[2];

        List<Address> addresses = new ArrayList<>();
        List<PhoneNumber> phones = new ArrayList<>();
        List<EmailAddress> emails = new ArrayList<>();

        for (int i = 1; i < tokens.length; i++) {
            String token = tokens[i];
            if (token.contains("|")) {
                addresses.add(Address.fromString(token));
            } else if (token.contains("@")) {
                emails.add(EmailAddress.fromString(token));
            } else if (token.contains(":")) {
                phones.add(PhoneNumber.fromString(token));
            } else {
                System.err.println("Unrecognized token: " + token);
            }
        }

        return new Contact(first, middle, last, addresses, phones, emails);
    }

    public String getFirstName() { return firstName; }
    public String getMiddleName() { return middleName; }
    public String getLastName() { return lastName; }
    public List<Address> getAddresses() { return addresses; }
    public List<PhoneNumber> getPhoneNumbers() { return phoneNumbers; }
    public List<EmailAddress> getEmailAddresses() { return emailAddresses; }
}
