package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Person's name in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidName(String)}
 */
public class Name {

    public static final int MAX_LENGTH = 300;
    public static final String MESSAGE_CONSTRAINTS_LENGTH = "Names can be at most " + MAX_LENGTH
            + " characters long";
    public static final String MESSAGE_CONSTRAINTS_CHARACTERS =
            "Names should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX =
            "^[\\p{L}][\\p{L}0-9 ]*(?:[@.,'/\\\\-][\\p{L}0-9 ]+)*$";

    public final String fullName;

    /**
     * Constructs a {@code Name}.
     *
     * @param name A valid name.
     */
    public Name(String name) {
        requireNonNull(name);
        isValidName(name);
        fullName = name;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidName(String test) {
        if (test.isEmpty()) {
            return true;
        }
        if (test.length() > MAX_LENGTH) {
            throw new IllegalArgumentException(MESSAGE_CONSTRAINTS_LENGTH);
        }
        if (!test.matches(VALIDATION_REGEX)) {
            throw new IllegalArgumentException(MESSAGE_CONSTRAINTS_CHARACTERS);
        }
        return true;
    }

    @Override
    public String toString() {
        return fullName;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Name)) {
            return false;
        }

        Name otherName = (Name) other;
        return fullName.equals(otherName.fullName);
    }

    @Override
    public int hashCode() {
        return fullName.hashCode();
    }

}
