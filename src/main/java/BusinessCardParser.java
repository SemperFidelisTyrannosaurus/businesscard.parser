import com.google.common.base.Charsets;
import com.google.common.io.Files;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;

import java.io.File;

public class BusinessCardParser {

    private static Options options = new Options();
    static {
        options.addOption("f", "file", true, "Business Card File:");
        options.addOption("h", "help", false, "Show help");
    }

    public static void main(String... args) throws Exception {

        CommandLineParser parser = new DefaultParser();
        CommandLine cmdline = parser.parse(options, args);


        if (cmdline.hasOption("f")) {
            String document = Files.toString(new File(cmdline.getOptionValue("f")), Charsets.UTF_8);
            System.out.println(BusinessCardParser.getContactInfo(document));
        } else {
            showHelp();
        }
    }

    /**
     * Parses the given string into a Contact Info object. Expects one piece on info per line i.e.:
     *   Arthur Wilson
     *   Software Engineer
     *   Decision & Security Technologies
     *   ABC Technologies
     *   123 North 11th Street
     *   Suite 229
     *   Arlington, VA 22209
     *   Tel: +1 (703) 555-1259
     *   Fax: +1 (703) 555-1200
     *   awilson@abctech.com
     *
     * @param document data String from OCR's business card to parse.  Assumes each line of data is separated by newline
     * @return ContactInformation object from afformentioned data
     */
    public static ContactInfo getContactInfo(String document) {

        //ensure there is an even split between lines
        String[] lines = document.split("\\n");

        // initialize the parser classes
        // parsers were split into different classes for future extensibility
        ProperNameParser nameParser = new ProperNameParser();
        PhoneNumberParser phoneNumberParser = new PhoneNumberParser();
        EmailAddressParser emailAddressParser = new EmailAddressParser();

        //go through the file line by line and see if they match a given format
        for (String line: lines) {
            nameParser.consider(line);
            phoneNumberParser.consider(line);
            emailAddressParser.consider(line);
        }

        return new ContactInfo(nameParser.getName(), phoneNumberParser.getPhoneNumber(), emailAddressParser.getEmailAddress());
    }

    private static void showHelp() {
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp( "businesscard.parser", options );
    }

}
