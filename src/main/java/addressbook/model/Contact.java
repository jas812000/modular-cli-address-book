package addressbook.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents a contact with a name and collections of addresses,
 * phone numbers, and email addresses.
 */
public class Contact implements Matchable {

    private String firstName;
    private String middleName;
    private String lastName;

    private final List<Address> addresses;
    private final List<PhoneNumber> phoneNumbers;
    private final List<EmailAddress> emailAddresses;

    /**
     * Creates a contact.
     *
     * @param firstName first name
     * @param middleName optional middle name
     * @param lastName last name
     * @param addresses addresses associated with the contact
     * @param phoneNumbers phone numbers associated with the contact
     * @param emailAddresses email addresses associated with the contact
     */
    public Contact(
            String firstName,
            String middleName,
            String lastName,
            List<Address> addresses,
            List<PhoneNumber> phoneNumbers,
            List<EmailAddress> emailAddresses
    ) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.addresses = new ArrayList<>(addresses);
        this.phoneNumbers = new ArrayList<>(phoneNumbers);
        this.emailAddresses = new ArrayList<>(emailAddresses);
    }

    /**
     * Replaces the contact's name.
     *
     * @param firstName first name
     * @param middleName optional middle name
     * @param lastName last name
     */
    public void setName(String firstName, String middleName, String lastName) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
    }

    /**
     * Adds an address to the contact.
     *
     * @param address address to add
     */
    public void addAddress(Address address) {
        addresses.add(address);
    }

    /**
     * Adds a phone number to the contact.
     *
     * @param phoneNumber phone number to add
     */
    public void addPhoneNumber(PhoneNumber phoneNumber) {
        phoneNumbers.add(phoneNumber);
    }

    /**
     * Adds an email address to the contact.
     *
     * @param emailAddress email address to add
     */
    public void addEmailAddress(EmailAddress emailAddress) {
        emailAddresses.add(emailAddress);
    }

    /**
     * Replaces an existing address.
     *
     * @param index zero-based address index
     * @param address replacement address
     */
    public void replaceAddress(int index, Address address) {
        addresses.set(index, address);
    }

    /**
     * Replaces an existing phone number.
     *
     * @param index zero-based phone number index
     * @param phoneNumber replacement phone number
     */
    public void replacePhoneNumber(int index, PhoneNumber phoneNumber) {
        phoneNumbers.set(index, phoneNumber);
    }

    /**
     * Replaces an existing email address.
     *
     * @param index zero-based email address index
     * @param emailAddress replacement email address
     */
    public void replaceEmailAddress(int index, EmailAddress emailAddress) {
        emailAddresses.set(index, emailAddress);
    }

    /**
     * Returns the contact's full name.
     *
     * @return formatted full name
     */
    public String getFullName() {
        if (middleName == null || middleName.isBlank()) {
            return firstName + " " + lastName;
        }

        return firstName + " " + middleName + " " + lastName;
    }

    /**
     * Determines whether the contact or any associated contact information
     * contains the search term.
     *
     * @param query search term
     * @return {@code true} if the contact matches the query
     */
    @Override
    public boolean matches(String query) {
        if (query == null || query.isBlank()) {
            return false;
        }

        String normalizedQuery = query.toLowerCase();

        boolean nameMatch = getFullName().toLowerCase().contains(normalizedQuery)
                || firstName.toLowerCase().contains(normalizedQuery)
                || (middleName != null
                && middleName.toLowerCase().contains(normalizedQuery))
                || lastName.toLowerCase().contains(normalizedQuery);

        boolean addressMatch = addresses.stream()
                .anyMatch(address -> address.matches(normalizedQuery));

        boolean phoneMatch = phoneNumbers.stream()
                .anyMatch(phone -> phone.matches(normalizedQuery));

        boolean emailMatch = emailAddresses.stream()
                .anyMatch(email -> email.matches(normalizedQuery));

        return nameMatch || addressMatch || phoneMatch || emailMatch;
    }

    /**
     * Serializes the contact for persistent storage.
     *
     * @return serialized contact
     */
    @Override
    public String toString() {
        String namePart = firstName
                + ","
                + (middleName == null ? "" : middleName)
                + ","
                + lastName;

        String addressPart = String.join(
                ";",
                addresses.stream().map(Address::toString).toList()
        );

        String phonePart = String.join(
                ";",
                phoneNumbers.stream().map(PhoneNumber::toString).toList()
        );

        String emailPart = String.join(
                ";",
                emailAddresses.stream().map(EmailAddress::toString).toList()
        );

        return namePart + ";" + addressPart + ";" + phonePart + ";" + emailPart;
    }

    /**
     * Creates a contact from tokens produced from its serialized representation.
     *
     * @param tokens serialized contact tokens
     * @return parsed contact
     * @throws IllegalArgumentException if the contact data is malformed
     */
    public static Contact fromString(String[] tokens) {
        if (tokens.length == 0) {
            throw new IllegalArgumentException("Contact record is empty.");
        }

        String[] nameParts = tokens[0].split(",", -1);

        if (nameParts.length != 3
                || nameParts[0].isBlank()
                || nameParts[2].isBlank()) {
            throw new IllegalArgumentException("Malformed contact name.");
        }

        String first = nameParts[0];
        String middle = nameParts[1].isBlank() ? null : nameParts[1];
        String last = nameParts[2];

        List<Address> addresses = new ArrayList<>();
        List<PhoneNumber> phones = new ArrayList<>();
        List<EmailAddress> emails = new ArrayList<>();

        for (int i = 1; i < tokens.length; i++) {
            String token = tokens[i];

            if (token.isBlank()) {
                continue;
            }

            if (token.contains("|")) {
                addresses.add(Address.fromString(token));
            } else if (token.contains("@")) {
                emails.add(EmailAddress.fromString(token));
            } else if (token.contains(":")) {
                phones.add(PhoneNumber.fromString(token));
            } else {
                throw new IllegalArgumentException(
                        "Unrecognized contact token: " + token
                );
            }
        }

        return new Contact(
                first,
                middle,
                last,
                addresses,
                phones,
                emails
        );
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    /**
     * Returns an unmodifiable view of the contact's addresses.
     *
     * @return unmodifiable address list
     */
    public List<Address> getAddresses() {
        return Collections.unmodifiableList(addresses);
    }

    /**
     * Returns an unmodifiable view of the contact's phone numbers.
     *
     * @return unmodifiable phone number list
     */
    public List<PhoneNumber> getPhoneNumbers() {
        return Collections.unmodifiableList(phoneNumbers);
    }

    /**
     * Returns an unmodifiable view of the contact's email addresses.
     *
     * @return unmodifiable email address list
     */
    public List<EmailAddress> getEmailAddresses() {
        return Collections.unmodifiableList(emailAddresses);
    }
}
