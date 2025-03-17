package seedu.address.logic.commands;

import seedu.address.logic.CommandTracker;
import seedu.address.model.Model;

/**
 * This class handles the "redoing" of commands entered by the CLI user.
 */
public class RedoCommand extends Command {
    public static final String COMMAND_WORD = "redo";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Redoes the most recent command entered by the user.\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_REDO_SUCCESS = "Redo successful!";

    public static final String MESSAGE_FAILURE = "Nothing to redo!";

    @Override
    public CommandResult execute(Model model) {
        CommandTracker commandTracker = CommandTracker.getInstance();
        if (!commandTracker.canRedo()) {
            return new CommandResult(MESSAGE_FAILURE);
        }
        Command lastCommand = commandTracker.popRedo();
        lastCommand.redo(model);
        return new CommandResult(MESSAGE_REDO_SUCCESS);
    }
}
