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
import seedu.address.commons.core.LogsCenter;

/**
 * Controller for a help page.
 */
public class HelpWindow extends UiPart<Stage> {

    public static final String USERGUIDE_URL = "https://ay2425s2-cs2103t-t14-1.github.io/tp/UserGuide.html";
    public static final String HELP_MESSAGE = "Refer to the user guide: " + USERGUIDE_URL;

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

    /**
     * Creates a new HelpWindow.
     *
     * @param root Stage to use as the root of the HelpWindow.
     */
    public HelpWindow(Stage root) {
        super(FXML, root);
        initializeHelpContent();
    }

    /**
     * Creates a new HelpWindow.
     */
    public HelpWindow() {
        this(new Stage());
    }

    /**
     * Initializes the help content with formatted text and code blocks.
     */
    private void initializeHelpContent() {
        // Clear existing content
        helpContent.getChildren().clear();

        // Add title
        TextFlow titleFlow = new TextFlow();
        Label titleLabel = new Label("WhoAreYouAgain? User Guide");
        titleLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: white;");
        titleFlow.getChildren().add(titleLabel);
        helpContent.getChildren().add(titleFlow);

        // Add quick start section
        TextFlow quickStartFlow = new TextFlow();
        Label quickStartTitle = new Label("Quick Start");
        quickStartTitle.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: white;");
        Label quickStartContent = new Label(
                "\n1. Ensure you have Java `17` or above installed in your Computer.\n" +
                        "2. Download the latest `.jar` file from [here](https://github.com/se-edu/addressbook-level3/releases).\n" +
                        "3. Copy the file to the folder you want to use as the _home folder_ for your AddressBook.\n" +
                        "4. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar whoareyouagain.jar` command to run the application.\n" +
                        "5. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.\n\n"
        );
        quickStartContent.setStyle("-fx-text-fill: white;");
        quickStartFlow.getChildren().addAll(quickStartTitle, quickStartContent);
        helpContent.getChildren().add(quickStartFlow);

        // Add features section
        TextFlow featuresFlow = new TextFlow();
        Label featuresTitle = new Label("Features");
        featuresTitle.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: white;");
        Label featuresContent = new Label(
                "\n**Add**\nFormat: `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [r/RELATIONSHIP] [nn/NICKNAME] [b/BIRTHDAY] [no/NOTES] [t/TAG]…​`\n\n" +
                        "**List**\nFormat: `list`\n\n" +
                        "**Edit**\nFormat: `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [r/RELATIONSHIP] [nn/NICKNAME] [b/BIRTHDAY] [no/NOTES] [t/TAG]…​`\n\n" +
                        "**Find**\nFormat: `find KEYWORD [MORE_KEYWORDS]`\n\n" +
                        "**Delete**\nFormat: `delete INDEX…​`\n\n" +
                        "**Clear**\nFormat: `clear`\n\n" +
                        "**Exit**\nFormat: `exit`\n\n"
        );
        featuresContent.setStyle("-fx-text-fill: white;");
        featuresFlow.getChildren().addAll(featuresTitle, featuresContent);
        helpContent.getChildren().add(featuresFlow);

        // Add code blocks
        TextFlow codeBlockFlow = new TextFlow();
        Label codeBlockAdd = new Label("Example: `add n/Nickie p/88888888 r/son e/nickie@gmail.com a/21 Lower Kent Ridge Rd, Singapore 119077 nn/nickelodeon b/2001-01-01 no/My favorite son`");
        codeBlockAdd.setStyle("-fx-font-family: monospace; -fx-background-color: #2d2d2d; -fx-padding: 5px; -fx-text-fill: white;");
        Label codeBlockEdit = new Label("\nExample: `edit 1 p/91234567 e/johndoe@example.com`");
        codeBlockEdit.setStyle("-fx-font-family: monospace; -fx-background-color: #2d2d2d; -fx-padding: 5px; -fx-text-fill: white;");
        codeBlockFlow.getChildren().addAll(codeBlockAdd, codeBlockEdit);
        helpContent.getChildren().add(codeBlockFlow);
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

    /**
     * Handles the search action.
     */
    @FXML
    private void handleSearch() {
        String searchText = searchBar.getText().toLowerCase();
        // Implement search logic here
        // For example, highlight or filter content based on the search text
    }
}