package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.ReadOnlyCommandHistory;

/**
 * Represents a storage for {@link seedu.address.model.CommandHistory}.
 */
public interface CommandHistoryStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getCommandHistoryFilePath();

    /**
     * Returns CommandHistory data as a ReadOnlyCommandHistory.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataLoadingException if loading the data from storage failed.
     */
    Optional<ReadOnlyCommandHistory> readCommandHistory() throws DataLoadingException;

    /**
     * @see #getCommandHistoryFilePath()
     */
    Optional<ReadOnlyCommandHistory> readCommandHistory(Path filePath) throws DataLoadingException;

    /**
     * Saves the given CommandHistory to the storage.
     * @param commandHistory cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveCommandHistory(ReadOnlyCommandHistory commandHistory) throws IOException;

    /**
     * @see #saveCommandHistory(ReadOnlyCommandHistory)
     */
    void saveCommandHistory(ReadOnlyCommandHistory commandHistory, Path filePath) throws IOException;
}
