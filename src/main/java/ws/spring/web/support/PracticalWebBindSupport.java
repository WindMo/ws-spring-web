package ws.spring.web.support;

import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @author WindShadow
 * @version 2022-07-05.
 */

public class PracticalWebBindSupport implements WebMvcConfigurer {

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {

        resolvers.add(new FormModelResolverDev());
//        resolvers.add(new FormModelResolver2());
    }
}
