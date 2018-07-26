package apps.aleonqe.com.lintvalidator;

import com.android.tools.lint.client.api.IssueRegistry;
import com.android.tools.lint.detector.api.Issue;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

import static apps.aleonqe.com.lintvalidator.compliance.Constants.ISSUE_FORBIDDEN_METHOD_ACCESS;
import static apps.aleonqe.com.lintvalidator.correctness.Constants.ISSUE_NAMING_PATTERN;

public class MyIssueRegistry extends IssueRegistry {


    @Override
    public int getApi() {
        return com.android.tools.lint.detector.api.ApiKt.CURRENT_API;
    }

    @NotNull
    @Override
    public List<Issue> getIssues() {
        return Arrays.asList(ISSUE_FORBIDDEN_METHOD_ACCESS, ISSUE_NAMING_PATTERN);
    }
}
