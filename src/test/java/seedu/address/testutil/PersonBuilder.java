package seedu.address.testutil;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

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
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final String DEFAULT_BIRTHDAY = "01-01-1990";
    public static final String DEFAULT_RELATIONSHIP = "Mother";
    public static final String DEFAULT_NICKNAME = "Nicholas";
    public static final String DEFAULT_NOTES = "Allergic to peanuts";

    private Name name;
    private Phone phone;
    private Email email;
    private Address address;
    private Optional<Birthday> birthday;
    private Optional<Relationship> relationship;
    private Optional<Nickname> nickname;
    private Optional<Notes> notes;
    private Set<Tag> tags;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        birthday = Optional.empty();
        relationship = Optional.empty();
        nickname = Optional.empty();
        notes = Optional.empty();
        tags = new HashSet<>();
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        name = personToCopy.getName();
        phone = personToCopy.getPhone();
        email = personToCopy.getEmail();
        address = personToCopy.getAddress();
        birthday = personToCopy.getBirthday();
        relationship = personToCopy.getRelationship();
        nickname = personToCopy.getNickname();
        notes = personToCopy.getNotes();
        tags = new HashSet<>(personToCopy.getTags());
    }

    /**
     * Constructs a {@code PersonBuilder} with an option to include default optional fields.
     *
     * @param allOptionalFieldsPresent If true, initializes all optional fields with default values;
     *                                 otherwise, optional fields remain uninitialized.
     */
    public PersonBuilder(boolean allOptionalFieldsPresent) {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        birthday = Optional.of(new Birthday(DEFAULT_BIRTHDAY));
        relationship = Optional.of(new Relationship(DEFAULT_RELATIONSHIP));
        nickname = Optional.of(new Nickname(DEFAULT_NICKNAME));
        notes = Optional.of(new Notes(DEFAULT_NOTES));
        tags = new HashSet<>();
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public PersonBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Person} that we are building.
     */
    public PersonBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Birthday} of the {@code Person} that is being built.
     *
     * @param birthday A string representing the birthday in the format DD-MM-YYYY.
     *                 The birthday must be valid and should not be a future date.
     * @return The updated {@code PersonBuilder} instance with the specified birthday set.
     * @throws NullPointerException if {@code birthday} is null.
     * @throws IllegalArgumentException if {@code birthday} is in an invalid format or is a future date.
     */
    public PersonBuilder withBirthday(String birthday) {
        this.birthday = Optional.of(new Birthday(birthday));
        return this;
    }

    /**
     * Sets the {@code Relationship} of the {@code Person} that is being built.
     *
     * @param relationship A string representing the relationship of family member to User.
     *                     The relationship must be declared in
     *                     a valid manner, meeting the {@Link #isValidRelationship(String)}.
     * @return The updated {@code PersonBuilder} instance with the specified relationship.
     * @throws NullPointerException if {@code relationship} is null.
     * @throws IllegalArgumentException if {@code relationship} is in an invalid format.
     */
    public PersonBuilder withRelationship(String relationship) {
        this.relationship = Optional.of(new Relationship(relationship));
        return this;
    }
    /**
     * Sets the {@code Nickname} of the {@code Person} that we are building.
     */
    public PersonBuilder withNickname(String nickname) {
        this.nickname = Optional.of(new Nickname(nickname));
        return this;
    }

    /**
     * Sets the {@code Notes} of the {@code Person} that we are building.
     */
    public PersonBuilder withNotes(String notes) {
        this.notes = Optional.of(new Notes(notes));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Person} that we are building.
     */
    public PersonBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Person} that we are building.
     */
    public PersonBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    public Person build() {
        return new Person(name, phone, email, address, birthday, relationship, nickname, notes, tags);
    }

}
