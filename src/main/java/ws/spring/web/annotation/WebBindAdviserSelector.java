package ws.spring.web.annotation;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.Assert;
import ws.spring.web.support.PracticalWebBindSupport;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author WindShadow
 * @version 2022-07-05.
 */

class WebBindAdviserSelector implements ImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {

        Map<String, Object> attributes = importingClassMetadata.getAnnotationAttributes(EnableWebBindAdvice.class.getName());
        Assert.notNull(attributes, "Not found <" + EnableWebBindAdvice.class.getName() + ">");
        List<Class<?>> beanClasses = new ArrayList<>();
        boolean useFromModel = (boolean) attributes.get("useFromModel");
        if (useFromModel) {

            beanClasses.add(PracticalWebBindSupport.class);
        }
        return beanClasses.stream()
                .map(Class::getName)
                .toArray(String[]::new);
    }
}
