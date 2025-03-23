package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.time.LocalDate;
import java.time.MonthDay;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;

import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Lists all persons in the address book to the user.
 */
public class ListCommand extends Command {
    /**
     * Represents the sort order of the list command.
     */
    public enum SortOrder {
        ASCENDING,
        DESCENDING,
        NONE
    }
    public static final String COMMAND_WORD = "list";
    public static final String MESSAGE_SUCCESS = "Listed all persons";
    public static final String MESSAGE_ASC_SUCCESS = "Listed all persons by upcoming birthdays (soonest first)";
    public static final String MESSAGE_DESC_SUCCESS = "Listed all persons by upcoming birthdays (latest first)";
    private final SortOrder sortOrder;

    public ListCommand() {
        this.sortOrder = SortOrder.NONE;
    }
    public ListCommand(SortOrder sortOrder) {
        this.sortOrder = sortOrder;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        if (this.sortOrder == SortOrder.NONE) {
            model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
            model.sortFilteredPersonList(null);
            return new CommandResult(MESSAGE_SUCCESS);
        } else {
            String successMessage = this.sortOrder == SortOrder.ASCENDING ? MESSAGE_ASC_SUCCESS : MESSAGE_DESC_SUCCESS;
            model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
            LocalDate today = LocalDate.now();

            Comparator<Person> birthdayComparator = Comparator.comparing(
                    person -> person.getBirthday()
                            .map(b -> {
                                LocalDate birthDate = b.getLocalDate();
                                if (birthDate == null) {
                                    return Long.MAX_VALUE;
                                }

                                MonthDay birthdayMonthDay = MonthDay.from(birthDate);
                                MonthDay currentMonthDay = MonthDay.from(today);
                                LocalDate nextBirthday = birthdayMonthDay.atYear(today.getYear());
                                if (birthdayMonthDay.isBefore(currentMonthDay)) {
                                    nextBirthday = nextBirthday.plusYears(1);
                                }

                                return ChronoUnit.DAYS.between(today, nextBirthday);
                            })
                            .orElse(Long.MAX_VALUE),
                    Comparator.naturalOrder()
            );

            if (sortOrder == SortOrder.DESCENDING) {
                birthdayComparator = birthdayComparator.reversed();
            }
            model.sortFilteredPersonList(birthdayComparator);

            return new CommandResult(successMessage);
        }
    }
}
