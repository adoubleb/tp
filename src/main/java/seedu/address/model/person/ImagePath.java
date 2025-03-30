package seedu.address.model.person;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

/**
 * Represents a Person's image path in the address book.
 */
public class ImagePath {
    public static final String MESSAGE_CONSTRAINTS = "Image path should be a valid path to a .png file";
    public static final String DEFAULT_IMAGE_RELATIVE_PATH = "src/main/resources/images/defaultUserPicture.png";
    private static final Path DEFAULT_IMAGE_PATH = Paths.get(DEFAULT_IMAGE_RELATIVE_PATH);
    private final Path path;
    /**
     * Constructs an instance of ImagePath
     *
     * @param pathString Path of the image, as a string
     */
    public ImagePath(String pathString) {
        if (!isValidImagePath(pathString)) {
            throw new IllegalArgumentException(MESSAGE_CONSTRAINTS);
        }
        this.path = Paths.get(pathString);
    }

    /**
     * Returns the path of the image.
     *
     * @return Path of the image loaded in Person
     */
    public Path getPath() {
        return path;
    }

    public static ImagePath getDefault() {
        return new ImagePath(DEFAULT_IMAGE_PATH.toString());
    }

    /**
     * Returns true if a given string is a valid image path.
     * Will be enforcing .png extensions only for optimal performance.
     *
     * @param pathString String to be checked
     * @return Boolean Value
     */
    public static boolean isValidImagePath(String pathString) {
        File file = new File(pathString);
        if (!(file.exists() && file.isFile() && file.canRead())) {
            return false;
        }

        String lowerCasePath = pathString.toLowerCase();
        return lowerCasePath.endsWith(".png");
    }

    @Override
    public String toString() {
        return path.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof ImagePath
                && path.equals(((ImagePath) other).path));
    }

    @Override
    public int hashCode() {
        return Objects.hash(path);
    }
}
