# Modular CLI Address Book

## Overview

The **Modular CLI Address Book** is a Java-based, backend-focused command-line application 
designed to manage a persistent collection of contacts.

The project emphasizes **domain modeling, validation, persistence, and testability** rather 
than UI concerns. Contacts are stored using a structured, file-based persistence model 
instead of a database, allowing the system to simulate real backend responsibilities while 
remaining portable and easy to test.

---

## Features

- Automatic loading of contacts at application startup
- Add, view, edit, and delete contacts
- Search contacts by name, address, phone number, or email
- Support for multiple addresses, phone numbers, and email addresses per contact
- Structured, file-based persistence with deterministic loading
- Centralized manager/service layer coordinating domain logic
- Defensive parsing and graceful handling of malformed input
- Unit and filesystem-based integration tests using JUnit 5

---

## Architecture Overview

The system follows a **layered, object-oriented architecture**:

### Application Layer
- `Main` initializes application state and starts the CLI session

### Manager / Service Layer
- `AddressBookManager` coordinates loading, saving, searching, and mutating contacts
- Acts as the boundary between persistence and application logic

### Domain Model
- `Contact` aggregates:
  - `Address`
  - `PhoneNumber`
  - `EmailAddress`
- Implements search behavior via a shared `Matchable` interface

### Persistence Layer
- File-based storage using structured text (`address_book.csv`)
- Generic parsing via `FileParser<T>` and `LineParser<T>`
- Explicit load/save responsibilities (`FileLoader`, `FileSaver`)

### Path Management
- `AppPaths` resolves application data paths
- Supports environment-based configuration and test-time path injection

---

## Data Persistence Model

- One CSV-style file: `address_book.csv`
- Each line represents a contact
- Semicolon-delimited tokens

Example:
```
James,,Stevens;Home:123 Maple 
St|Chicago|IL|60601;Mobile:312-555-0101;Personal:james@example.com
```

This approach keeps persistence **explicit, deterministic, and testable**, without 
introducing database complexity.

---

### Error Handling Strategy
The system enforces correctness through defensive programming techniques, including:
- Validation of user input before applying mutations
- Graceful handling of malformed or incomplete data records
- Explicit handling of file I/O failures
- Prevention of invalid edit or delete operations
- Non-fatal handling of load errors to allow partial recovery
Malformed records do not crash the application and are safely ignored where possible, 
allowing the address book to remain usable even when data inconsistencies are encountered.
 
---

## Testing

This project includes **JUnit 5 unit tests and filesystem-based integration tests**.

Tests cover:
- Domain parsing and serialization
- Search behavior
- File parsing logic
- Load/save round-trip persistence
- Manager behavior using isolated temporary directories

Temporary directories are used to ensure no real application data is modified during test 
execution.

### Run tests
```bash
mvn test
```
 
---

## Running the Application

The address book is automatically loaded from the configured data directory at application 
startup.

Run the application using Maven:
```bash
mvn -q exec:java
```

The CLI menu provides options to:
- Add contacts
- View all contacts
- Edit existing contact information
- Delete contacts
- Search the address book
 
---

## Getting Started
### Prerequisites
- Java 17+
- Maven 3.8+
### Run the application
```bash
mvn -q exec:java
```

The application will:
- Create a data/ directory if needed
- Load address_book.csv if present 
- Start an interactive CLI session
 
---

## Configuration

The base data directory can be overridden using the APP_DATA_DIR environment variable.

Example:
```bash
export APP_DATA_DIR=/path/to/data
```
If not set, the application defaults to `./data`.
 
---

## Project Structure
```bash
src/
 ├─ main/
 │   └─ java/
 │       ├─ app/
 │       ├─ addressbook/
 │       │   ├─ manager/
 │       │   ├─ model/
 │       │   ├─ io/
 │       │   └─ input/
 └─ test/
     └─ java/
         └─ addressbook/
```
 
---

## What This Project Demonstrates
- Object-oriented domain modeling
- Separation of concerns
- Deterministic persistence strategies
- Defensive input validation
- Dependency injection for testability
- Unit and integration testing with JUnit 5
- Clean Maven project structure

---
 
## Roadmap / Possible Improvements
- Validation feedback surfaced in the CLI
- CSV schema versioning
- Import/export support
- Additional search filters
- Optional JSON persistence format

---

## License
This project is licensed under the MIT License. See the LICENSE file for details.

---

