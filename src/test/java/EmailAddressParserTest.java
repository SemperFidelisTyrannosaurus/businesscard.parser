import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Unit tests for EmailAddressParser
 */
public class EmailAddressParserTest {
    @Test
    public void getEmailAddress() {
        String expected = "come@mebro.com";
        String lines[] = new String[] {expected, "randocalrissian@place.net"};
        doTest(expected, lines);
    }

    @Test
    public void getIncompleteEmailAddress() {
        String expected = "come@mebro.com";
        String lines[] = new String[] {"randocalrissian@", expected};
        doTest(expected, lines);
    }

    @Test
    public void getMalformedEmailAddress() {
        String expected = "\"just  some stuff\"+notspam@gmail.com";
        String lines[] = new String[] {"randocalrissian@", expected};
        doTestFailure(expected, lines);
    }

    private void doTest(String expected, String[] lines) {
        EmailAddressParser emailAddressParser = new EmailAddressParser();
        for (String line: lines) {
            emailAddressParser.consider(line);
        }
        assertEquals(expected, emailAddressParser.getEmailAddress());
    }

    private void doTestFailure(String expected, String[] lines) {
        EmailAddressParser emailAddressParser = new EmailAddressParser();
        for (String line: lines) {
            emailAddressParser.consider(line);
        }
        assertNotEquals(expected, emailAddressParser.getEmailAddress());
    }
}