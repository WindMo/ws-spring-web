package ws.spring.web.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import ws.spring.web.support.ParamName;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Data
public class QueryDto {

    private Integer id;
    @ParamName("sort_name")
    private String sortName = "defaultSortName";
    private String sortSize;
    @ParamName("person_person")
    private Person person;
}