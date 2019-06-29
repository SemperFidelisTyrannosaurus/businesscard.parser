import java.util.regex.Pattern;

public class EmailAddressParser {
    /**
     * RegEx from http://emailregex.com/ - Since email addresses must have a certain format to be valid, this regex will cover
     * the vast,VAST majority of all addresses used.  Further analysis is only possible by attempting to email the specified
     * address, which is beyond the scope of this project
     */
    private static final Pattern EMAIL_ADDDRESS_PATTERN = Pattern.compile("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])");

    private String emailAddress = "";

    /**
     * Checks a given line to see if it matches the structure of an email address
     * Quits when it finds the first match, so the first email address found will
     * be the one returned
     * @param line value to search for an email address
     */
    public void consider(String line) {
        if (this.emailAddress.length() > 0)
            return;
        if (EMAIL_ADDDRESS_PATTERN.matcher(line).matches()) {
            this.emailAddress = line;
        }
    }

    public String getEmailAddress() {

        return this.emailAddress;
    }
}
