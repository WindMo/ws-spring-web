package ws.spring.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ws.spring.web.pojo.QueryDto;

/**
 * @author WindShadow
 * @version 2021-04-10.
 */
@Slf4j
@RestController
@RequestMapping("/resolvers")
public class ParamResolversController {


    @GetMapping("/snake/{id}")
    public String snake(QueryDto queryDto) {

        log.info("resolversTest: {}", queryDto);
        return queryDto == null ? "null" : queryDto.toString();
    }

}
