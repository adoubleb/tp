package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_DUPLICATE_INDEX;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.parser.exceptions.DuplicateIndexParseException;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class DeleteCommandParser implements Parser<DeleteCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteCommand parse(String args) throws ParseException {
        try {
            List<Index> targetIndices = new ArrayList<>();
            HashSet<Index> seenIndices = new HashSet<>();
            String[] indicesStr = args.trim().split("\\s+");

            for (String indexStr : indicesStr) {
                Index index = ParserUtil.parseIndex(indexStr);
                if (!seenIndices.add(index)) {
                    throw new DuplicateIndexParseException(
                            String.format(MESSAGE_DUPLICATE_INDEX, "Entered: delete " + args)
                    );
                }
                targetIndices.add(index);
            }
            return new DeleteCommand(targetIndices);

        } catch (DuplicateIndexParseException de) {
            throw de;

        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE), pe
            );
        }
    }

}
