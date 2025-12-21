package addressbook.io;

import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Centralized path resolution utility for application data.
 *
 * Allows the base data directory to be configured at runtime
 * using an environment variable, with a safe default fallback.
 *
 * Design goals:
 * - No static Path constants (avoids hard-to-test global state)
 * - Methods are deterministic and easy to override in tests
 * - Directory creation is explicit via ensure* methods
 */
public class AppPaths {

    private static final String ENV_VAR = "APP_DATA_DIR";
    private static final String DEFAULT_DIR = "data";
    private static final String ADDRESS_BOOK_FILENAME = "address_book.csv";

    private AppPaths() {}

    /** Production default base directory (env var override). */
    public static Path baseDirectory() {
        String env = System.getenv(ENV_VAR);
        if (env == null || env.isBlank()) {
            return Paths.get(DEFAULT_DIR);
        }
        return Paths.get(env);
    }

    /** Ensure production base directory exists and return it. */
    public static Path ensureBaseDirectoryExists() {
        return ensureBaseDirectoryExists(baseDirectory());
    }

    /** Testable: ensure any base directory exists and return it. */
    public static Path ensureBaseDirectoryExists(Path baseDirectory) {
        try {
            Files.createDirectories(baseDirectory);
            return baseDirectory;
        } catch (java.io.IOException e) {
            throw new UncheckedIOException("Failed to create data directory: " + baseDirectory, e);
        }
    }

    /** Production default address book file location. */
    public static Path addressBookFile() {
        return addressBookFile(baseDirectory());
    }

    /** Testable: compute address book file for any base directory. */
    public static Path addressBookFile(Path baseDirectory) {
        return baseDirectory.resolve(ADDRESS_BOOK_FILENAME);
    }
    
    /** Backward-compatible helper used by FileLoader/FileSaver. */
    public static Path getFile(String filename) {
	Path base = ensureBaseDirectoryExists();
        return getFile(base, filename);        
    }

    /** Testable overload: resolve filename under a provided base directory. */
    public static Path getFile(Path baseDirectory, String filename) {
        return baseDirectory.resolve(filename);
    }
}

