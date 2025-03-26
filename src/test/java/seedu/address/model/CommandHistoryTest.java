package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CommandHistoryTest {
    private static final String VALID_COMMAND_1 = "list";
    private static final String VALID_COMMAND_2 = "add n/James Ho p/22224444 e/jamesho@example.com a/123,"
            + " Clementi Rd, 1234665 t/friend t/colleague";
    private static final String VALID_COMMAND_3 = "edit 1 n/James Lee e/jameslee@example.com";
    private CommandHistory commandHistory;

    @BeforeEach
    public void setUp() {
        commandHistory = new CommandHistory();
    }

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), commandHistory.getCommandHistory());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> commandHistory.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyCommandHistory_replacesData() {
        CommandHistory newData = new CommandHistory();
        newData.addCommand(VALID_COMMAND_1);

        commandHistory.resetData(newData);
        assertEquals(newData.getCommandHistory(), commandHistory.getCommandHistory());
    }

    @Test
    public void addCommand_validCommand_success() {
        commandHistory.addCommand(VALID_COMMAND_1);
        List<String> expectedHistory = Collections.singletonList(VALID_COMMAND_1);
        assertEquals(expectedHistory, commandHistory.getCommandHistory());

        commandHistory.addCommand(VALID_COMMAND_2);
        expectedHistory = Arrays.asList(VALID_COMMAND_1, VALID_COMMAND_2);
        assertEquals(expectedHistory, commandHistory.getCommandHistory());
    }

    @Test
    public void trimToSize_exceedMaxSizeByOne_trimmed() {
        List<String> lotsOfCommands = new ArrayList<>();
        for (int i = 0; i < 101; i++) {
            lotsOfCommands.add("list" + i);
        }

        CommandHistory newHistory = new CommandHistory();
        lotsOfCommands.forEach(newHistory::addCommand);

        List<String> expectedCommands = lotsOfCommands.subList(1, 101);
        assertEquals(expectedCommands, newHistory.getCommandHistory());
        assertEquals(100, newHistory.getCommandHistory().size());
    }

    @Test
    public void trimToSize_exceedMaxSize_trimmed() {
        List<String> lotsOfCommands = new ArrayList<>();
        for (int i = 0; i < 200; i++) {
            lotsOfCommands.add("list" + i);
        }

        CommandHistory newHistory = new CommandHistory();
        lotsOfCommands.forEach(newHistory::addCommand);

        List<String> expectedCommands = lotsOfCommands.subList(100, 200);
        assertEquals(expectedCommands, newHistory.getCommandHistory());
        assertEquals(100, newHistory.getCommandHistory().size());
    }

    @Test
    public void setCommands_validList_success() {
        List<String> commands = Arrays.asList(VALID_COMMAND_1, VALID_COMMAND_2, VALID_COMMAND_3);
        commandHistory.setCommands(commands);
        assertEquals(commands, commandHistory.getCommandHistory());
    }

    @Test
    public void getCommandHistory_modifyReturnedList_originalUnmodified() {
        commandHistory.addCommand(VALID_COMMAND_1);
        commandHistory.addCommand(VALID_COMMAND_2);

        List<String> returnedHistory = commandHistory.getCommandHistory();
        returnedHistory.add(VALID_COMMAND_3);

        List<String> expectedHistory = Arrays.asList(VALID_COMMAND_1, VALID_COMMAND_2);
        assertEquals(expectedHistory, commandHistory.getCommandHistory());
    }

    @Test
    public void navigationMethods_emptyHistory_returnsExpectedResults() {
        assertFalse(commandHistory.canNavigateBackward());
        assertFalse(commandHistory.canNavigateForward());

        assertNull(commandHistory.getPreviousCommand());
        assertNull(commandHistory.getNextCommand());
    }

    @Test
    public void navigationMethods_nonEmptyHistory_returnsExpectedResults() {
        commandHistory.addCommand(VALID_COMMAND_1);
        commandHistory.addCommand(VALID_COMMAND_2);
        commandHistory.addCommand(VALID_COMMAND_3);

        // Initially, we can only navigate backward
        assertTrue(commandHistory.canNavigateBackward());
        assertFalse(commandHistory.canNavigateForward());

        // Navigate backward once
        assertEquals(VALID_COMMAND_3, commandHistory.getPreviousCommand());
        assertTrue(commandHistory.canNavigateBackward());
        assertTrue(commandHistory.canNavigateForward());

        // Navigate backward again
        assertEquals(VALID_COMMAND_2, commandHistory.getPreviousCommand());
        assertTrue(commandHistory.canNavigateBackward());
        assertTrue(commandHistory.canNavigateForward());

        // Navigate backward to the first command
        assertEquals(VALID_COMMAND_1, commandHistory.getPreviousCommand());
        assertFalse(commandHistory.canNavigateBackward());
        assertTrue(commandHistory.canNavigateForward());

        // Now navigate forward
        assertEquals(VALID_COMMAND_2, commandHistory.getNextCommand());
        assertTrue(commandHistory.canNavigateBackward());
        assertTrue(commandHistory.canNavigateForward());

        // Navigate forward to the last command
        assertEquals(VALID_COMMAND_3, commandHistory.getNextCommand());
        assertTrue(commandHistory.canNavigateBackward());
        assertTrue(commandHistory.canNavigateForward());

        // Navigate forward past the last command to return to initial state
        assertEquals("", commandHistory.getNextCommand());
        assertTrue(commandHistory.canNavigateBackward());
        assertFalse(commandHistory.canNavigateForward());
    }

    @Test
    public void resetNavigation_afterNavigation_resetsToInitialState() {
        commandHistory.addCommand(VALID_COMMAND_1);
        commandHistory.addCommand(VALID_COMMAND_2);
        commandHistory.getPreviousCommand();

        // Reset navigation
        commandHistory.resetNavigation();
        assertTrue(commandHistory.canNavigateBackward());
        assertFalse(commandHistory.canNavigateForward());
    }

    @Test
    public void equals_sameCommandHistory_returnsTrue() {
        CommandHistory history1 = new CommandHistory();
        history1.addCommand(VALID_COMMAND_1);

        CommandHistory history2 = new CommandHistory();
        history2.addCommand(VALID_COMMAND_1);

        assertTrue(history1.equals(history1)); // Same object
        assertTrue(history1.equals(history2)); // Different object, same contents
    }

    @Test
    public void equals_differentCommandHistory_returnsFalse() {
        CommandHistory history1 = new CommandHistory();
        history1.addCommand(VALID_COMMAND_1);

        CommandHistory history2 = new CommandHistory();
        history2.addCommand(VALID_COMMAND_2);

        assertFalse(history1.equals(history2)); // Different contents
        assertFalse(history1.equals(null)); // Null
        assertFalse(history1.equals("string")); // Different type
    }
}
