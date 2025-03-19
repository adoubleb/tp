package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Represents a command that requires a confirmation step before execution.
 * Classes implementing this interface should define the specific logic for confirmation and execution.
 */
public interface ConfirmableCommand {
    public String getConfirmationString();
    public CommandResult executeConfirmed(Model model) throws CommandException;

    public CommandResult executeAborted();
}
