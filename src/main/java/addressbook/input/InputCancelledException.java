package addressbook.input;

/**
 * Signals that the user has cancelled the current interactive operation.
 */
public class InputCancelledException extends RuntimeException {

    /**
     * Creates an exception representing a user-requested operation cancellation.
     */
    public InputCancelledException() {
        super("Operation cancelled.");
    }
}
