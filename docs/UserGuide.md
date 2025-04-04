---
  layout: default.md
  title: "User Guide"
  pageNav: 3
---

# WhoAreYouAgain? User Guide

**WhoAreYouAgain?** is a **desktop application** for managing your family contacts, optimized for use via a **Command Line Interface (CLI)** while still offering the benefits of a **Graphical User Interface (GUI)**. If you can type fast, WhoAreYouAgain? helps you keep track of relatives and birthdays more efficiently than traditional address book apps—so you’ll never awkwardly forget a name at reunions again.

<!-- * Table of Contents -->
<page-nav-print />

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `17` or above installed in your Computer.<br>
   **Mac users:** Ensure you have the precise JDK version prescribed [here](https://se-education.org/guides/tutorials/javaInstallationMac.html).

1. Download the latest `.jar` file from [here](https://github.com/se-edu/addressbook-level3/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your AddressBook.

1. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar whoareyouagain.jar` command to run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * `list` : Lists all contacts.

   * `add n/Nickie p/88888888 r/son e/nickie@gmail.com a/21 Lower Kent Ridge Rd, Singapore 119077 nn/nickelodeon b/2001-01-01 no/My favorite son` : Adds a relative named `Nickie` to the family book.

   * `delete 3` : Deletes the 3rd contact shown in the current list.

   * `clear` : Deletes all contacts.

   * `exit` : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<box type="info" seamless>

**Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [e/EMAIL]` can be used as `n/John Doe e/johnDoe@gmail.com` or as `n/John Doe`.

* Items with `…`​ after them can accept multiple values.<br>
  e.g. `delete…​` can be used as `delete 1`, `delete 1 2 4`, etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

* If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines as space characters surrounding line-breaks may be omitted when copied over to the application.
</box>

### Viewing help : `help`

Shows a message explaining how to access the help page.

![help message](images/helpMessage.png)

Format: `help`


### Adding a person: `add`

Adds a person to the address book.

Format: `addn/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [r/RELATIONSHIP] [nn/NICKNAME] [b/BIRTHDAY] [no/NOTES] [t/TAG]…​`

### Name Requirements
✔ **Must start with a letter** (A-Z, a-z)  
✔ **Cannot end with a special character** (@, ., -, etc.)  
✔ **No consecutive special characters** (e.g., `--`, `@@`, `..`)

💡 **All `/` must be escaped with `\` to be recognized correctly**

E.g. **To include "s/o" in a name, type it as** `"s\/o"`

[//]: # (<box type="tip" seamless>)

[//]: # ()
[//]: # (**Tip:** A person can have any number of tags &#40;including 0&#41;)

[//]: # (</box>)

Examples:
* `add n/Nickie p/88888888 r/son e/nickie@gmail.com a/21 Lower Kent Ridge Rd, Singapore 119077 nn/nickelodeon b/2001-01-01 no/My favorite son t/son`
* `add n/Betsy Crowe p/99999999 r/Other e/betsycrowe@example.com a/Newgate Prison b/2001-03-30 no/Son's girlfriend`

### Listing all persons : `list`

Shows a list of all persons in the address book.

Format: `list`

### Editing a person : `edit`

Edits an existing person in the address book.

Format: `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [r/RELATIONSHIP] [nn/NICKNAME] [b/BIRTHDAY] [no/NOTES] [t/TAG]…​`

* Edits the person at the specified `INDEX`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the person will be removed i.e adding of tags is not cumulative.
* You can remove all the person’s tags by typing `t/` without
    specifying any tags after it.

Examples:
*  `edit 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st person to be `91234567` and `johndoe@example.com` respectively.
*  `edit 2 n/Betsy Crower t/` Edits the name of the 2nd person to be `Betsy Crower` and clears all existing tags.

### Locating persons by name: `find`

Finds persons whose names contain any of the given keywords.

Format: `find KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only the name is searched.
* Only full words will be matched e.g. `Han` will not match `Hans`
* Persons matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

Examples:
* `find John` returns `john` and `John Doe`
* `find alex david` returns `Alex Yeoh`, `David Li`<br>
  ![result for 'find alex david'](images/findAlexDavidResult.png)

### Deleting a person: `delete`

Deletes the specified person(s) from the address book.

**Format:** `delete INDEX…​`

* Deletes the person(s) at the specified `INDEX` values.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** (e.g., 1, 2, 3, …).
* Supports deleting multiple people at once (e.g., `delete 1 2 4`).

**Examples:**
* `list` followed by `delete 2` deletes the 2nd person in the address book.
* `find Betsy` followed by `delete 1 3` deletes the 1st and 3rd persons in the results of the `find` command.

### Clearing all entries : `clear`

Clears all entries from the address book.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

AddressBook data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

AddressBook data are saved automatically as a JSON file `[JAR file location]/data/addressbook.json`. Advanced users are welcome to update data directly by editing that data file.

<box type="warning" seamless>

**Caution:**
If your changes to the data file makes its format invalid, AddressBook will discard all data and start with an empty data file at the next run.  Hence, it is recommended to take a backup of the file before editing it.<br>
Furthermore, certain edits can cause the AddressBook to behave in unexpected ways (e.g., if a value entered is outside the acceptable range). Therefore, edit the data file only if you are confident that you can update it correctly.
</box>

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous AddressBook home folder.

--------------------------------------------------------------------------------------------------------------------

## Known issues

1. **When using multiple screens**, if you move the application to a secondary screen, and later switch to using only the primary screen, the GUI will open off-screen. The remedy is to delete the `preferences.json` file created by the application before running the application again.
2. **If you minimize the Help Window** and then run the `help` command (or use the `Help` menu, or the keyboard shortcut `F1`) again, the original Help Window will remain minimized, and no new Help Window will appear. The remedy is to manually restore the minimized Help Window.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action     | Format, Examples
-----------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------
**Add**    | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [r/RELATIONSHIP] [nn/NICKNAME] [b/BIRTHDAY] [no/NOTES] [t/TAG]…​` <br> e.g., `add n/Nickie p/88888888 r/son e/nickie@gmail.com a/21 Lower Kent Ridge Rd, Singapore 119077 nn/nickelodeon b/2001-01-01 no/My favorite son`
**Clear**  | `clear`
**Delete** | `delete INDEX…​`<br> e.g., `delete 3`, `delete 1 2 4`
**Edit**   | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [r/RELATIONSHIP] [nn/NICKNAME] [b/BIRTHDAY] [no/NOTES] [t/TAG]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`
**Find**   | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`
**List**   | `list`
**Help**   | `help`
