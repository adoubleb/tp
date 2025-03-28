package seedu.address.model.person.namepredicate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.person.Person;

/**
 * A predicate that evaluates whether a given person's name is similar to any of the specified keywords.
 * Similarity is determined based on a similarity threshold using the Levenshtein distance algorithm.
 *
 * The predicate converts both the person's name and the keywords to lowercase, splits the name into words,
 * and checks each keyword against the name's words. If any keyword matches a name word with a similarity
 * score above the threshold (default set to 0.7), the predicate evaluates to true.
 *
 * This class is immutable and ensures equality checks are based on the list of keywords.
 *
 * Implements {@link Predicate} with a type parameter of {@link Person}.
 */
public class NameSimilarPredicate implements Predicate<Person> {
    private final List<String> keywords;
    public NameSimilarPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        String fullName = person.getName().fullName.toLowerCase();
        List<String> nameWords = Arrays.asList(fullName.split("\\s+"));

        for (String keyword : keywords) {
            String keywordLowerCase = keyword.toLowerCase();
            for (String word : nameWords) {
                if (!findSimilarWordsAboveThreshold(new String[]{word}, keywordLowerCase, 0.6).isEmpty()) {
                    return true;
                }
            }
        }
        return false;
    }

    private static List<String> findSimilarWordsAboveThreshold(String[] words, String targetWord, double threshold) {
        // Validate input
        if (words == null || words.length == 0 || targetWord == null) {
            return Collections.emptyList();
        }

        List<String> similarWords = new ArrayList<>();

        for (String word : words) {
            // Calculate Levenshtein Distance
            int distance = StringUtil.levenshteinDistance(word, targetWord);

            // Calculate similarity score as a ratio:
            // Similarity = 1 - (distance / maxLength)
            double maxLength = Math.max(word.length(), targetWord.length());
            double similarity = 1 - (double) distance / maxLength;

            if (similarity >= threshold) {
                similarWords.add(word);
            }
        }

        return similarWords;
    }


    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof NameSimilarPredicate)) {
            return false;
        }

        NameSimilarPredicate otherNameSimilarPrediate = (NameSimilarPredicate) other;
        return keywords.equals(otherNameSimilarPrediate.keywords);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keywords", keywords).toString();
    }
}

