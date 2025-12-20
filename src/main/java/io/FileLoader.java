package io;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;

/**
 * Generic file loader utility class.
 * Uses AppPaths for directory resolution if needed.
 */
public class FileLoader {

    /**
     * Loads all lines from a file.
     *
     * @param path the path to the file
     * @return list of lines
     * @throws IOException if reading fails
     */
    public static List<String> loadLines(Path path) throws IOException {
        return Files.readAllLines(path);
    }

    /**
     * Loads the entire file content as a single string.
     *
     * @param path the path to the file
     * @return full content as one string
     * @throws IOException if reading fails
     */
    public static String loadAsString(Path path) throws IOException {
        return Files.readString(path);
    }

    /**
     * Loads file lines from a named file inside the base directory.
     *
     * @param fileName name of file (e.g. "customers.csv")
     * @return list of lines
     * @throws IOException if reading fails
     */
    public static List<String> loadLinesFromBase(String fileName) throws IOException {
        return loadLines(AppPaths.getFile(fileName));
    }

    /**
     * Checks whether a file exists.
     */
    public static boolean fileExists(Path path) {
        return Files.exists(path);
    }
}

