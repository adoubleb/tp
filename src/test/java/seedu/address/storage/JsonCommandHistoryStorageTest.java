package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.CommandHistory;
import seedu.address.model.ReadOnlyCommandHistory;

public class JsonCommandHistoryStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data",
            "JsonCommandHistoryStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readCommandHistory_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readCommandHistory(null));
    }

    private java.util.Optional<ReadOnlyCommandHistory> readCommandHistory(String filePath) throws Exception {
        return new JsonCommandHistoryStorage(Paths.get(filePath))
                .readCommandHistory(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readCommandHistory("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataLoadingException.class, () -> readCommandHistory("notJsonFormatCommandHistory.json"));
    }

    @Test
    public void readCommandHistory_invalidCommandHistory_throwDataLoadingException() {
        assertThrows(DataLoadingException.class, () -> readCommandHistory("invalidCommandHistory.json"));
    }

    @Test
    public void readAndSaveCommandHistory_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempCommandHistory.json");
        CommandHistory original = new CommandHistory();
        original.addCommand("list");
        original.addCommand("help");
        JsonCommandHistoryStorage jsonCommandHistoryStorage = new JsonCommandHistoryStorage(filePath);

        // Save in new file and read back
        jsonCommandHistoryStorage.saveCommandHistory(original, filePath);
        ReadOnlyCommandHistory readBack = jsonCommandHistoryStorage.readCommandHistory(filePath).get();
        assertEquals(original, new CommandHistory(readBack));

        // Modify data, overwrite existing file, and read back
        original.addCommand("add n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd,"
                + " 1234665 t/friend t/colleague");
        jsonCommandHistoryStorage.saveCommandHistory(original, filePath);
        readBack = jsonCommandHistoryStorage.readCommandHistory(filePath).get();
        assertEquals(original, new CommandHistory(readBack));

        // Save and read without specifying file path
        original.addCommand("edit 1 n/James Lee e/jameslee@example.com");
        jsonCommandHistoryStorage.saveCommandHistory(original); // file path not specified
        readBack = jsonCommandHistoryStorage.readCommandHistory().get(); // file path not specified
        assertEquals(original, new CommandHistory(readBack));
    }

    @Test
    public void saveCommandHistory_nullCommandHistory_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveCommandHistory(null, "SomeFile.json"));
    }

    /**
     * Saves {@code commandHistory} at the specified {@code filePath}.
     */
    private void saveCommandHistory(ReadOnlyCommandHistory commandHistory, String filePath) {
        try {
            new JsonCommandHistoryStorage(Paths.get(filePath))
                    .saveCommandHistory(commandHistory, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveCommandHistory_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveCommandHistory(new CommandHistory(), null));
    }

    @Test
    public void getCommandHistoryFilePath_returnsCorrectFilePath() {
        Path expectedPath = Paths.get("test/path.json");
        JsonCommandHistoryStorage storage = new JsonCommandHistoryStorage(expectedPath);
        assertEquals(expectedPath, storage.getCommandHistoryFilePath());
    }
}
