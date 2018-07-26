package apps.aleonqe.com.lintvalidator;

import org.junit.Test;

import apps.aleonqe.com.lintvalidator.correctness.NamingPatternHandler;

import static apps.aleonqe.com.lintvalidator.correctness.Constants.ISSUE_NAMING_PATTERN;
import static com.android.tools.lint.checks.infrastructure.LintDetectorTest.java;
import static com.android.tools.lint.checks.infrastructure.TestLintTask.lint;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class NamingPatternDetectorTest {

    @Test
    public void testIsDefinedCamelCase() {
        NamingPatternHandler namingPatternHandler = new NamingPatternHandler(null);
        assertFalse(namingPatternHandler.isDefinedCamelCase("AA"));
        assertFalse(namingPatternHandler.isDefinedCamelCase("aa"));
        assertFalse(namingPatternHandler.isDefinedCamelCase("a"));
        assertFalse(namingPatternHandler.isDefinedCamelCase("AaBbCC"));
        assertTrue(namingPatternHandler.isDefinedCamelCase("A"));
        assertTrue(namingPatternHandler.isDefinedCamelCase("Aa"));
        assertTrue(namingPatternHandler.isDefinedCamelCase("AaBb.java"));
    }

    @Test
    public void asdasd() {
        lint()
                .files(java("" +
                        "package apps.aleonqe.com.lintvalidarotsample;\n" +
                        "\n" +
                        "import android.app.Activity;\n" +
                        "import android.os.Bundle;\n" +
                        "import android.util.Log;\n" +
                        "\n" +
                        "public class MainActivity extends Activity {\n" +
                        "\n" +
                        "    @Override\n" +
                        "    protected void onCreate(Bundle savedInstanceState) {\n" +
                        "        super.onCreate(savedInstanceState);\n" +
                        "       // Log.d(\"here you go\", \"message\");\n" +
                        "    }\n" +
                        "}\n"))
                .issues(ISSUE_NAMING_PATTERN)
                .run()
                .expect("No warnings.");
    }
}