package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.ReadOnlyCommandHistory;

/**
 * A class to access CommandHistory data stored as a json file on the hard disk.
 */
public class JsonCommandHistoryStorage extends JsonStorage<ReadOnlyCommandHistory, JsonSerializableCommandHistory>
        implements CommandHistoryStorage {

    public JsonCommandHistoryStorage(Path filePath) {
        super(filePath, JsonSerializableCommandHistory.class);
    }

    @Override
    public Path getCommandHistoryFilePath() {
        return getFilePath();
    }

    @Override
    public Optional<ReadOnlyCommandHistory> readCommandHistory() throws DataLoadingException {
        return read();
    }

    @Override
    public Optional<ReadOnlyCommandHistory> readCommandHistory(Path filePath) throws DataLoadingException {
        return read(filePath);
    }

    @Override
    public void saveCommandHistory(ReadOnlyCommandHistory commandHistory) throws IOException {
        save(commandHistory);
    }

    @Override
    public void saveCommandHistory(ReadOnlyCommandHistory commandHistory, Path filePath) throws IOException {
        save(commandHistory, filePath);
    }

    @Override
    public JsonSerializableCommandHistory createSerializable(ReadOnlyCommandHistory commandHistory) {
        return new JsonSerializableCommandHistory(commandHistory);
    }
}
