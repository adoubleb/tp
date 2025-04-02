package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_PHONE = new Prefix("p/");
    public static final Prefix PREFIX_EMAIL = new Prefix("e/");
    public static final Prefix PREFIX_ADDRESS = new Prefix("a/");
    public static final Prefix PREFIX_TAG = new Prefix("t/");
    public static final Prefix PREFIX_BIRTHDAY = new Prefix("b/");
    public static final Prefix PREFIX_RELATIONSHIP = new Prefix("r/");
    public static final Prefix PREFIX_NICKNAME = new Prefix("nn/");
    public static final Prefix PREFIX_NOTES = new Prefix("no/");
    public static final Prefix PREFIX_SORT = new Prefix("s/");
    public static final Prefix PREFIX_IMG = new Prefix("img/");
}
