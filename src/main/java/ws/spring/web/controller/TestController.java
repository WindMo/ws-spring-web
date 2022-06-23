package ws.spring.web.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ws.spring.web.pojo.City;

import java.util.Arrays;
import java.util.List;

/**
 * @author WindShadow
 * @version 2022-06-13.
 */

@Slf4j
@RestController
@RequestMapping("/test")
public class TestController {

    /**
     * 多个相同的query参数
     * /test/query?name=c1&desc=de1&name=c2&desc=de2
     *
     * @return
     */
    @GetMapping("/query")
    public String multiQueryParams(@RequestParam("name") List<String> name, @RequestParam("desc") List<String> desc) {

        // List<City> -> 500 GG
        // City[] -> 500 GG

        log.info("names:{}, descs: {}", name, desc);
        return "OK";
    }

    /**
     * 多个相同的query参数
     * /test/query?name=c1&desc=de1&name=c2&desc=de2
     *
     * @return
     */
    @GetMapping("/query-wrapper")
    public String multiQueryParamsWRapper(@RequestParam("name") List<ListWrapper> wrappers) {

        log.info("wrappers:{}", wrappers);
        return "OK";
    }

    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    static class ListWrapper {

        List<String> name;
        List<String> desc;
    }
}
