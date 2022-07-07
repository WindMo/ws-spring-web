package ws.spring.web.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.Size;

/**
 * @author WindShadow
 * @version 2021-2-28 0028.
 */

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class Person {

    private String name;
    @Size(min = 1,max = 99)
    private Integer age;
}
