package apps.aleonqe.com.lintvalidator;

import com.android.tools.lint.client.api.IssueRegistry;
import com.android.tools.lint.detector.api.Category;
import com.android.tools.lint.detector.api.Implementation;
import com.android.tools.lint.detector.api.Issue;
import com.android.tools.lint.detector.api.Scope;
import com.android.tools.lint.detector.api.Severity;

import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.EnumSet;
import java.util.List;

public class MyIssueRegistry extends IssueRegistry {
    static final Issue ISSUE_NAMING_PATTERN = Issue.create("NamingPattern",
            "Names should be well named.",
            "Some long description about this issue",
            Category.CORRECTNESS,
            5,
            Severity.WARNING,
            new Implementation(NamingPatternDetector.class,
                    EnumSet.of(Scope.JAVA_FILE, Scope.TEST_SOURCES))
    );

    @Override
    public int getApi() {
        return com.android.tools.lint.detector.api.ApiKt.CURRENT_API;
    }

    @NotNull
    @Override
    public List<Issue> getIssues() {
        return Collections.singletonList(ISSUE_NAMING_PATTERN);
    }
}
