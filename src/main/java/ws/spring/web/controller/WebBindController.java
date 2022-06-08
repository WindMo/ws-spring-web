package ws.spring.web.controller;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ws.spring.web.pojo.City;
import ws.spring.web.pojo.User;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Objects;
import java.util.function.Consumer;

/**
 * SpringWeb参数绑定示例
 * @author WindShadow
 * @version 2021-12-19.
 * @see WebBindControllerAdvice#initBinder(WebDataBinder)
 */

@Slf4j
@RestController
@RequestMapping("/bind")
public class WebBindController {

    @Autowired
    @Setter
    private HttpServletRequest request;

    /**
     * 根据参数的名称前缀绑定
     *  <pre>/bind/param-prefix?user.name=tom&user.desc=tom cat&user.email=123@qq.com&city.name=北京&city.desc=中国首都</pre>
     * @param user user
     * @param city city
     * @return String
     * @see #setFieldDefaultPrefix(WebDataBinder)
     */
    @GetMapping("/param-prefix")
    public String paramPrefix(User user, City city) {

        log.info("user: {}, city: {}",user,city);
        return toString(user, city);
    }

    /**
     * 设置寻找的参数的名称前缀
     * @param binder binder
     */
    @InitBinder({"user","city"})
    public void setFieldDefaultPrefix(WebDataBinder binder) {

        needInitBinder("/bind/param-prefix",() -> {
            String objectName = binder.getObjectName();
            log.info("initBinderForParamPrefix - objectName: {}",objectName);
            if ("user".equals(objectName)) {
                binder.setFieldDefaultPrefix("user.");
            } else if ("city".equals(objectName)) {
                binder.setFieldDefaultPrefix("city.");
            }
        });
    }

    @GetMapping("/allowed-fields")
    public String allowedFields(User user, City city) {

        log.info("user: {}, city: {}",user,city);
        return toString(user, city);
    }

    @InitBinder({"user","city"})
    public void setAllowedFields(WebDataBinder binder) {

        needInitBinder("/allowed-fields",() -> {
            String objectName = binder.getObjectName();
            log.info("initBinderForParamPrefix - objectName: {}",objectName);
            binder.setAllowedFields();
        });
    }


//    @GetMapping("/form-mdoel")
//    public String formModel() {
//
//        return null;
//    }




    //--------------------
    // helper methods
    //--------------------

    private void needInitBinder(String expectServletPath, Runnable init) {

        String servletPath = request.getServletPath();
        if (servletPath.equals(expectServletPath)) {
            init.run();
        }
    }

    private static String toString(Object... os) {

        return Arrays.toString(os);
    }
}
