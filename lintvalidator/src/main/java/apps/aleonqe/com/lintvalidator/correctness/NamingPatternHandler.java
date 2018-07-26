package apps.aleonqe.com.lintvalidator.correctness;

import com.android.tools.lint.client.api.UElementHandler;
import com.android.tools.lint.detector.api.JavaContext;

import org.jetbrains.uast.UClass;

import static apps.aleonqe.com.lintvalidator.correctness.Constants.BRIEF_DESCRIPTION;
import static apps.aleonqe.com.lintvalidator.correctness.Constants.ISSUE_NAMING_PATTERN;

public class NamingPatternHandler extends UElementHandler {
    private JavaContext context;

    public NamingPatternHandler(JavaContext context) {
        this.context = context;
    }

    @Override
    public void visitClass(UClass clazz) {
        if (clazz == null || clazz.getName() == null) {
            return;
        }
        if (!isDefinedCamelCase(clazz.getName())) {
            context.report(ISSUE_NAMING_PATTERN, clazz,
                    context.getNameLocation(clazz),
                    BRIEF_DESCRIPTION);
        }
    }

    public boolean isDefinedCamelCase(String name) {
        char[] charArray = name.toCharArray();
        if (Character.isLowerCase(charArray[0])) {
            return false;
        }
        for (int i = 0; i < charArray.length - 1; i++) {
            if ((Character.isUpperCase(charArray[i]) && Character.isUpperCase(charArray[i + 1]))) {
                return false;
            }
        }
        return true;
    }
}