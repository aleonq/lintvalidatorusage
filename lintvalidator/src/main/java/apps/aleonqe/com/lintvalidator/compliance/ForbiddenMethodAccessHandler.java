package apps.aleonqe.com.lintvalidator.compliance;

import com.android.tools.lint.client.api.UElementHandler;
import com.android.tools.lint.detector.api.JavaContext;

import org.jetbrains.uast.UClass;
import org.jetbrains.uast.UExpression;
import org.jetbrains.uast.UFile;
import org.jetbrains.uast.UMethod;
import org.jetbrains.uast.java.JavaUCodeBlockExpression;
import org.jetbrains.uast.java.JavaUCompositeQualifiedExpression;

import java.util.Collections;
import java.util.List;

import static apps.aleonqe.com.lintvalidator.compliance.Constants.BRIEF_DESCRIPTION;
import static apps.aleonqe.com.lintvalidator.compliance.Constants.ISSUE_FORBIDDEN_METHOD_ACCESS;

class ForbiddenMethodAccessHandler extends UElementHandler {
    private static final List<String> FORBIDDEN_CLASS = Collections.singletonList("Log");
    private JavaContext context;

    ForbiddenMethodAccessHandler(JavaContext context) {
        this.context = context;
    }

    @Override
    public void visitClass(UClass clazz) {
        if (clazz == null || clazz.getName() == null) {
            return;
        }
        UFile uFile = context.getUastFile();
        if (uFile != null) {
            List<UClass> uClasses = uFile.getClasses();
            for (UClass uClass : uClasses) {
                UMethod[] uMethods = uClass.getMethods();
                for (UMethod uMethod : uMethods) {
                    UExpression uExpression = uMethod.getUastBody();
                    if (uExpression instanceof JavaUCodeBlockExpression) {
                        JavaUCodeBlockExpression javaUCodeBlockExpression = (JavaUCodeBlockExpression) uExpression;
                        List<UExpression> uExpressions = javaUCodeBlockExpression.getExpressions();
                        for (UExpression ue : uExpressions) {
                            JavaUCompositeQualifiedExpression javaUCompositeQualifiedExpression = (JavaUCompositeQualifiedExpression) ue;
                            if (FORBIDDEN_CLASS.contains(javaUCompositeQualifiedExpression.getReceiver().toString())) {
                                context.report(ISSUE_FORBIDDEN_METHOD_ACCESS, clazz,
                                        context.getLocation(javaUCompositeQualifiedExpression),
                                        BRIEF_DESCRIPTION);
                            }
                        }
                    }
                }
            }
        }
    }
}