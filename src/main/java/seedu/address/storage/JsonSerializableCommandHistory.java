package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.CommandHistory;
import seedu.address.model.ReadOnlyCommandHistory;

/**
 * JSON-friendly version of {@link ReadOnlyCommandHistory}.
 */
@JsonRootName(value = "commandhistory")
public class JsonSerializableCommandHistory {

    private final List<String> commandList;

    /**
     * Constructs a {@code JsonSerializableCommandHistory} with the given commands.
     */
    @JsonCreator
    public JsonSerializableCommandHistory(@JsonProperty("commandList") List<String> commandList) {
        this.commandList = commandList != null ? commandList : new ArrayList<>();
    }

    /**
     * Converts a given {@code ReadOnlyCommandHistory} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableCommandHistory}.
     */
    public JsonSerializableCommandHistory(ReadOnlyCommandHistory source) {
        commandList = new ArrayList<>(source.getCommandHistory());
    }

    /**
     * Converts this command history into the model's {@code CommandHistory} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public CommandHistory toModelType() throws IllegalValueException {
        CommandHistory commandHistory = new CommandHistory();
        commandHistory.setCommands(commandList);
        return commandHistory;
    }
}
