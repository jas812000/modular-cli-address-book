package addressbook.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * Provides file-writing operations for application data.
 */
public final class FileSaver {

    /**
     * Prevents instantiation because this class provides only static utility methods.
     */
    private FileSaver() {
    }

    /**
     * Writes lines to the specified file.
     *
     * @param path destination file
     * @param lines lines to write
     * @throws IOException if the file cannot be written
     */
    public static void saveLines(Path path, List<String> lines) throws IOException {
        ensureParentDirectory(path);
        Files.write(path, lines);
    }

    /**
     * Writes a string to the specified file.
     *
     * @param path destination file
     * @param content content to write
     * @throws IOException if the file cannot be written
     */
    public static void saveString(Path path, String content) throws IOException {
        ensureParentDirectory(path);
        Files.writeString(path, content);
    }

    /**
     * Writes lines to a named file in the application's base data directory.
     *
     * @param fileName destination file name
     * @param lines lines to write
     * @throws IOException if the file cannot be written
     */
    public static void saveLinesToBase(String fileName, List<String> lines) throws IOException {
        saveLines(AppPaths.getFile(fileName), lines);
    }

    /**
     * Writes a string to a named file in the application's base data directory.
     *
     * @param fileName destination file name
     * @param content content to write
     * @throws IOException if the file cannot be written
     */
    public static void saveStringToBase(String fileName, String content) throws IOException {
        saveString(AppPaths.getFile(fileName), content);
    }

    /**
     * Creates the parent directory for a file when necessary.
     *
     * @param path file whose parent directory should exist
     * @throws IOException if the directory cannot be created
     */
    private static void ensureParentDirectory(Path path) throws IOException {
        Path parent = path.getParent();

        if (parent != null) {
            Files.createDirectories(parent);
        }
    }
}
