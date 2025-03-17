package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_DUPLICATE_INDEX;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.DeleteCommand;



/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the DeleteCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the DeleteCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class DeleteCommandParserTest {

    private DeleteCommandParser parser = new DeleteCommandParser();

    @Test
    public void parse_emptyArgs_throwsParseException() {
        String userInput = "";
        assertParseFailure(parser, userInput, String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsDeleteCommand() {
        assertParseSuccess(parser, "1", new DeleteCommand(List.of(INDEX_FIRST_PERSON)));
    }

    @Test
    public void parse_withMultipleValidArgs_returnsDeleteCommand() {
        assertParseSuccess(parser, "1 2", new DeleteCommand(List.of(INDEX_FIRST_PERSON, INDEX_SECOND_PERSON)));
    }
    @Test
    public void parse_withMultipleWhitespaceValidArgs_returnsDeleteCommand() {
        assertParseSuccess(parser, "1                   2",
                new DeleteCommand(List.of(INDEX_FIRST_PERSON, INDEX_SECOND_PERSON)));
    }

    @Test
    public void parse_leadingAndTrailingWhitespace_returnsDeleteCommand() {
        assertParseSuccess(parser, "   1 2 3   ",
                new DeleteCommand(List.of(INDEX_FIRST_PERSON, INDEX_SECOND_PERSON, INDEX_THIRD_PERSON)));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_negativeIndex_throwsParseException() {
        String userInput = "-1 2";
        assertParseFailure(parser, userInput, String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_duplicateIndices_throwsDuplicateIndexParseException() {
        String userInput = "1 1";
        String expectedMessage = String.format(MESSAGE_DUPLICATE_INDEX, "Entered: delete " + userInput);

        assertParseFailure(parser, userInput, expectedMessage);
    }

    @Test
    public void parse_duplicateNonConsecutiveIndices_throwsDuplicateIndexParseException() {
        String userInput = "1 2 1";
        String expectedMessage = String.format(MESSAGE_DUPLICATE_INDEX, "Entered: delete " + userInput);

        assertParseFailure(parser, userInput, expectedMessage);
    }

}
