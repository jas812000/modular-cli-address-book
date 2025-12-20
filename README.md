# Modular CLI Address Book

## Overview

The Modular CLI Address Book is a Java-based backend-focused command-line application 
designed to manage a persistent collection of contacts.

The project emphasizes domain modeling, validation, persistence, and testability rather 
than UI concerns. Contacts are stored using a structured, file-based persistence model 
instead of a database, allowing the system to simulate real backend responsibilities while 
remaining portable and easy to reason about.

Each contact supports multiple addresses, phone numbers, and email addresses, enabling 
realistic data modeling and search behavior.

---

## Features
- Automatic loading of contacts at application startup
- Add, view, edit, and delete contacts
- Search contacts by name, address, phone number, or email
- Support for multiple addresses, phone numbers, and emails per contact
- Structured file-based persistence with deterministic loading
- Centralized manager/service layer coordinating domain logic
- Defensive parsing and graceful handling of malformed input
- Clear separation between input handling, domain logic, and persistence
- Designed for automated testing and backend extensibility

---

## Architecture Overview

The system follows a layered, object-oriented architecture:

### Manager / Service Layer

- Central controller responsible for loading contacts, coordinating edits, performing 
searches, and triggering persistence.
- Encapsulates contact collection management and shields the UI from direct data 
manipulation.

### Domain Model
- Contact represents an individual entry and aggregates:
- - Addresses
- - Phone numbers
- - Email addresses
- Shared matching behavior is implemented via a Matchable interface to enable consistent 
search logic across fields.

### Input Layer
- Handles CLI prompts, menus, and user interaction.
- Delegates all business logic to the manager layer.

### Editing Layer
- Specialized editors handle safe modification of contact fields.
- Validation is applied before committing changes.

### Persistence Layer
- Responsible for loading, parsing, and saving contact data.
- Uses structured text storage to maintain deterministic behavior and simplify testing.
- The application is organized into focused packages, each with a single responsibility:

---

## Data Persistence Model
- One directory represents the address book data store
- A single structured text file stores all contacts
- Each contact is serialized in a deterministic, parseable format
- The storage directory is configurable via environment variable

Default path:
```bash
./data/address_book.csv
```

Configurable via:
```
APP_DATA_DIR
```

This approach simulates backend persistence without external dependencies while keeping 
file I/O explicit and testable.

---

## Contact State Management
Contacts are managed as in-memory domain objects with controlled lifecycle operations:
- Creation
- Modification
- Deletion
- Search and retrieval
All mutations are coordinated through the manager layer to preserve consistency and prevent 
invalid state changes.

---

### Error Handling Strategy

The system enforces correctness through defensive programming techniques, including:
- Validation of user input before mutation
- Graceful handling of malformed or incomplete data records
- Explicit handling of file I/O failures
- Prevention of invalid edit or delete operations
- Non-fatal handling of load errors to allow partial recovery

Malformed records do not crash the application and are safely ignored where possible.

---

## Build & Test

### Prerequisites
- Java 17+
- Maven 3.9+

### Run Tests
```bash
mvn test
```

---

## Run (CLI)

The address book is automatically loaded from the configured data directory at application 
startup.
```bash
java -jar target/address-book.jar
```

The CLI menu provides options to:
- Add contacts
- View all contacts
- Edit existing contact information
- Delete contacts
- Search the address book

---

##Tools & Technologies
- **Language**: Java 17+
- **Build Tool**: Maven
- **Testing**: JUnit 5
- **Persistence**: Structured text file storage
- **Design Principles**: Object-oriented design, separation of concerns
- **Testing Techniques**: Unit tests and filesystem-based integration tests

---

### Purpose

This project serves as a backend engineering case study demonstrating:
- Object-oriented domain modeling
- Separation of concerns in a layered architecture
- Deterministic file-based persistence strategies
- Defensive input validation and error handling
- Search and matching logic across composite domain objects
- Translation of backend design principles into a working Java application

---

## License
This project is licensed under the MIT License. See the LICENSE file for details.

--- 
