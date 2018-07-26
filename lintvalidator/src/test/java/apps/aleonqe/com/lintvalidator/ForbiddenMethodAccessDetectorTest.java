package apps.aleonqe.com.lintvalidator;

import org.junit.Test;

import static apps.aleonqe.com.lintvalidator.compliance.Constants.ISSUE_FORBIDDEN_METHOD_ACCESS;
import static com.android.tools.lint.checks.infrastructure.LintDetectorTest.java;
import static com.android.tools.lint.checks.infrastructure.TestLintTask.lint;

public class ForbiddenMethodAccessDetectorTest {

    @Test
    public void testForbiddenMethodAccess_notAccessed() {
        lint()
                .files(java("" +
                        "package apps.aleonqe.com.lintvalidarotsample;\n" +
                        "\n" +
                        "import android.app.Activity;\n" +
                        "import android.os.Bundle;\n" +
                        "\n" +
                        "public class MainActivity extends Activity {\n" +
                        "\n" +
                        "    @Override\n" +
                        "    protected void onCreate(Bundle savedInstanceState) {\n" +
                        "        super.onCreate(savedInstanceState);\n" +
                        "    }\n" +
                        "}\n"))
                .issues(ISSUE_FORBIDDEN_METHOD_ACCESS)
                .run()
                .expect("No warnings.");
    }

    @Test
    public void testForbiddenMethodAccess_accessedOnce() {
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
                        "        Log.d(\"MainActivity\", \"message\");\n" +
                        "    }\n" +
                        "}\n"))
                .issues(ISSUE_FORBIDDEN_METHOD_ACCESS)
                .run()
                .expect("src/apps/aleonqe/com/lintvalidarotsample/MainActivity.java:12: Warning: This method call is forbidden. [ForbiddenMethodCall]\n" +
                        "        Log.d(\"MainActivity\", \"message\");\n" +
                        "        ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n" +
                        "0 errors, 1 warnings");
    }
}