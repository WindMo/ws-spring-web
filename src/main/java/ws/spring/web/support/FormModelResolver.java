package ws.spring.web.support;

import org.springframework.beans.MutablePropertyValues;
import org.springframework.core.MethodParameter;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import ws.spring.web.annotation.FormModel;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * {@link FormModel}注解参数解析器
 *
 * @author WindShadow
 * @version 2020/9/19.
 */

public class FormModelResolver implements HandlerMethodArgumentResolver {

    private static final TypeDescriptor STRING_TYPE = TypeDescriptor.valueOf(String.class);

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {

        return methodParameter.hasParameterAnnotation(FormModel.class);
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {

        FormModel formModel = methodParameter.getParameterAnnotation(FormModel.class);
        Assert.state(formModel != null,"No FormModel annotation");
        // 获取控制层方法参数的class
        Class<?> paramClass = methodParameter.getParameterType();
        TypeDescriptor paramTypeDesc = TypeDescriptor.valueOf(paramClass);
        Assert.state(!paramClass.isPrimitive() && !STRING_TYPE.isAssignableTo(paramTypeDesc),
                "FormModel does not support Primitive Type binding");
        // 创建web数据绑定器及参数表
        if (webDataBinderFactory != null) {

            // 获取控制层方法参数的名称，即源码中函数形参参数名
            String paramName = methodParameter.getParameterName();
            // 最终的属性前缀
            String paramPrefix = obtainParamPrefix(formModel, paramName);

            if (paramTypeDesc.isArray()) {

                // TODO
            } else if (paramTypeDesc.isCollection()) {

                // TODO
            } else if (paramTypeDesc.isMap()) {

                TypeDescriptor mapKeyTypeDesc = paramTypeDesc.getMapKeyTypeDescriptor();
                if (mapKeyTypeDesc != null) {
                    Assert.state(STRING_TYPE.isAssignableTo(mapKeyTypeDesc),
                            "The key of the map type modified by FormModel must be a String or its superclass");
                }
                TypeDescriptor mapValueTypeDesc = paramTypeDesc.getMapValueTypeDescriptor();
                Class<?> mapValueClass = mapValueTypeDesc == null ? Object.class : mapValueTypeDesc.getType();
                WebDataBinder binder = webDataBinderFactory.createBinder(nativeWebRequest,null,paramName);
                return this.<Object>filterParameterMap(nativeWebRequest, paramPrefix,
                        value -> binder.convertIfNecessary(value, mapValueClass));
            } else {

                Assert.state(ClassUtils.hasConstructor(paramClass),
                        () -> "The class [" + paramClass.getName() + "] has no default empty parameter constructor");
                Object webData = paramClass.newInstance();
                WebDataBinder binder = webDataBinderFactory.createBinder(nativeWebRequest,webData,paramName);
                binder.setFieldDefaultPrefix(paramPrefix);
                MutablePropertyValues pvs = new MutablePropertyValues(nativeWebRequest.getParameterMap());
                binder.bind(pvs);
                if (formModel.validate()) {

                    binder.validate((Object[]) formModel.groups());
                    binder.close();
                }
                return webData;
            }
        }
        return null;
    }

    private String obtainParamPrefix(FormModel formModel, String paramName) {

        // 获取注解中的指定参数名称的前缀
        String annotationParamName = formModel.prefix();
        // 获取控制层方法参数的名称，即源码中函数形参参数名
        // 决定属性名称前缀，属性名称前缀取决于注解内名称，若未指定则默认为函数形参参数名
        String prefix = StringUtils.hasText(annotationParamName) ? annotationParamName : paramName;
        // 获取分隔符
        String separator = formModel.separator();
        // 最终的属性前缀
        return prefix + separator;
    }

    private <T> Map<String, T> filterParameterMap(NativeWebRequest nativeWebRequest, String paramPrefix, Function<Object, T> converter) {

        Map<String, T> result = new HashMap<>();
        Map<String, String[]> parameterMap = nativeWebRequest.getParameterMap();
        parameterMap.forEach((k,v) -> {

            if (k.startsWith(paramPrefix)) {

                Object value = v.length == 1 ? v[0] : v;
                T resultValue = converter.apply(value);
                if (resultValue != null) {

                    String resultKey = k.substring(paramPrefix.length());
                    result.put(resultKey, resultValue);
                }
            }
        });
        return result;
    }
}
