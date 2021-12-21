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
     * @param user user
     * @param city city
     * @return String
     * @see #initBinderForParamPrefix(WebDataBinder)
     */
    @GetMapping("/param-prefix")
    public String paramPrefix(User user, City city) {

        log.info("user: {}, city: {}",user,city);
        return toString(user, city);
    }



//    @GetMapping("/form-mdoel")
//    public String formModel() {
//
//        return null;
//    }


    /**
     * 设置寻找的参数的名称前缀
     * @param binder binder
     */
    @InitBinder({"user","city"})
    public void initBinderForParamPrefix(WebDataBinder binder) {

        if (needInitBinder("/bind/param-prefix")) {

            String objectName = binder.getObjectName();
            log.info("initBinderForParamPrefix - objectName: {}",objectName);
            if ("user".equals(objectName)) {
                binder.setFieldDefaultPrefix("user.");
            } else if ("city".equals(objectName)) {
                binder.setFieldDefaultPrefix("city.");
            }
        }
    }

    //--------------------
    // helper methods
    //--------------------

    private boolean needInitBinder(String expectServletPath) {

        String servletPath = request.getServletPath();
        return servletPath.equals(expectServletPath);
    }

    private static String toString(Object... os) {

        return Arrays.toString(os);
    }
}
