package io;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;

/**
 * Utility class for saving strings or lines to a file.
 * Works with paths from AppPaths.
 */
public class FileSaver {

    /**
     * Saves a list of lines to a given file.
     */
    public static void saveLines(Path path, List<String> lines) throws IOException {
        ensureParentDirectory(path);
        Files.write(path, lines);
    }

    /**
     * Saves a full string to a given file.
     */
    public static void saveString(Path path, String content) throws IOException {
        ensureParentDirectory(path);
        Files.writeString(path, content);
    }

    /**
     * Saves lines to a file inside the base directory.
     *
     * @param fileName file name only (e.g. "customers.csv")
     */
    public static void saveLinesToBase(String fileName, List<String> lines) throws IOException {
        saveLines(AppPaths.getFile(fileName), lines);
    }

    /**
     * Saves a string to a file inside the base directory.
     */
    public static void saveStringToBase(String fileName, String content) throws IOException {
        saveString(AppPaths.getFile(fileName), content);
    }

    /**
     * Ensures the file’s parent directory exists.
     */
    private static void ensureParentDirectory(Path path) throws IOException {
        Path parent = path.getParent();
        if (parent != null && !Files.exists(parent)) {
            Files.createDirectories(parent);
        }
    }
}
