package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.CommandHistory;

public class JsonSerializableCommandHistoryTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableCommandHistoryTest");
    private static final Path TYPICAL_COMMANDS_FILE = TEST_DATA_FOLDER.resolve("typicalCommandHistory.json");
    private static final Path INVALID_COMMAND_FILE = TEST_DATA_FOLDER.resolve("invalidCommandHistory.json");

    private static final List<String> TYPICAL_COMMANDS = Arrays.asList(
            "find Alice",
            "add n/John Doe p/98765432 e/johnd@example.com a/311, Clementi Ave 2, #02-25",
            "list",
            "help",
            "exit");

    @Test
    public void toModelType_typicalCommandsFile_success() throws Exception {
        JsonSerializableCommandHistory dataFromFile = JsonUtil.readJsonFile(TYPICAL_COMMANDS_FILE,
                JsonSerializableCommandHistory.class).get();
        CommandHistory commandHistoryFromFile = dataFromFile.toModelType();
        CommandHistory typicalCommandHistory = getTypicalCommandHistory();
        assertEquals(commandHistoryFromFile, typicalCommandHistory);
    }

    @Test
    public void toModelType_invalidCommandFile_throwsIllegalValueException() throws Exception {
        JsonSerializableCommandHistory dataFromFile = JsonUtil.readJsonFile(INVALID_COMMAND_FILE,
                JsonSerializableCommandHistory.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void constructor_nullCommandList_createsEmptyList() throws Exception {
        JsonSerializableCommandHistory history = new JsonSerializableCommandHistory(new CommandHistory());
        assertEquals(0, history.toModelType().getCommandHistory().size());
    }

    @Test
    public void roundTrip_typicalCommandHistory_success() throws Exception {
        CommandHistory original = getTypicalCommandHistory();
        // Convert to JSON format
        JsonSerializableCommandHistory serialized = new JsonSerializableCommandHistory(original);
        // Convert back to model type
        CommandHistory converted = serialized.toModelType();
        // Check equality
        assertEquals(original, converted);
    }

    /**
     * Returns a {@code CommandHistory} with typical commands.
     */
    private CommandHistory getTypicalCommandHistory() {
        CommandHistory commandHistory = new CommandHistory();
        for (String command : TYPICAL_COMMANDS) {
            commandHistory.addCommand(command);
        }
        return commandHistory;
    }
}
