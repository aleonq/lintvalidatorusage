package apps.aleonqe.com.lintvalidator.compliance;

import com.android.tools.lint.client.api.UElementHandler;
import com.android.tools.lint.detector.api.Detector;
import com.android.tools.lint.detector.api.JavaContext;

import org.jetbrains.annotations.Nullable;
import org.jetbrains.uast.UClass;
import org.jetbrains.uast.UElement;

import java.util.ArrayList;
import java.util.List;

public class ForbiddenMethodAccessDetector extends Detector implements Detector.UastScanner {
    @Nullable
    @Override
    public List<Class<? extends UElement>> getApplicableUastTypes() {
        List<Class<? extends UElement>> types = new ArrayList<>(1);
        types.add(UClass.class);
        return types;
    }

    @Nullable
    @Override
    public UElementHandler createUastHandler(JavaContext context) {
//        System.out.println("createUastHandler():\n" + UastUtils.asRecursiveLogString(context.getUastFile()));
        return new ForbiddenMethodAccessHandler(context);
    }
}