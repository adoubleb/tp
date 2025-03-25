package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SORT;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class ListCommandParserTest {
    private ListCommandParser parser;

    @BeforeEach
    public void setUp() {
        parser = new ListCommandParser();
    }

    @Test
    public void parse_noArgs_returnsListCommandWithNone() throws Exception {
        ListCommand command = parser.parse("  ");
        assertEquals(new ListCommand(ListCommand.SortOrder.NONE), command);
    }

    @Test
    public void parse_invalidInput_throwsParseException() {
        assertThrows(ParseException.class, () -> parser.parse("random"));
    }

    @Test
    public void parse_validAsc_returnsListCommandAsc() throws Exception {
        String userInput = PREFIX_SORT + "asc";
        ListCommand command = parser.parse(userInput);
        assertEquals(new ListCommand(ListCommand.SortOrder.ASCENDING), command);
    }

    @Test
    public void parse_validDesc_returnsListCommandDesc() throws Exception {
        ListCommand command = parser.parse(PREFIX_SORT + "desc");
        assertEquals(new ListCommand(ListCommand.SortOrder.DESCENDING), command);
    }

    @Test
    public void parse_invalidSortValue_throwsParseException() {
        assertThrows(ParseException.class, () -> parser.parse(PREFIX_SORT.toString() + "random"));
    }

    @Test
    public void parse_extraInputBeforePrefix_throwsParseException() {
        assertThrows(ParseException.class, () -> parser.parse("extra s/asc"));
    }

    @Test
    public void parse_emptySortValue_throwsParseException() {
        assertThrows(ParseException.class, () -> parser.parse(PREFIX_SORT.toString()));
    }

}
