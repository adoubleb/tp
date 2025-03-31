package seedu.address.model.person;

//import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

/**
 * Represents a Person's image path in the address book.
 */
public class ImagePath {
    public static final String MESSAGE_CONSTRAINTS = "Image path should be a valid path to a .png file";
    public static final String DEFAULT_IMAGE_RELATIVE_PATH = "src/main/resources/images/defaultUserPicture.png";
    // private static final Path DEFAULT_IMAGE_PATH = Paths.get(DEFAULT_IMAGE_RELATIVE_PATH);
    private final String path;
    /**
     * Constructs an instance of ImagePath
     *
     * @param pathString Path of the image, as a string
     */
    public ImagePath(String pathString) {
        System.out.println("DEBUGG ImagePath: " + pathString);
        if (!isValidImagePath(pathString)) {
            throw new IllegalArgumentException(MESSAGE_CONSTRAINTS);
        }
        this.path = pathString;
    }

    /**
     * Returns the path of the image.
     *
     * @return Path of the image loaded in Person
     */
    public String getPath() {
        return path;
    }

//    public static ImagePath getDefault() {
//        // This returns a URL to the file within the resources folder
//        String path = Objects.requireNonNull(ImagePath.class.getResource("/images/defaultUserPicture.png"))
//                .getPath();
//        return new ImagePath(path);
//    }

    public static ImagePath getDefault() {
        String resourceUrl = Objects.requireNonNull(
                ImagePath.class.getResource("/images/defaultUserPicture.png")
        ).toExternalForm(); // Works in JARs
        return new ImagePath(resourceUrl);
    }



    /**
     * Returns true if a given string is a valid image path.
     * Will be enforcing .png extensions only for optimal performance.
     *
     * @param pathString String to be checked
     * @return Boolean Value
     */
    public static boolean isValidImagePath(String pathString) {
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
