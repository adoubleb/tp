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

/**
 * A generic class to access data stored as a json file on the hard disk.
 * @param <T> The model type being stored
 * @param <S> The JsonSerializable wrapper for the model type
 */
public abstract class JsonStorage<T, S extends JsonSerializable<T>> {
    private static final Logger logger = LogsCenter.getLogger(JsonStorage.class);

    protected Path filePath;
    private final Class<S> serializableClass;

    /**
     * Constructs a JsonStorage with the specified file path and serializable class.
     *
     * @param filePath The path where the serialized data will be stored
     * @param serializableClass The class of the serializable wrapper for the model type
     */
    public JsonStorage(Path filePath, Class<S> serializableClass) {
        this.filePath = filePath;
        this.serializableClass = serializableClass;
    }

    public Path getFilePath() {
        return filePath;
    }

    public Optional<T> read() throws DataLoadingException {
        return read(filePath);
    }

    /**
     * Reads data from the specified file path.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataLoadingException if the file format is not as expected.
     */
    public Optional<T> read(Path filePath) throws DataLoadingException {
        requireNonNull(filePath);

        Optional<S> jsonData = JsonUtil.readJsonFile(filePath, serializableClass);
        if (jsonData.isEmpty()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonData.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataLoadingException(ive);
        }
    }

    public void save(T data) throws IOException {
        save(data, filePath);
    }

    /**
     * Saves the given data to the specified file path.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void save(T data, Path filePath) throws IOException {
        requireNonNull(data);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(createSerializable(data), filePath);
    }

    /**
     * Creates a serializable wrapper for the data.
     */
    protected abstract S createSerializable(T data);
}
