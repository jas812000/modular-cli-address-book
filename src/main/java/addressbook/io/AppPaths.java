package addressbook.io;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Centralized path resolution utility for application data.
 *
 * Allows the base data directory to be configured at runtime
 * using an environment variable, with a safe default fallback.
 */
public class AppPaths {

    // Environment variable name
    private static final String ENV_VAR = "APP_DATA_DIR";

    // Base directory, resolved at runtime
    public static final Path BASE_DIRECTORY;


    // Resolve base directory once at class load time
    static {
        String envPath = System.getenv(ENV_VAR);
        if (envPath != null && !envPath.isBlank()) {
            BASE_DIRECTORY = Paths.get(envPath);
        } else {
            BASE_DIRECTORY = Paths.get("data"); // Default fallback
        }
    }

    // File paths
    //public static final Path CUSTOMER_FILE = BASE_DIRECTORY.resolve("customers.csv");
    //public static final Path TRANSACTION_FILE = BASE_DIRECTORY.resolve("transactions.txt");
    //public static final Path INVENTORY_FILE = BASE_DIRECTORY.resolve("inventory.txt");
    //public static final Path CONFIG_FILE = BASE_DIRECTORY.resolve("config.properties");
    //public static final Path LOG_FILE = BASE_DIRECTORY.resolve("app.log");
    public static final Path ADDRESS_BOOK_FILE = BASE_DIRECTORY.resolve("address_book.csv");

    /**
     * Dynamically resolve any file inside the base directory.
     */
    public static Path getFile(String fileName) {
        return BASE_DIRECTORY.resolve(fileName);
    }

    /**
     * Dynamically resolve a subdirectory path inside the base directory.
     */
    public static Path getSubDirectory(String folderName) {
        return BASE_DIRECTORY.resolve(folderName);
    }
}

