import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Unit tests for NameParser
 */
public class NameParserTest {
    @Test
    public void getName() {
        String expected = "John Leguzamo";
        String lines[] = new String[] {expected, "Zombie Guy"};
        doTest(expected, lines);
    }

    @Test
    public void getNameWhereNERPartialMatch() {
        String expected = "Rob Shnyder";
        String lines[] = new String[] {"A Carrot", expected};
        doTest(expected, lines);
    }

    private void doTest(String expected, String[] lines) {
        ProperNameParser nameParser = new ProperNameParser();
        for (String line: lines) {
            nameParser.consider(line);
        }
        assertEquals(expected, nameParser.getName());
    }
}