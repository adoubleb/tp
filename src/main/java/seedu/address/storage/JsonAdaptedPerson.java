package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Birthday;
import seedu.address.model.person.Email;
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

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedPerson(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
            @JsonProperty("email") String email, @JsonProperty("address") String address,
                @JsonProperty("birthday") String birthday, @JsonProperty("relationship") String relationship, 
                @JsonProperty("nickname") String nickname, @JsonProperty("notes") String notes, @JsonProperty("tags") List<JsonAdaptedTag> tags) {
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

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (phone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName()));
        }
        if (!Phone.isValidPhone(phone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        final Phone modelPhone = new Phone(phone);

        if (email == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        }
        if (!Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        final Email modelEmail = new Email(email);

        if (address == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName()));
        }
        if (!Address.isValidAddress(address)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }

        final Address modelAddress = new Address(address);

        final Optional<Birthday> modelBirthday;
        if (birthday == null || birthday.isEmpty()) {
            modelBirthday = Optional.empty();
        } else {
            try {
                modelBirthday = Optional.of(new Birthday(birthday));
            } catch (IllegalArgumentException e) {
                throw new IllegalValueException(e.getMessage());
            }
        }

        final Optional<Relationship> modelRelationship;
        if (relationship == null) {
            modelRelationship = Optional.empty();
        } else {
            try {
                modelRelationship = Optional.of(new Relationship(relationship));
              
        final Optional<Nickname> modelNickname;
        if (nickname == null || nickname.isEmpty()) {
            modelNickname = Optional.empty();
        } else {
            try {
                modelNickname = Optional.of(new Nickname(nickname));
            } catch (IllegalArgumentException e) {
                throw new IllegalValueException(e.getMessage());
            }
        }

        final Optional<Notes> modelNotes;
        if (notes == null || notes.isEmpty()) {
            modelNotes = Optional.empty();
        } else {
            try {
                modelNotes = Optional.of(new Notes(notes));
            } catch (IllegalArgumentException e) {
                throw new IllegalValueException(e.getMessage());
            }
        }

        final Set<Tag> modelTags = new HashSet<>(personTags);
        return new Person(modelName, modelPhone, modelEmail, modelAddress, modelBirthday, modelRelationship,
                modelNickname, modelNotes, modelTags);
    }

}
