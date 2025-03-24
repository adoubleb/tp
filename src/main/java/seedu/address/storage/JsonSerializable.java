package seedu.address.storage;

import seedu.address.commons.exceptions.IllegalValueException;

/**
 * Represents a JSON-serializable object that can be converted to a model type.
 * @param <T> The model type this serializable object converts to
 */
public interface JsonSerializable<T> {
    /**
     * Converts this serializable object into the model type.
     * @return The model representation of this object
     * @throws IllegalValueException if there were issues with the data values
     */
    T toModelType() throws IllegalValueException;
}
