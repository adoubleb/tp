package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Birthday;
import seedu.address.model.person.Email;
import seedu.address.model.person.ImagePath;
import seedu.address.model.person.Name;
import seedu.address.model.person.Nickname;
import seedu.address.model.person.Notes;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Relationship;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Person}.
 */
class JsonAdaptedPerson {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    private final String name;
    private final String phone;
    private final String email;
    private final String address;
    private final String birthday;
    private final String relationship;
    private final String nickname;
    private final String notes;
    private final List<JsonAdaptedTag> tags = new ArrayList<>();
    private final String imagePath;

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedPerson(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
            @JsonProperty("email") String email, @JsonProperty("address") String address,
                @JsonProperty("birthday") String birthday, @JsonProperty("relationship") String relationship,
                @JsonProperty("nickname") String nickname, @JsonProperty("notes") String notes,
                             @JsonProperty("imagePath") String imagePath,
                             @JsonProperty("tags") List<JsonAdaptedTag> tags) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.birthday = birthday;
        this.relationship = relationship;
        this.nickname = nickname;
        this.notes = notes;
        if (tags != null) {
            this.tags.addAll(tags);
        }
        this.imagePath = imagePath;
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedPerson(Person source) {
        name = source.getName().fullName;
        phone = source.getPhone().value;
        email = source.getEmail().value;
        address = source.getAddress().value;
        birthday = source.getBirthday().map(b -> b.value).orElse("");
        relationship = source.getRelationship().map(b->b.relationship).orElse("");
        nickname = source.getNickname().map(Nickname::toString).orElse("");
        notes = source.getNotes().map(Notes::toString).orElse("");
        tags.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
        imagePath = source.getImagePath().toString();
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Person toModelType() throws IllegalValueException {
        final List<Tag> personTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tags) {
            personTags.add(tag.toModelType());
        }

        final Name modelName = parseRequiredField(name, Name::new, Name::isValidName,
                String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()), Name.MESSAGE_CONSTRAINTS);

        final Phone modelPhone = parseRequiredField(phone, Phone::new, Phone::isValidPhone,
                String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName()), Phone.MESSAGE_CONSTRAINTS);

        final Email modelEmail = parseRequiredField(email, Email::new, Email::isValidEmail,
                String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()), Email.MESSAGE_CONSTRAINTS);

        final Address modelAddress = parseRequiredField(address, Address::new, Address::isValidAddress,
                String.format(MISSING_FIELD_MESSAGE_FORMAT,
                        Address.class.getSimpleName()), Address.MESSAGE_CONSTRAINTS);

        final Optional<Birthday> modelBirthday = parseOptionalField(birthday, Birthday::new);
        final Optional<Relationship> modelRelationship = parseOptionalField(relationship, Relationship::new);
        final Optional<Nickname> modelNickname = parseOptionalField(nickname, Nickname::new);
        final Optional<Notes> modelNotes = parseOptionalField(notes, Notes::new);

        final Set<Tag> modelTags = new HashSet<>(personTags);
        final Optional<ImagePath> modelImagePath = parseOptionalField(imagePath, ImagePath::new);
        return new Person(modelName, modelPhone, modelEmail, modelAddress,
                modelBirthday, modelRelationship, modelNickname, modelNotes, modelImagePath, modelTags);
    }

    /**
     * Parses a required field from its string representation into the corresponding model object.
     *
     * @param <T>             The type of the model object to be constructed.
     * @param value           The string representation of the field.
     * @param constructor     A function that constructs the model object from a valid string.
     * @param validator       A predicate that checks the validity of the string.
     * @param missingMessage  The error message to throw if the value is null.
     * @param invalidMessage  The error message to throw if the value is invalid.
     * @return The constructed model object.
     * @throws IllegalValueException If the value is null or invalid.
     */
    private <T> T parseRequiredField(String value, Function<String, T> constructor,
                                     Predicate<String> validator, String missingMessage, String invalidMessage)
            throws IllegalValueException {
        if (value == null) {
            throw new IllegalValueException(missingMessage);
        }
        if (!validator.test(value)) {
            throw new IllegalValueException(invalidMessage);
        }
        return constructor.apply(value);
    }

    /**
     * Parses an optional field from its string representation into an {@code Optional} containing the model object.
     *
     * @param <T>         The type of the model object to be constructed.
     * @param value       The string representation of the optional field.
     * @param constructor A function that constructs the model object from the string.
     * @return {@code Optional} containing the  object if the string is non-empty; otherwise {@code Optional.empty()}.
     * @throws IllegalValueException If the string is non-empty but invalid for the model type.
     */
    private <T> Optional<T> parseOptionalField(String value, Function<String, T> constructor)
            throws IllegalValueException {
        if (value == null || value.isEmpty()) {
            return Optional.empty();
        }
        try {
            return Optional.of(constructor.apply(value));
        } catch (IllegalArgumentException e) {
            throw new IllegalValueException(e.getMessage());
        }
    }

}
