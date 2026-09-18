package addressbook.io;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Centralizes resolution of application data paths.
 *
 * <p>The {@code APP_DATA_DIR} environment variable may override the
 * default {@code data} directory. Overloads accepting explicit paths
 * support isolated filesystem testing.</p>
 */
public final class AppPaths {

    private static final String ENV_VAR = "APP_DATA_DIR";
    private static final String DEFAULT_DIR = "data";
    private static final String ADDRESS_BOOK_FILENAME = "address_book.csv";

    /**
     * Prevents instantiation because this class provides only static utility methods.
     */
    private AppPaths() {
    }

    /**
     * Returns the configured application data directory.
     *
     * @return configured or default data directory
     */
    public static Path baseDirectory() {
        String env = System.getenv(ENV_VAR);

        if (env == null || env.isBlank()) {
            return Paths.get(DEFAULT_DIR);
        }

        return Paths.get(env);
    }

    /**
     * Ensures that the configured application data directory exists.
     *
     * @return application data directory
     * @throws UncheckedIOException if the directory cannot be created
     */
    public static Path ensureBaseDirectoryExists() {
        return ensureBaseDirectoryExists(baseDirectory());
    }

    /**
     * Ensures that the specified data directory exists.
     *
     * @param baseDirectory directory to create if necessary
     * @return supplied directory
     * @throws UncheckedIOException if the directory cannot be created
     */
    public static Path ensureBaseDirectoryExists(Path baseDirectory) {
        try {
            Files.createDirectories(baseDirectory);
            return baseDirectory;
        } catch (IOException e) {
            throw new UncheckedIOException(
                    "Failed to create data directory: " + baseDirectory,
                    e
            );
        }
    }

    /**
     * Returns the default address book file path.
     *
     * @return address book file path
     */
    public static Path addressBookFile() {
        return addressBookFile(baseDirectory());
    }

    /**
     * Resolves the address book file beneath the specified base directory.
     *
     * @param baseDirectory base data directory
     * @return address book file path
     */
    public static Path addressBookFile(Path baseDirectory) {
        return baseDirectory.resolve(ADDRESS_BOOK_FILENAME);
    }

    /**
     * Resolves a named file beneath the application's base data directory.
     *
     * @param fileName file name
     * @return resolved file path
     */
    public static Path getFile(String fileName) {
        Path baseDirectory = ensureBaseDirectoryExists();
        return getFile(baseDirectory, fileName);
    }

    /**
     * Resolves a named file beneath the specified base directory.
     *
     * @param baseDirectory base data directory
     * @param fileName file name
     * @return resolved file path
     */
    public static Path getFile(Path baseDirectory, String fileName) {
        return baseDirectory.resolve(fileName);
    }
}
