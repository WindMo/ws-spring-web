package ws.spring.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ws.spring.web.annotation.FormModel;
import ws.spring.web.pojo.City;
import ws.spring.web.pojo.User;
import ws.spring.web.util.ObjectUtils;

/**
 * @author WindShadow
 * @version 2022-06-25.
 */

@Slf4j
@RestController
@RequestMapping("/support")
public class WebBindSupportController {

    /**
     * 通过FormModel指定参数前缀，据参数的名称前缀绑定
     * <pre>/bind/param-prefix?user.name=tom&user.desc=tom cat&user.email=123@qq.com&city.name=北京&city.desc=中国首都</pre>
     * @param user user
     * @param city city
     * @return String
     */
    @GetMapping("/from-model/default")
    public String formModelDefaultPrefix(@FormModel User user, @FormModel City city) {

        log.info("user: {}, city: {}",user,city);
        return ObjectUtils.toString(user, city);
    }

    /**
     * 通过FormModel指定参数前缀，据参数的名称前缀绑定
     * <pre>/bind/param-prefix?user.name=tom&user.desc=tom cat&user.email=123@qq.com&city.name=北京&city.desc=中国首都</pre>
     * @param u user
     * @param c city
     * @return String
     */
    @GetMapping("/from-model")
    public String formModel(@FormModel("user") User u, @FormModel("city") City c) {

        log.info("user: {}, city: {}",u,c);
        return ObjectUtils.toString(u, c);
    }
}