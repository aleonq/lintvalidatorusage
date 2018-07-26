package apps.aleonqe.com.lintvalidator.correctness;

import com.android.tools.lint.detector.api.Category;
import com.android.tools.lint.detector.api.Implementation;
import com.android.tools.lint.detector.api.Issue;
import com.android.tools.lint.detector.api.Scope;
import com.android.tools.lint.detector.api.Severity;

import java.util.EnumSet;

public class Constants {

    static final String BRIEF_DESCRIPTION = "Not named according to agreed upon convention.";
    private static final String ISSUE_ID = "NamingPattern";
    private static final String EXPLANATION = "Camel case notations should be followed in Java and Kotlin files.";

    public static final Issue ISSUE_NAMING_PATTERN = Issue.create(ISSUE_ID,
            BRIEF_DESCRIPTION,
            EXPLANATION,
            Category.CORRECTNESS,
            5,
            Severity.WARNING,
            new Implementation(NamingPatternDetector.class,
                    EnumSet.of(Scope.JAVA_FILE/*, Scope.TEST_SOURCES*/))
    );
}
