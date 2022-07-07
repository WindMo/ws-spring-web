package ws.spring.web.support;

import java.lang.annotation.*;

/**
 * @author WindShadow
 * @version 2021-04-10.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ParamName {

    /**
     * The name of the request parameter to bind to.
     */
    String value();
}
