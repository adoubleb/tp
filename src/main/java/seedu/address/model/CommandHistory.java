package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the command history in the address book.
 * Guarantees: details are present and not null.
 */
public class CommandHistory implements ReadOnlyCommandHistory {
    private static final int MAX_HISTORY_SIZE = 100;
    private final List<String> commandList;
    private int currentIndex = 0;

    public CommandHistory() {
        commandList = new ArrayList<>();
    }

    /**
     * Creates a CommandHistory using the List in the {@code toBeCopied}
     */
    public CommandHistory(ReadOnlyCommandHistory toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    /**
     * Replaces the contents of the command list with {@code commands}.
     */
    public void setCommands(List<String> commands) {
        this.commandList.clear();
        this.commandList.addAll(commands);
        trimToSize();
    }

    /**
     * Resets the existing data of this {@code CommandHistory} with {@code newData}.
     */
    public void resetData(ReadOnlyCommandHistory newData) {
        requireNonNull(newData);
        setCommands(newData.getCommandHistory());
    }

    /**
     * Adds a command to the command history.
     */
    public void addCommand(String command) {
        commandList.add(command);
        trimToSize();
    }

    /**
     * Ensures the history doesn't exceed the maximum size.
     */
    private void trimToSize() {
        if (commandList.size() > MAX_HISTORY_SIZE) {
            commandList.subList(0, commandList.size() - MAX_HISTORY_SIZE).clear();
        }
    }

    @Override
    public List<String> getCommandHistory() {
        return new ArrayList<>(commandList);
    }

    @Override
    public String getPreviousCommand() {
        if (canNavigateBackward()) {
            this.currentIndex++;
            return commandList.get(commandList.size() - currentIndex);
        }

        return null;
    }

    @Override
    public String getNextCommand() {
        if (canNavigateForward()) {
            this.currentIndex--;
            // Special case: If we've returned to the beginning,
            // return empty string to clear the command box
            if (currentIndex == 0) {
                return "";
            }
            return commandList.get(commandList.size() - currentIndex);
        }

        return null;
    }

    @Override
    public void resetNavigation() {
        this.currentIndex = 0;
    }

    @Override
    public boolean canNavigateBackward() {
        return this.currentIndex < commandList.size();
    }

    @Override
    public boolean canNavigateForward() {
        return this.currentIndex > 0;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof CommandHistory)) {
            return false;
        }

        CommandHistory otherCommandHistory = (CommandHistory) other;
        return commandList.equals(otherCommandHistory.commandList);
    }

    @Override
    public int hashCode() {
        return commandList.hashCode();
    }
}
