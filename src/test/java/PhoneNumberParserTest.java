import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Unit tests for PhoneNumberParser
 */
public class PhoneNumberParserTest {
    @Test
    public void getPhoneNumber() {
        String expected = "4105551234";
        String lines[] = new String[] {"A Guy", "F: 5553334444", "P: " + expected};
        doTest(expected, lines);
    }

    public void getPhoneNumber11Digits() {
        String expected = "18003334444";
        String lines[] = new String[] {"A Guy", "F: 15553334444", "P: " + expected};
        doTest(expected, lines);
    }

    private void doTest(String expected, String[] lines) {
        PhoneNumberParser phoneNumberParser = new PhoneNumberParser();
        for (String line: lines) {
            phoneNumberParser.consider(line);
        }
        assertEquals(expected, phoneNumberParser.getPhoneNumber());
    }
}