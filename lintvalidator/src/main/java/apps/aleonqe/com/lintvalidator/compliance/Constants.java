package apps.aleonqe.com.lintvalidator.compliance;

import com.android.tools.lint.detector.api.Category;
import com.android.tools.lint.detector.api.Implementation;
import com.android.tools.lint.detector.api.Issue;
import com.android.tools.lint.detector.api.Scope;
import com.android.tools.lint.detector.api.Severity;

import java.util.EnumSet;

public class Constants {

    static final String BRIEF_DESCRIPTION = "This method call is forbidden.";
    private static final String ISSUE_ID = "ForbiddenMethodCall";
    private static final String EXPLANATION = "Do not call this method. Instead use the method in the library.";

    public static final Issue ISSUE_FORBIDDEN_METHOD_ACCESS = Issue.create(ISSUE_ID,
            BRIEF_DESCRIPTION,
            EXPLANATION,
            Category.COMPLIANCE,
            8,
            Severity.WARNING,
            new Implementation(ForbiddenMethodAccessDetector.class,
                    EnumSet.of(Scope.JAVA_FILE))
    );
}
