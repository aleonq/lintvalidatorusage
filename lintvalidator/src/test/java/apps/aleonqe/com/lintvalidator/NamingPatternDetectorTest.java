package apps.aleonqe.com.lintvalidator;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class NamingPatternDetectorTest {

    private NamingPatternHandler namingPatternHandler;

    @Before
    public void setup() {
        namingPatternHandler = new NamingPatternHandler(null);
    }

    @Test
    public void testIsDefinedCamelCase() {
        assertFalse(namingPatternHandler.isDefinedCamelCase("AA"));
        assertFalse(namingPatternHandler.isDefinedCamelCase("aa"));
        assertFalse(namingPatternHandler.isDefinedCamelCase("a"));
        assertFalse(namingPatternHandler.isDefinedCamelCase("AaBbCC"));
        assertTrue(namingPatternHandler.isDefinedCamelCase("A"));
        assertTrue(namingPatternHandler.isDefinedCamelCase("Aa"));
        assertTrue(namingPatternHandler.isDefinedCamelCase("AaBb.java"));
    }
}