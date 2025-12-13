# Modular CLI Address Book

A modular Java command-line address book application that supports creating, viewing, searching, editing, and deleting contacts with persistent file storage.

---

## Overview

This project implements a fully functional address book using a command-line interface.  
It emphasizes clean separation of concerns, extensible domain modeling, and reliable file-based persistence.

Each contact can contain multiple labeled addresses, phone numbers, and email addresses.  
Data is saved to disk and restored across application runs.

---

## Key Features

- Interactive CLI menu for managing contacts
- Support for multiple addresses, phone numbers, and emails per contact
- Search by name, address, phone number, or email
- Modular editors for targeted updates (name, address, phone, email)
- Consistent, formatted contact display
- Persistent storage using a structured text format
- Configurable data directory via environment variable

---

## Architecture & Design

The application is organized into focused packages, each with a single responsibility:

- **input** – User prompts, menu flow, and session control  
- **editor** – Modular editors for modifying specific contact fields  
- **display** – Centralized contact formatting and presentation  
- **model** – Domain objects and search-matching behavior  
- **manager** – In-memory contact management and persistence coordination  
- **io** – File I/O, parsing utilities, and runtime path configuration  

Search behavior is implemented through a shared `Matchable` interface, allowing consistent query handling across domain objects.

File persistence is abstracted through reusable loader, saver, and parser utilities.

---

## Data Persistence

Contacts are stored in a structured, delimiter-based text file.

- Each contact is serialized to a single line
- Nested data (addresses, phones, emails) is encoded with predictable token separators
- Files are loaded at application startup and saved on exit

The storage location can be configured at runtime using an environment variable.

---

## Configuration

By default, data is stored in a local `data/` directory.

To override the base directory, set the environment variable:

```bash
APP_DATA_DIR=/path/to/data
```

## Technologies Used
- Java
- Java NIO (Path, Files)
- Command-line I/O
- Object-oriented design
- Modular application structure

---

## What This Project Demonstrates
- Clean modular design in a non-trivial CLI application
- Practical file persistence without external frameworks
- Searchable domain models with minimal coupling
- Maintainable separation between UI flow, domain logic, and storage
- Engineering-focused problem solving in a constrained interface

---

## License
© 2025 James Stevens. All rights reserved.

This repository is provided for educational, evaluation, and portfolio review purposes. You may clone and run the code locally for non-commercial review.

Commercial use, redistribution, or modification beyond review purposes requires explicit permission from the author.

--- 
