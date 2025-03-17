package seedu.address.logic.parser.exceptions;

/**
 * Represents a Duplicate Index parse error encountered by a parser.
 */
public class DuplicateIndexParseException extends ParseException {
    public DuplicateIndexParseException(String message) {
        super(message);
    }

    public DuplicateIndexParseException(String message, Throwable cause) {
        super(message, cause);
    }
}
