package ws.spring.web.pojo;

import lombok.*;

import javax.validation.constraints.Email;

/**
 * @author WindShadow
 * @date 2020/9/20.
 */

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode
public class User {

    private String name;
    private String desc;

    @Email
    private String email;
}
