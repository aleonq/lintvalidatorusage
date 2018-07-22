package apps.aleonqe.com.lintvalidator;

import com.android.tools.lint.client.api.UElementHandler;
import com.android.tools.lint.detector.api.Detector;
import com.android.tools.lint.detector.api.JavaContext;

import org.jetbrains.annotations.Nullable;
import org.jetbrains.uast.UClass;
import org.jetbrains.uast.UElement;

import java.util.ArrayList;
import java.util.List;

public class NamingPatternDetector extends Detector implements Detector.UastScanner {
    @Nullable
    @Override
    public List<Class<? extends UElement>> getApplicableUastTypes() {
        List<Class<? extends UElement>> types = new ArrayList<>(1);
        types.add(UClass.class);
        return types;
    }

    private boolean isDefinedCamelCase(String name) {
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

    @Nullable
    @Override
    public UElementHandler createUastHandler(JavaContext context) {
        return new NamingPatternHandler(context);
    }

    class NamingPatternHandler extends UElementHandler {
        JavaContext context;

        NamingPatternHandler(JavaContext context) {
            this.context = context;
        }

        @Nullable
        @Override
        public void visitClass(UClass clazz) {
            try {
                if (!isDefinedCamelCase(clazz.getName())) {
                    context.report(MyIssueRegistry.ISSUE_NAMING_PATTERN, clazz,
                            context.getNameLocation(clazz),
                            "Not named in defined camel case.");
                }
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        }
    }
}
