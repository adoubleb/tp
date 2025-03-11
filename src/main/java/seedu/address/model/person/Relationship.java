package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

// ATTRIBUTION: this code was adapted from the Tag.java class created by Yijin, Liang,
// Yong, Tan, Ullas, Rajapakse and Izq.

/**
 * Represents a Relationship between User and Family Member in the address book.
 * Guarantees: immutable, name is valid as declared in {@Link #isValidRelationship(String)}.
 */
public class Relationship {

    public static final String MESSAGE_CONSTRAINTS = "Tags names should be alphanumeric";

    public static final String VALIDATION_REGEX = "\\p{Alnum}+";

    public final String relationship;

    /**
     * Constructs a {@code Relationship}.
     *
     * @param relationship A valid relationship to user.
     */
    public Relationship(String relationship) {
        requireNonNull(relationship);
        checkArgument(isValidTagName(relationship), MESSAGE_CONSTRAINTS);
        this.relationship = relationship;
    }

    /**
     * Returns true if a given string is a valid relationship.
     */
    public static boolean isValidTagName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Relationship)) {
            return false;
        }

        Relationship otherRelationship = (Relationship) other;
        return relationship.equals(otherRelationship.relationship);
    }

    @Override
    public int hashCode() {
        return relationship.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + relationship + ']';
    }
}
