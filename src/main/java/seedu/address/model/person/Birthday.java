package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

import java.time.DateTimeException;
import java.time.LocalDate;

import seedu.address.logic.parser.exceptions.ParseException;


/**
 * Represents a Person's birthday in the address book.
 * Guarantees: immutable; is valid as declared in #isValidBirthday(String)
 */
public class Birthday {

    public static final String MESSAGE_BIRTHDAY_CONSTRAINTS =
            "Birthdays should be in the format DD-MM-YYYY and must be a valid date that is not in the future";

    public static final String MESSAGE_BIRTHDAY_CONSTRAINTS_FORMAT = "Birthdays should be in the format DD-MM-YYYY";

    public static final String MESSAGE_BIRTHDAY_CONSTRAINTS_INVALID = "Birthdays should be a real date";

    public static final String MESSAGE_BIRTHDAY_CONSTRAINTS_FUTURE = "Birthdays can't be in the future!";

    public static final String VALIDATION_REGEX = "\\d{2}-\\d{2}-\\d{4}";


    public final String value;

    /**
     * Constructs a {@code Birthday}.
     *
     * @param birthday A valid birthday in the format DD/MM/YYYY that is not a future date.
     * @throws NullPointerException if {@code birthday} is null.
     * @throws IllegalArgumentException if {@code birthday} is invalid.
     */
    public Birthday(String birthday) {
        requireNonNull(birthday);
        birthday = birthday.trim();
        isValidBirthday(birthday);
        value = birthday;
    }

    /**
     * Returns if a given string is a valid birthday
     */
    public static boolean isValidBirthday(String test) {
        if (!test.matches(VALIDATION_REGEX)) {
            throw new IllegalArgumentException(MESSAGE_BIRTHDAY_CONSTRAINTS_FORMAT);
        }
        try {
            String[] parts = test.split("-");
            int day = Integer.parseInt(parts[0]);
            int month = Integer.parseInt(parts[1]);
            int year = Integer.parseInt(parts[2]);

            // Create LocalDate for validation
            LocalDate birthDate = LocalDate.of(year, month, day);
            LocalDate today = LocalDate.now();

            // Check if date is not in the future
            if (birthDate.isAfter(today)) {
                throw new IllegalArgumentException(MESSAGE_BIRTHDAY_CONSTRAINTS_FUTURE);
            }

        } catch (DateTimeException e) {
            throw new IllegalArgumentException(MESSAGE_BIRTHDAY_CONSTRAINTS_INVALID);
        }
        return true;
    }


    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Birthday)) {
            return false;
        }

        Birthday otherBirthday = (Birthday) other;
        return value.equals(otherBirthday.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
