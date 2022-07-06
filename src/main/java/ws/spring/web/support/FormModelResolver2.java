package ws.spring.web.support;

import org.springframework.beans.MutablePropertyValues;
import org.springframework.core.MethodParameter;
import org.springframework.util.Assert;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.annotation.AbstractNamedValueMethodArgumentResolver;
import ws.spring.web.annotation.FormModel;

import java.util.Map;


/**
 * @author WindShadow
 * @version 2021-3-20 0020.
 */

public class FormModelResolver2 extends AbstractNamedValueMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(FormModel.class);
    }

    @Override
    protected NamedValueInfo createNamedValueInfo(MethodParameter parameter) {

        FormModel formModel = parameter.getParameterAnnotation(FormModel.class);
        Assert.state(formModel != null,"No FormModel annotation");
        // 获取注解中的指定参数名称的前缀
        String annotationParamName = formModel.prefix();
        // 获取控制层方法参数的名称，即源码中函数形参参数名
        String paramName = parameter.getParameterName();
        // 决定属性名称前缀，属性名称前缀取决于注解内名称，若未指定则默认为函数形参参数名
        String prefix = "".equals(annotationParamName) ? paramName : annotationParamName;
        // 获取分隔符
        String separator = formModel.separator();
        // 最终的属性前缀
        String fieldPrefix = prefix + separator;

        boolean required = formModel.required();
        return new NamedValueInfo(fieldPrefix,required,null);
    }

    @Override
    protected Object resolveName(String name, MethodParameter parameter, NativeWebRequest request) throws Exception {

        final String fieldPrefix = name;
        // 获取控制层方法参数的class
        Class<?> paramClass = parameter.getNestedParameterType();
        // 创建web数据绑定器及参数表
        Object webData = paramClass.newInstance();
//        WebDataBinder binder2 = webDataBinderFactory.createBinder(nativeWebRequest,webData,paramName);
        WebDataBinder binder = new WebDataBinder(webData);
        MutablePropertyValues pvs = new MutablePropertyValues();

        /*
        Iterator<String> parameterNames = nativeWebRequest.getParameterNames();
        while (parameterNames.hasNext()) {

            String parameterName = parameterNames.next();
            if (parameterName.indexOf(fieldPrefix) == 0) {
                pvs.add(parameterName,nativeWebRequest.getParameterValues(parameterName));
            }
        }
        */

        // 遍历请求对象获取符合当前class的参数
        // 获取表单提交的参数并遍历
        Map<String,String[]> allParamsMap = request.getParameterMap();
        allParamsMap.forEach((k,v) -> {
//            log.info("K: {} V: {}",k, Arrays.toString(v));
            // 前缀匹配成功则添加到属性表
            if (k.startsWith(fieldPrefix)) {
                // 属性名为前缀以后的字符串
                String fieldName = k.substring(fieldPrefix.length());
                pvs.add(fieldName,v);
            }
        });
        // 虽然填进去的是字符串数组，但是WebDataBinder依旧可以绑定到单一属性对应类型的值
        // 大致如下
        // bean属性   字符串数组处理结果
        // >字符串   数组内的字符串相加
        // >数值类型   数组长度为1则进行转换，否则为默认值
        // >其它      未知
        binder.bind(pvs);
        return webData;
    }
}
