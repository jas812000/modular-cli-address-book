package addressbook.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * Provides file-reading operations for application data.
 */
public final class FileLoader {

    /**
     * Prevents instantiation because this class provides only static utility methods.
     */
    private FileLoader() {
    }

    /**
     * Reads all lines from a file.
     *
     * @param path file to read
     * @return lines read from the file
     * @throws IOException if the file cannot be read
     */
    public static List<String> loadLines(Path path) throws IOException {
        return Files.readAllLines(path);
    }

    /**
     * Reads the entire contents of a file as a string.
     *
     * @param path file to read
     * @return file contents
     * @throws IOException if the file cannot be read
     */
    public static String loadAsString(Path path) throws IOException {
        return Files.readString(path);
    }

    /**
     * Reads all lines from a named file in the application's base data directory.
     *
     * @param fileName file name
     * @return lines read from the file
     * @throws IOException if the file cannot be read
     */
    public static List<String> loadLinesFromBase(String fileName) throws IOException {
        return loadLines(AppPaths.getFile(fileName));
    }

    /**
     * Determines whether the specified file exists.
     *
     * @param path file to check
     * @return {@code true} if the file exists
     */
    public static boolean fileExists(Path path) {
        return Files.exists(path);
    }
}
