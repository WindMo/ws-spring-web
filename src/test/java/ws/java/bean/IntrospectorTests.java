package ws.java.bean;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import ws.spring.web.pojo.User;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;

/**
 * java内省机制
 * @author WindShadow
 * @version 2021-12-21.
 */

@Slf4j
public class IntrospectorTests {

    @Test
    public void beanInfoTest() throws IntrospectionException {

        BeanInfo beanInfo = Introspector.getBeanInfo(User.class);
        PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor pd : pds) {

            printPropertyDescriptor(pd);
            log.info("------------------------------------");
        }
    }

    @Test
    public void propertyDescriptorTest() throws IntrospectionException {

        PropertyDescriptor pd = new PropertyDescriptor("name",User.class);
        printPropertyDescriptor(pd);

    }

    private static void printPropertyDescriptor(PropertyDescriptor pd) {

        log.info("DisplayName: {}",pd.getDisplayName());
        log.info("Name: {}",pd.getName());
        log.info("PropertyEditorClass: {}",pd.getPropertyEditorClass());
        // 属性类型
        log.info("PropertyTyp: {}",pd.getPropertyType());
        // get 方法
        log.info("ReadMethod: {}",pd.getReadMethod());
        // set 方法
        log.info("WriteMethod: {}",pd.getWriteMethod());
        log.info("ShortDescription: {}",pd.getShortDescription());
    }
}
