package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;

/**
 * Controller for a help page.
 */
public class HelpWindow extends UiPart<Stage> {

    public static final String USERGUIDE_URL = "https://ay2425s2-cs2103t-t14-1.github.io/tp/UserGuide.html";
    public static final String HELP_MESSAGE = "Refer to the user guide: " + USERGUIDE_URL;
    public static final String DOWNLOAD_URL = "https://github.com/se-edu/addressbook-level3/releases";

    private static final Logger logger = LogsCenter.getLogger(HelpWindow.class);
    private static final String FXML = "HelpWindow.fxml";

    @FXML
    private Button copyButton;

    @FXML
    private TextField searchBar;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private VBox helpContent;

    @FXML
    private VBox quickStartSection;

    @FXML
    private VBox featuresSection;

    @FXML
    private VBox examplesSection;

    /**
     * Creates a new HelpWindow.
     *
     * @param root Stage to use as the root of the HelpWindow.
     * @param guiSettings The GUI settings to set the window size and position.
     */
    public HelpWindow(Stage root, GuiSettings guiSettings) {
        super(FXML, root);
        setWindowDefaultSize(guiSettings);
    }

    /**
     * Creates a new HelpWindow.
     *
     * @param guiSettings The GUI settings to set the window size and position.
     */
    public HelpWindow(GuiSettings guiSettings) {
        this(new Stage(), guiSettings);
    }

    /**
     * Sets the default size and position of the window based on {@code guiSettings}.
     */
    private void setWindowDefaultSize(GuiSettings guiSettings) {
        Stage root = getRoot();
        root.setHeight(guiSettings.getWindowHeight());
        root.setWidth(guiSettings.getWindowWidth());
        if (guiSettings.getWindowCoordinates() != null) {
            root.setX(guiSettings.getWindowCoordinates().getX());
            root.setY(guiSettings.getWindowCoordinates().getY());
        }
    }

    @FXML
    private void scrollToQuickStart() {
        scrollToSection(quickStartSection);
    }

    @FXML
    private void scrollToFeatures() {
        scrollToSection(featuresSection);
    }

    @FXML
    private void scrollToExamples() {
        scrollToSection(examplesSection);
    }

    private void scrollToSection(VBox section) {
        double targetY = section.getBoundsInParent().getMinY() / helpContent.getHeight();
        scrollPane.setVvalue(targetY);
        section.requestFocus();
    }

    /**
     * Shows the help window.
     */
    public void show() {
        logger.fine("Showing help page about the application.");
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Returns true if the help window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the help window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the help window.
     */
    public void focus() {
        getRoot().requestFocus();
    }

    /**
     * Copies the URL to the user guide to the clipboard.
     */
    @FXML
    private void copyUrl() {
        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent url = new ClipboardContent();
        url.putString(USERGUIDE_URL);
        clipboard.setContent(url);
    }
}
