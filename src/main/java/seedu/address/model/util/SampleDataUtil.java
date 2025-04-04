package seedu.address.model.util;

import java.util.Arrays;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
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
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"),
                    Optional.of(new Phone("87438807")),
                    Optional.of(new Email("alexyeoh@example.com")),
                    Optional.of(new Address("Blk 30 Geylang Street 29, #06-40")),
                    Optional.of(new Birthday("01-01-1990")),
                    Optional.of(new Relationship("Brother")),
                    Optional.of(new Nickname("Al")),
                    Optional.of(new Notes("Likes photography")), Optional.empty(),
                    getTagSet("friends")),
            new Person(new Name("Bernice Yu"),
                    Optional.of(new Phone("99272758")),
                    Optional.of(new Email("berniceyu@example.com")),
                    Optional.of(new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18")),
                    Optional.of(new Birthday("15-03-1992")),
                    Optional.of(new Relationship("Kor Kor")),
                    Optional.of(new Nickname("Bernie")), Optional.empty(), Optional.empty(),
                    getTagSet("colleagues", "friends")),
            new Person(new Name("Charlotte Oliveiro"),
                    Optional.of(new Phone("93210283")),
                    Optional.of(new Email("charlotte@example.com")),
                    Optional.of(new Address("Blk 11 Ang Mo Kio Street 74, #11-04")),
                    Optional.of(new Birthday("22-07-1995")),
                    Optional.of(new Relationship("Cousin")), Optional.empty(),
                    Optional.of(new Notes("Allergic to peanuts")), Optional.empty(),
                    getTagSet("neighbours")),
            new Person(new Name("David Li"),
                    Optional.of(new Phone("91031282")),
                    Optional.of(new Email("lidavid@example.com")),
                    Optional.of(new Address("Blk 436 Serangoon Gardens Street 26, #16-43")),
                    Optional.of(new Birthday("30-12-1989")),
                    Optional.of(new Relationship("Old Classmate")),
                    Optional.of(new Nickname("Dave")),
                    Optional.of(new Notes("Prefers email contact")), Optional.empty(),
                    getTagSet("family")),
            new Person(new Name("Irfan Ibrahim"),
                    Optional.of(new Phone("92492021")),
                    Optional.of(new Email("irfan@example.com")),
                    Optional.of(new Address("Blk 47 Tampines Street 20, #17-35")),
                    Optional.of(new Birthday("05-09-1993")),
                    Optional.of(new Relationship("Tutor")), Optional.empty(), Optional.empty(),
                    Optional.empty(),
                    getTagSet("classmates")),
            new Person(new Name("Roy Balakrishnan"),
                    Optional.of(new Phone("92624417")),
                    Optional.of(new Email("royb@example.com")),
                    Optional.of(new Address("Blk 45 Aljunied Street 85, #11-31")),
                    Optional.of(new Birthday("12-06-1988")),
                    Optional.of(new Relationship("Best-Friend")),
                    Optional.of(new Nickname("RB")),
                    Optional.of(new Notes("Birthday gift idea: books")), Optional.empty(),
                    getTagSet("colleagues"))
        };
    }
    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

}
