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


        Ac ac = new Ac();
        ac.setName("name");
        int times = 10 * 10000;
//        int times = 10;
        exeTime(ac::getName,times);
        Method method = ReflectionUtils.findMethod(Ac.class, "getName");
        assert method != null;
        exeTime(() -> {

            try {
                method.invoke(ac);
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        },times);
    }

    static void exeTime(Runnable runnable, int times) {

        long start = System.currentTimeMillis();
        for (int i = 0; i < times; i++) {
            runnable.run();
        }
        long end = System.currentTimeMillis();
        System.out.println(new SimpleDateFormat("HH:mm:ss.SSS").format(new Date(end - start)));
    }
}
