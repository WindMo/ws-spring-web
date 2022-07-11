package ws.spring.web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ws.spring.web.converter.PersonConverter;

/**
 * @author WindShadow
 * @version 2021-12-10.
 */

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    /**
     * 一般格式化更多用于web环境，此处可以为SpringMVC添加格式化器或转换器
     * @param registry 注册器
     */
    @Override
    public void addFormatters(FormatterRegistry registry) {

        registry.addConverter(new PersonConverter());
    }
}
