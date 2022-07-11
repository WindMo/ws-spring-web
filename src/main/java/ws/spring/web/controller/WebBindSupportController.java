package ws.spring.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ws.spring.web.annotation.FormModel;
import ws.spring.web.pojo.City;
import ws.spring.web.pojo.Person;
import ws.spring.web.pojo.User;
import ws.spring.web.util.ObjectUtils;

import java.util.List;
import java.util.Map;

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

    /**
     * 通过FormModel指定参数前缀，据参数的名称前缀绑定，且进行验证
     * <pre>/bind/param-prefix?user.name=tom&user.desc=tom cat&user.email=123qq.com&city.name=北京&city.desc=中国首都</pre>
     * @param u user
     * @param c city
     * @return String
     */
    @GetMapping("/from-model/validate")
    public String formModelValidate(@FormModel(value = "user", validate = true) User u, @FormModel("city") City c) {

        log.info("user: {}, city: {}",u,c);
        return ObjectUtils.toString(u, c);
    }

    @GetMapping("/from-model/string")
    public String formModelOfString(@FormModel("user") String str) {

        log.info("str: {}",str);
        return ObjectUtils.toString(str);
    }

    // ~ Map 系列
    // 虽然不报错，但是实参的map的value类型不是正确的类型，而是String，get时会发生类型转换错误，运行时无法获取泛型信息，
    // key类型也同理，并不能限制为String
    // =====================================================================================

    @GetMapping("/from-model/map/string")
    public String formModelOfMapString(@FormModel("user") Map<String, String> map) {

        log.info("map: {}",map);
        return ObjectUtils.toString(map);
    }

    @GetMapping("/from-model/map/integer")
    public String formModelOfMapInteger(@FormModel("user") Map<String, Integer> map) {

        log.info("map: {}",map);
        return ObjectUtils.toString(map);
    }

    @GetMapping("/from-model/map/object")
    public String formModelOfMapObject(@FormModel("user") Map<String, Object> map) {

        log.info("map: {}",map);
        return ObjectUtils.toString(map);
    }

    @GetMapping("/from-model/map/pojo")
    public String formModelOfMapPojo(@FormModel("user") Map<String, Person> map) {

        log.info("map: {}",map);
        return ObjectUtils.toString(map);
    }

    @GetMapping("/from-model/map/string/unknow")
    public String formModelOfMapStringUnkonw(@FormModel("user") Map<String, ?> map) {

        log.info("map: {}",map);
        return ObjectUtils.toString(map);
    }

    @GetMapping("/from-model/map/unknow/unknow")
    public String formModelOfMapUnkonwUnkonw(@FormModel("user") Map<?, ?> map) {

        log.info("map: {}",map);
        return ObjectUtils.toString(map);
    }

    // ~ 集合系列
    // =====================================================================================

    @GetMapping("/from-model/list/string")
    public String formModelOfListString(@FormModel("user") List<String> list) {

        log.info("list: {}",list);
        return ObjectUtils.toString(list);
    }
}
