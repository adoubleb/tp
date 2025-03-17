package seedu.address.logic.commands;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.CommandTracker;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * This class handles the "undoing" of commands entered by the CLI user.
 */
public class UndoCommand extends Command {
    public static final String COMMAND_WORD = "undo";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Undoes the most recent command entered by the user.\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_UNDO_SUCCESS = "Undo successful!";

    public static final String MESSAGE_FAILURE = "Nothing to undo!";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        CommandTracker commandTracker = CommandTracker.getInstance();
        if (!commandTracker.canUndo()) {
            throw new CommandException(MESSAGE_FAILURE);
        }
        Command lastCommand = commandTracker.popUndo();
        lastCommand.undo(model);
        return new CommandResult(MESSAGE_UNDO_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof UndoCommand;
    }

    @Override
    public String toString() {
        return UndoCommand.class.getCanonicalName();
    }
}
