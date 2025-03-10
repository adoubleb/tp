package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.DateTimeException;
import java.time.LocalDate;


/**
 * Represents a Person's birthday in the address book.
 * Guarantees: immutable; is valid as declared in #isValidBirthday(String)
 */
public class Birthday {

    public static final String MESSAGE_BIRTHDAY_CONSTRAINTS =
            "Birthdays should be in the format DD-MM-YYYY and must be a valid date that is not in the future";


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
        checkArgument(isValidBirthday(birthday), MESSAGE_BIRTHDAY_CONSTRAINTS);
        value = birthday;
    }

    /**
     * Returns if a given string is a valid birthday
     */
    public static boolean isValidBirthday(String test) {
        // Check format first
        if (!test.matches("\\d{2}-\\d{2}-\\d{4}")) {
            return false;
        }

        try {
            // Parse the date
            String[] parts = test.split("-");
            int day = Integer.parseInt(parts[0]);
            int month = Integer.parseInt(parts[1]);
            int year = Integer.parseInt(parts[2]);

            // Create LocalDate for validation
            LocalDate birthDate = LocalDate.of(year, month, day);
            LocalDate today = LocalDate.now();

            // Check if date is not in the future
            return !birthDate.isAfter(today);

        } catch (DateTimeException e) {
            // This will catch invalid dates like 31/02/2000
            return false;
        }
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
