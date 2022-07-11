package ws.spring.web.converter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ws.spring.web.pojo.Person;

/**
 * "Tom-18" -> Person("Tom", 18)
 *
 * @author WindShadow
 * @version 2021-2-28.
 */

@Slf4j
@Component
public class PersonConverter implements Converter<String, Person> {

    /**
     * @param source 这里进来的字符串不会是空的
     * @return Person
     */
    @Override
    public Person convert(String source) {
        String[] params = source.split("-");
        if (params.length == 2) {

            String name = params[0];
            Integer age = Integer.valueOf(params[1]);
            return new Person(name,age);
        }else {
            throw new IllegalArgumentException("Unexpected format, example: \"Tom-18\" -> Person(\"Tom\", 18)");
        }
    }
}
