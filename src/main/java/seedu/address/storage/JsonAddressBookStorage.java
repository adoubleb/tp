package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.ReadOnlyAddressBook;

/**
 * A class to access AddressBook data stored as a json file on the hard disk.
 */
public class JsonAddressBookStorage extends JsonStorage<ReadOnlyAddressBook, JsonSerializableAddressBook>
        implements AddressBookStorage {

    public JsonAddressBookStorage(Path filePath) {
        super(filePath, JsonSerializableAddressBook.class);
    }

    @Override
    public Path getAddressBookFilePath() {
        return getFilePath();
    }

    @Override
    protected JsonSerializableAddressBook createSerializable(ReadOnlyAddressBook data) {
        return new JsonSerializableAddressBook(data);
    }

    @Override
    public Optional<ReadOnlyAddressBook> readAddressBook() throws DataLoadingException {
        return read();
    }

    @Override
    public Optional<ReadOnlyAddressBook> readAddressBook(Path filePath) throws DataLoadingException {
        return read(filePath);
    }

    @Override
    public void saveAddressBook(ReadOnlyAddressBook addressBook) throws IOException {
        save(addressBook);
    }

    @Override
    public void saveAddressBook(ReadOnlyAddressBook addressBook, Path filePath) throws IOException {
        save(addressBook, filePath);
    }
}
