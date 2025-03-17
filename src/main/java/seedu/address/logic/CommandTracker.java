package seedu.address.logic;

import java.util.Stack;

import seedu.address.logic.commands.Command;

/**
 * This class tracks the commands that can be redone and undone.
 */
public class CommandTracker {
    private static final CommandTracker instance = new CommandTracker();
    private final Stack<Command> undoStack = new Stack<>();
    private final Stack<Command> redoStack = new Stack<>();
    private boolean wasUndoCalled = false; // Track if last action was an undo

    private CommandTracker() {}

    /**
     * Returns the instance of command tracker as a singleton.
     *
     * @return The instance of command tracker.
     */
    public static CommandTracker getInstance() {
        return instance;
    }

    /**
     * Pushes a new command to the undo stack.
     * Clears the redo stack only if the last action was an undo, and the new command is not a redo.
     *
     * @param command The command being tracked.
     */
    public void push(Command command) {
        undoStack.push(command);

        if (!wasUndoCalled) {
            redoStack.clear();
        }

        wasUndoCalled = false;
    }

    /**
     * Checks if the undo stack is not empty.
     *
     * @return True if there is a command to undo, otherwise false.
     */
    public boolean canUndo() {
        return !undoStack.isEmpty();
    }

    /**
     * Checks if the redo stack is not empty.
     *
     * @return True if there is a command to redo, otherwise false.
     */
    public boolean canRedo() {
        return !redoStack.isEmpty();
    }

    /**
     * Retrieves and removes the most recent command from the undo stack.
     * Moves the command to the redo stack, allowing it to be re-executed if needed.
     *
     * @return The most recently executed command, or {@code null} if there is no command to undo.
     */
    public Command popUndo() {
        if (canUndo()) {
            Command cmd = undoStack.pop();
            redoStack.push(cmd);
            wasUndoCalled = true;
            return cmd;
        }
        return null;
    }

    /**
     * Retrieves and removes the most recent command from the redo stack.
     * Moves the command back to the undo stack, allowing it to be undone again if needed.
     *
     * @return The most recently undone command, or {@code null} if there is no command to redo.
     */
    public Command popRedo() {
        if (canRedo()) {
            Command cmd = redoStack.pop();
            undoStack.push(cmd);
            wasUndoCalled = false;
            return cmd;
        }
        return null;
    }
}
