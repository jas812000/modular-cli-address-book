# Modular CLI Address Book

A Java 17 command-line application for managing and persistently storing contacts using a modular, layered architecture.

The project focuses on object-oriented domain modeling, separation of concerns, defensive input handling, file-based persistence, and automated testing.

## Features

- Automatically loads saved contacts at application startup
- Add, view, edit, delete, and search contacts
- Search by name, address, phone number, or email
- Support multiple addresses, phone numbers, and email addresses per contact
- Support standard and custom labels for contact information
- Persist contacts to structured text storage
- Configure the application data directory with an environment variable
- Skip malformed persisted records while continuing to load valid contacts
- Protect internal contact collections from uncontrolled external modification
- Centralize console input through a single input utility
- Validate menu selections and other interactive choices
- Unit and filesystem-based integration testing with JUnit 5

## Architecture

The application separates responsibilities across focused packages.

### Application

`app.Main` is the application entry point. It creates the address book manager and starts the interactive CLI session.

### Manager

`AddressBookManager` coordinates contact storage, loading, saving, and mutation while protecting its internal contact collection from direct modification.

### Domain Model

`Contact` represents an address book entry and aggregates:

- `Address`
- `PhoneNumber`
- `EmailAddress`

Searchable domain objects implement the shared `Matchable` interface.

`Contact` uses defensive copies and unmodifiable collection views to protect its internal state. Explicit addition and replacement methods provide controlled modification of contact data.

### Input and Editing

The `input` package handles interactive CLI workflows, including contact creation, display, search, and deletion.

The `editor` package handles modifications to existing contact information.

Console input is centralized through `PromptUtils`, which owns the application's single `Scanner` for `System.in`.

### Display

`ContactDisplayFormatter` provides structured formatting of contact information for console output.

### Persistence

The `io` package provides file-based persistence through:

- `FileLoader`
- `FileSaver`
- `FileParser<T>`
- `LineParser<T>`
- `AppPaths`

Parsing and object construction are separated so persistence logic remains reusable and testable.

## Data Persistence

Contacts are stored in:

```text
data/address_book.csv
```

Each line represents one contact using semicolon-delimited components.

Example:

```text
James,,Stevens;Home:123 Maple St|Chicago|IL|60601;Mobile:312-555-0101;Personal:james@example.com
```

The application skips blank lines and malformed records during loading so one invalid record does not prevent valid contacts from being recovered.

## Error Handling

The CLI handles invalid interactive selections without terminating the application. Yes/no prompts require recognized responses, invalid numeric selections are rejected safely, and active operations can be cancelled with `exit` without terminating the application.

Persisted domain tokens are validated during parsing. Malformed records are skipped while valid records continue to load.

File I/O failures are handled by the application session rather than silently ignored.

## Testing

The project uses JUnit 5 for unit and filesystem-based integration testing.

The current automated suite contains **25 tests** covering:

- Contact name formatting
- Case-insensitive and partial search behavior
- Phone-number search independent of display formatting
- Domain parsing and serialization
- Malformed domain tokens
- Contact serialization round trips
- Defensive collection copying
- Unmodifiable collection views
- Controlled addition and replacement of contact data
- File parsing and blank-line handling
- Recovery from malformed persisted records
- File save/load round trips
- Manager persistence behavior
- Contact removal
- Protection of the manager's internal contact collection

Filesystem tests use JUnit temporary directories so application data is not modified during testing.

Run the test suite with:

```bash
mvn test
```

## Getting Started

### Prerequisites

- Java 17 or later
- Maven 3.8 or later

### Run with Maven

```bash
mvn -q exec:java
```

The application will create the data directory when necessary, load `address_book.csv` when present, and start the interactive CLI session.

### Build the Runnable JAR

```bash
mvn clean package
```

Then run:

```bash
java -jar target/modular-cli-address-book-1.0.0-SNAPSHOT.jar
```

## Configuration

By default, application data is stored in:

```text
./data
```

Override the base data directory with the `APP_DATA_DIR` environment variable:

```bash
export APP_DATA_DIR=/path/to/data
mvn -q exec:java
```

This also allows application data to be isolated from the repository when desired.

## Project Structure

```text
src/
├── main/
│   └── java/
│       ├── app/
│       └── addressbook/
│           ├── display/
│           ├── editor/
│           ├── input/
│           ├── io/
│           ├── manager/
│           └── model/
└── test/
    └── java/
        └── addressbook/
            ├── io/
            ├── manager/
            └── model/
```

## What This Project Demonstrates

- Java 17
- Maven
- Object-oriented domain modeling
- Layered application design
- Separation of concerns
- Encapsulation and defensive copying
- Generic parsing abstractions
- File-based persistence
- Defensive input and persistence handling
- Environment-based configuration
- JUnit 5 unit testing
- Filesystem integration testing
- Runnable JAR packaging

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.
