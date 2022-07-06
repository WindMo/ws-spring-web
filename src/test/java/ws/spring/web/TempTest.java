package ws.spring.web;

import lombok.Getter;
import lombok.Setter;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.function.BooleanSupplier;
import java.util.function.Supplier;

/**
 * @author WindShadow
 * @version 2021-12-19.
 */

public class TempTest {

    static class Ac {

        @Getter
        @Setter
        private String name;
    }

    public static void main(String[] args) {

        if (System.out.append("A") == null) {

            System.out.print("A");
        } else {

            System.out.print("B");
        }
    }

}
