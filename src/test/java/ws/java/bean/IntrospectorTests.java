package ws.java.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

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

    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class JavaPojo {

        private String name;
        private String desc;
        private String email;
        private boolean ok;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    public static class OffbeatPojo {

        private boolean need;

        public boolean getNeed() {
            return need;
        }

        public void setNeed(boolean need) {
            this.need = need;
        }
    }

    @Test
    public void beanInfoTest() throws IntrospectionException {

        BeanInfo beanInfo = Introspector.getBeanInfo(JavaPojo.class);
        PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor pd : pds) {

            printPropertyDescriptor(pd);
            log.info("------------------------------------");
        }

        log.info("\n");
        BeanInfo beanInfo2 = Introspector.getBeanInfo(OffbeatPojo.class);
        PropertyDescriptor[] pds2 = beanInfo2.getPropertyDescriptors();
        for (PropertyDescriptor pd2 : pds2) {

            printPropertyDescriptor(pd2);
            log.info("------------------------------------");
        }
    }

    @Test
    public void propertyDescriptorTest() throws IntrospectionException {

        PropertyDescriptor pd = new PropertyDescriptor("name",JavaPojo.class);
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
