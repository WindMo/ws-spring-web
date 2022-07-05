package ws.spring.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.core.convert.ConversionService;
import org.springframework.format.Formatter;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ws.spring.web.annotation.FormModel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 控制层参数绑定增强写法
 *
 * @author WindShadow
 * @version 2021-12-19.
 */

@Slf4j
@RestControllerAdvice(assignableTypes = {WebBindController.class})
public class WebBindControllerAdvice {

    //---------------------------------
    // 初始化参数绑定器 WebDataBinder
    //---------------------------------

    /**
     * 初始化参数绑定器，在请求到达真正的处理器之前调用
     * <p>一般传参为{@link WebDataBinder}，可以设置的入参类型和普通MVC处理器（Controller的方法）差不多
     * <p>{@link InitBinder#value()}代表当参数名称为指定值时，才执行此初始化参数绑定器的方法
     * <p> 见名知意的方法：
     * <p>{@link WebDataBinder#setValidator(Validator)} 为当前参数设置Spring的校验器
     * <p>{@link WebDataBinder#addValidators(Validator...)} 为当前参数添加一个Spring的校验器
     * <p>{@link WebDataBinder#addCustomFormatter(Formatter)} 为当前参数设置一个格式化器
     * <p>{@link WebDataBinder#setConversionService(ConversionService)} 为当前参数设置一个数据转换服务
     * @param binder 参数绑定器
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {

        log.info("initBinder - params: binder");
    }

    /**
     * 既然入参类型和普通MVC处理器（Controller的方法）差不多，自然也支持无参，看developer怎么玩了
     */
    @InitBinder
    public void initBinder() {

        log.info("initBinder - no param");
    }

    @InitBinder
    public void initBinder(WebDataBinder binder, HttpServletRequest request, HttpServletResponse response) {

        log.info("initBinder - params: binder,request,response");
    }

    // ~ 扩展
    // =====================================================================================

    /**
     *
     * @param binder
     * @see FormModel
     */
    @InitBinder
    public void initBinderForFormModel(WebDataBinder binder) {

        log.info("initBinder - params: initBinderForFormModel");
//        binder.get
    }
}
