package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's nickname in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidNickname(String)}
 */
public class Nickname {

    public static final String MESSAGE_CONSTRAINTS =
            "Nicknames should be less than 30 characters long and can contain letters, numbers, "
                    + "spaces and the following special characters: .,'!@#&()-_+=";

    /*
     * The first character of the nickname must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final int MAX_LENGTH = 30;
    public static final String VALIDATION_REGEX = "^(?!\\s)[\\p{L}\\p{N} .,'!@#&()-_+=]*$";

    public final String nickname;

    /**
     * Constructs a {@code Nickname}.
     *
     * @param nickname A valid nickname.
     */
    public Nickname(String nickname) {
        requireNonNull(nickname);
        checkArgument(isValidNickname(nickname), MESSAGE_CONSTRAINTS);
        this.nickname = nickname;
    }

    /**
     * Returns true if a given string is a valid nickname.
     */
    public static boolean isValidNickname(String test) {
        return test.matches(VALIDATION_REGEX) && test.length() <= MAX_LENGTH;
    }

    @Override
    public String toString() {
        return nickname;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Nickname)) {
            return false;
        }

        Nickname otherNickname = (Nickname) other;
        return nickname.equals(otherNickname.nickname);
    }

    @Override
    public int hashCode() {
        return nickname.hashCode();
    }
}
