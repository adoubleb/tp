package seedu.address.ui;

import java.io.File;
import java.util.Comparator;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.stage.FileChooser;
import seedu.address.model.person.Birthday;
import seedu.address.model.person.ImagePath;
import seedu.address.model.person.Person;
import seedu.address.model.person.Relationship;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class PersonCard extends UiPart<Region> {

    private static final String FXML = "PersonListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Person person;
    private final Consumer<String> commandExecutor;
    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label email;
    @FXML
    private Label birthday;
    @FXML
    private Label relationship;
    @FXML
    private Label nickname;
    @FXML
    private Label notes;
    @FXML
    private FlowPane tags;
    @FXML
    private ImageView profileImage;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public PersonCard(Person person, int displayedIndex, Consumer<String> commandExecutor) {
        super(FXML);
        this.person = person;
        this.commandExecutor = commandExecutor;
        id.setText(String.valueOf(displayedIndex));
        name.setText(person.getName().fullName);
        setTextOrHide(nickname, person.getNickname(), nick -> " (" + nick + ")");
        setTextOrHide(phone, person.getPhone(), Object::toString);
        setTextOrHide(address, person.getAddress(), Object::toString);
        setTextOrHide(email, person.getEmail(), Object::toString);
        relationship.setText(person.getRelationship()
                .map(Relationship::getRelationshipString)
                .filter(str -> !str.isEmpty())
                .orElse("No relationship specified"));

        setTextOrHide(birthday, person.getBirthday(), b -> ((Birthday) b).getBirthdayStringFormatted());
        setTextOrHide(notes, person.getNotes(), Object::toString);

        person.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));

        Platform.runLater(() -> {
            try {
                String rawPath = person.getImagePath().getPath();
                Image image;

                if (rawPath.startsWith("jar:") || rawPath.startsWith("file:") || rawPath.startsWith("http")) {
                    image = new Image(rawPath, true);
                } else {
                    File file = new File(rawPath);
                    if (file.exists()) {
                        image = new Image(file.toURI().toString(), true);
                    } else {
                        // Fallback to classpath resource
                        image = new Image(ImagePath.class.getResourceAsStream("/images/defaultUserPicture.png"));
                    }
                }

                profileImage.setImage(image);
            } catch (Exception e) {
                System.out.println("Failed to load image for " + person.getName().fullName + ": " + e.getMessage());
            }
        });
        profileImage.setOnMouseClicked(event -> {
            event.consume();
            handleImageClick();
        });
        profileImage.setCursor(javafx.scene.Cursor.HAND);
    }
    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    private void setTextOrHide(Label label, Optional<?> optionalValue, Function<Object, String> mapper) {
        String text = optionalValue.map(mapper).orElse("");
        if (text.isEmpty()) {
            label.setVisible(false);
            label.setManaged(false);
        } else {
            label.setText(text);
        }
    }

    private void handleImageClick() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select New Profile Image");
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Png Images", "*.png")
        );

        File selectedFile = fileChooser.showOpenDialog(profileImage.getScene().getWindow());
        if (selectedFile == null) {
            return;
        }

        String path = selectedFile.getAbsolutePath();

        if (!ImagePath.isValidImagePath(path)) {
            System.out.println("Invalid image path: " + path);
            return;
        }

        // Setting image immediately for better UI responsiveness
        profileImage.setImage(new Image(selectedFile.toURI().toString()));

        int zeroBasedIndex = Integer.parseInt(id.getText()) - 1;
        String command = String.format("edit %d img/%s", zeroBasedIndex + 1, path);

        try {
            commandExecutor.accept(command);
        } catch (Exception e) {
            System.out.println("Failed to execute image edit command: " + e.getMessage());
        }
    }
}
