package ws.spring.web.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author WindShadow
 * @date 2020/9/20.
 */

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {

    private String name;
    private String desc;
    private String email;
}
