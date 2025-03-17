package seedu.address.model;

import java.util.List;

/**
 * Unmodifiable view of command history
 */
public interface ReadOnlyCommandHistory {
    /**
     * Returns an unmodifiable view of the command history.
     */
    List<String> getCommandHistory();
}
