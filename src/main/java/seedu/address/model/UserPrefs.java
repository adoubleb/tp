package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import seedu.address.commons.core.GuiSettings;

/**
 * Represents User's preferences.
 */
public class UserPrefs implements ReadOnlyUserPrefs {
    private static final String DEFAULT_DATA_PATH = "data";
    private static final String DEFAULT_ADDRESS_BOOK_PATH = "addressbook.json";
    private static final String DEFAULT_COMMAND_HISTORY_PATH = "commandhistory.json";

    private GuiSettings guiSettings = new GuiSettings();
    private Path addressBookFilePath = Paths.get(DEFAULT_DATA_PATH, DEFAULT_ADDRESS_BOOK_PATH);
    private Path commandHistoryFilePath = Paths.get(DEFAULT_DATA_PATH, DEFAULT_COMMAND_HISTORY_PATH);

    /**
     * Creates a {@code UserPrefs} with default values.
     */
    public UserPrefs() {}

    /**
     * Creates a {@code UserPrefs} with the prefs in {@code userPrefs}.
     */
    public UserPrefs(ReadOnlyUserPrefs userPrefs) {
        this();
        resetData(userPrefs);
    }

    /**
     * Resets the existing data of this {@code UserPrefs} with {@code newUserPrefs}.
     */
    public void resetData(ReadOnlyUserPrefs newUserPrefs) {
        requireNonNull(newUserPrefs);
        setGuiSettings(newUserPrefs.getGuiSettings());
        setAddressBookFilePath(newUserPrefs.getAddressBookFilePath());
        setCommandHistoryFilePath(newUserPrefs.getCommandHistoryFilePath());
    }

    @Override
    public GuiSettings getGuiSettings() {
        return guiSettings;
    }

    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        this.guiSettings = guiSettings;
    }

    @Override
    public Path getAddressBookFilePath() {
        return addressBookFilePath;
    }

    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        this.addressBookFilePath = addressBookFilePath;
    }

    @Override
    public Path getCommandHistoryFilePath() {
        return commandHistoryFilePath;
    }

    public void setCommandHistoryFilePath(Path commandHistoryFilePath) {
        requireNonNull(commandHistoryFilePath);
        this.commandHistoryFilePath = commandHistoryFilePath;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UserPrefs)) {
            return false;
        }

        UserPrefs otherUserPrefs = (UserPrefs) other;
        return guiSettings.equals(otherUserPrefs.guiSettings)
                && addressBookFilePath.equals(otherUserPrefs.addressBookFilePath)
                && commandHistoryFilePath.equals(otherUserPrefs.commandHistoryFilePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(guiSettings, addressBookFilePath, commandHistoryFilePath);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Gui Settings : " + guiSettings);
        sb.append("\nLocal data file location : " + addressBookFilePath);
        sb.append("\nCommand History file location : " + commandHistoryFilePath);
        return sb.toString();
    }

}
