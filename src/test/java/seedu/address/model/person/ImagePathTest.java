package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ImagePathTest {

    private File validPngFile;
    private File validJpgFile;
    private File unreadableFile;
    private File nonExistentFile;

    @BeforeEach
    public void setUp() throws IOException {
        validPngFile = File.createTempFile("testImage", ".png");
        validPngFile.deleteOnExit();

        validJpgFile = File.createTempFile("testImage", ".jpg");
        validJpgFile.deleteOnExit();

        unreadableFile = File.createTempFile("unreadableImage", ".png");
        unreadableFile.setReadable(false);
        unreadableFile.deleteOnExit();

        nonExistentFile = new File("nonexistent.png");
        nonExistentFile.deleteOnExit();
    }

    @AfterEach
    public void tearDown() {
        validPngFile.delete();
        validJpgFile.delete();
        unreadableFile.delete();
    }

    @Test
    public void constructor_validPngFile_doesNotThrow() {
        assertDoesNotThrow(() -> new ImagePath(validPngFile.getAbsolutePath()));
    }

    @Test
    public void constructor_invalidExtension_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () ->
                new ImagePath(validJpgFile.getAbsolutePath()));
    }

    @Test
    public void constructor_unreadableFile_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () ->
                new ImagePath(unreadableFile.getAbsolutePath()));
    }

    @Test
    public void constructor_nonExistentFile_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () ->
                new ImagePath(nonExistentFile.getAbsolutePath()));
    }

    @Test
    public void isValidImagePath_validPng_returnsTrue() {
        assertTrue(ImagePath.isValidImagePath(validPngFile.getAbsolutePath()));
    }

    @Test
    public void isValidImagePath_wrongExtension_returnsFalse() {
        assertFalse(ImagePath.isValidImagePath(validJpgFile.getAbsolutePath()));
    }

    @Test
    public void isValidImagePath_unreadable_returnsFalse() {
        assertFalse(ImagePath.isValidImagePath(unreadableFile.getAbsolutePath()));
    }

    @Test
    public void isValidImagePath_nonexistent_returnsFalse() {
        assertFalse(ImagePath.isValidImagePath(nonExistentFile.getAbsolutePath()));
    }

    @Test
    public void getPath_returnsCorrectPath() {
        ImagePath imagePath = new ImagePath(validPngFile.getAbsolutePath());
        assertEquals(Path.of(validPngFile.getAbsolutePath()), imagePath.getPath());
    }

    @Test
    public void equals_samePath_returnsTrue() {
        ImagePath path1 = new ImagePath(validPngFile.getAbsolutePath());
        ImagePath path2 = new ImagePath(validPngFile.getAbsolutePath());
        assertEquals(path1, path2);
    }

    @Test
    public void hashCode_samePath_returnsSameHash() {
        ImagePath path1 = new ImagePath(validPngFile.getAbsolutePath());
        ImagePath path2 = new ImagePath(validPngFile.getAbsolutePath());
        assertEquals(path1.hashCode(), path2.hashCode());
    }

    @Test
    public void toString_matchesPath() {
        ImagePath path = new ImagePath(validPngFile.getAbsolutePath());
        assertEquals(validPngFile.getAbsolutePath(), path.toString());
    }

    @Test
    public void getDefault_doesNotThrow() {
        assertDoesNotThrow(ImagePath::getDefault);
    }
}
