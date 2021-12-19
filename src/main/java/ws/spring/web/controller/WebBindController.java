package ws.spring.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ws.spring.web.pojo.dto.City;
import ws.spring.web.pojo.dto.User;

import javax.servlet.http.HttpServletRequest;

/**
 * @author WindShadow
 * @version 2021-12-19.
 */

@Slf4j
@RestController
@RequestMapping("/bind")
public class WebBindController {

    /**
     *
     * @param user
     * @param city
     * @return String
     * @see #initBinderForParamPrefix(WebDataBinder, HttpServletRequest)
     */
    @GetMapping("/param-prefix")
    public String paramPrefix(User user, City city) {

        return null;
    }

//    @GetMapping("/form-mdoel")
//    public String formModel() {
//
//        return null;
//    }


    /**
     * 设置寻找的参数的名称前缀
     * @param binder binder
     * @param request request
     */
    @InitBinder({"user","city"})
    public void initBinderForParamPrefix(WebDataBinder binder, HttpServletRequest request) {

        String expectServletPath = "/bind/param-prefix";
        String servletPath = request.getServletPath();
        if (servletPath.equals(expectServletPath)) {

            String objectName = binder.getObjectName();
            if ("user".equals(objectName)) {
                binder.setFieldMarkerPrefix("user.");
            } else if ("city".equals(objectName)) {
                binder.setFieldMarkerPrefix("city.");
            }

        }
    }
}
