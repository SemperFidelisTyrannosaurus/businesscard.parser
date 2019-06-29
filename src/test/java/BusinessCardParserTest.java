import com.google.common.base.Charsets;
import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

/**
 * Unit tests for BusinessCardParser
 */
public class BusinessCardParserTest {

    @org.junit.Test
    public void testMainShowHelp() throws Exception {
        BusinessCardParser.main("-h");
    }

    @org.junit.Test
    public void getContactInfoMikeSmith() throws Exception {
        doTest("MikeSmithTest", "Mike Smith", "4105551234", "msmith@asymmetrik.com");
    }

    @org.junit.Test
    public void getContactInfoLisaHuang() throws Exception {
        doTest("LisaHuangTest", "Lisa Huang", "4105551234", "lisa.haung@foobartech.com");
    }

    @org.junit.Test
    public void getContactInfoArthurWilson() throws Exception {
        doTest("ArthurWilsonTest", "Arthur Wilson", "17035551259",  "awilson@abctech.com");
    }

    private void doTest(String resource, String name, String phoneNumber, String emailAddress) throws IOException {
        ContactInfo expected = new ContactInfo(name, phoneNumber, emailAddress);

        String document = Files.toString(new File("src/test/resources/"+resource), Charsets.UTF_8);
        assertEquals(expected, BusinessCardParser.getContactInfo(document));
    }

}