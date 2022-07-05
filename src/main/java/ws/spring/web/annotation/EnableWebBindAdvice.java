package ws.spring.web.annotation;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author WindShadow
 * @version 2022-07-05.
 */

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(WebBindAdviserSelector.class)
public @interface EnableWebBindAdvice {

    boolean useFromModel() default true;
}
