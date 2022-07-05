package ws.spring.web.annotation;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * @author WindShadow
 * @version 2022-06-25.
 */

@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FormModel {

    @AliasFor("prefix")
    String value() default "";

    @AliasFor("value")
    String prefix() default "";

    String separator() default ".";

    boolean required() default false;
}
