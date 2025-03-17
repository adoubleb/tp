package seedu.address.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.ReadOnlyCommandHistory;


/**
 * A class to access CommandHistory data stored as a json file on the hard disk.
 */
public class JsonCommandHistoryStorage implements CommandHistoryStorage {
    private static final Logger logger = LogsCenter.getLogger(JsonAddressBookStorage.class);

    private Path filePath;

    public JsonCommandHistoryStorage(Path filePath) {
        this.filePath = filePath;
    }

    @Override
    public Path getCommandHistoryFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyCommandHistory> readCommandHistory() throws DataLoadingException {
        return readCommandHistory(filePath);
    }

    /**
     * Similar to {@link #readCommandHistory()}
     * @param commandHistoryFilePath location of the data. Cannot be null.
     * @throws DataLoadingException if the file format is not as expected.
     */
    @Override
    public Optional<ReadOnlyCommandHistory> readCommandHistory(Path commandHistoryFilePath)
            throws DataLoadingException {
        requireNonNull(commandHistoryFilePath);

        Optional<JsonSerializableCommandHistory> jsonCommandHistory = JsonUtil.readJsonFile(
                commandHistoryFilePath, JsonSerializableCommandHistory.class);
        if (jsonCommandHistory.isEmpty()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonCommandHistory.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + commandHistoryFilePath + ": " + ive.getMessage());
            throw new DataLoadingException(ive);
        }
    }

    @Override
    public void saveCommandHistory(ReadOnlyCommandHistory commandHistory) throws IOException {
        saveCommandHistory(commandHistory, filePath);
    }

    /**
     * Similar to {@link #saveCommandHistory(ReadOnlyCommandHistory)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    @Override
    public void saveCommandHistory(ReadOnlyCommandHistory commandHistory, Path filePath) throws IOException {
        requireNonNull(commandHistory);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableCommandHistory(commandHistory), filePath);
    }
}
