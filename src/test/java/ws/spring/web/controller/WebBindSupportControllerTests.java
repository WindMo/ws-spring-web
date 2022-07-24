package ws.spring.web.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.util.NestedServletException;
import ws.spring.web.SpringWebApplicationTests;
import ws.spring.web.pojo.City;
import ws.spring.web.pojo.User;
import ws.spring.web.util.ObjectUtils;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author WindShadow
 * @version 2022-07-05.
 * @see WebBindSupportController
 */

@AutoConfigureMockMvc
public class WebBindSupportControllerTests extends SpringWebApplicationTests {

    @Test
    void formModelDefaultPrefixTest(@Autowired MockMvc mvc) throws Exception {

        User user = new User("tom", "tom ca", "123@qq.com");
        City city = new City("北京", "中国首都");
        String content = mvc.perform(get("/support/from-model/default")
                .param("user.name", user.getName())
                .param("user.desc", user.getDesc())
                .param("user.email", user.getEmail())
                .param("city.name", city.getName())
                .param("city.desc", city.getDesc()))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        Assertions.assertEquals(ObjectUtils.toString(user,city), content);
    }

    @Test
    void formModelTest(@Autowired MockMvc mvc) throws Exception {

        User user = new User("tom", "tom ca", "123@qq.com");
        City city = new City("北京", "中国首都");
        String content = mvc.perform(get("/support/from-model")
                .param("user.name", user.getName())
                .param("user.desc", user.getDesc())
                .param("user.email", user.getEmail())
                .param("city.name", city.getName())
                .param("city.desc", city.getDesc()))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        Assertions.assertEquals(ObjectUtils.toString(user,city), content);
    }

    @Test
    void formModelValidateTest(@Autowired MockMvc mvc) throws Exception {

        User user = new User("tom", "tom ca", "123qq.com");
        City city = new City("北京", "中国首都");
        mvc.perform(get("/support/from-model/validate")
                .param("user.name", user.getName())
                .param("user.desc", user.getDesc())
                .param("user.email", user.getEmail())
                .param("city.name", city.getName())
                .param("city.desc", city.getDesc()))
                .andExpect(status().isBadRequest());

    }

    @Test
    void formModelOfStringTest(@Autowired MockMvc mvc) throws Exception {

        String str = "abcdefg";
        Assertions.assertThrows(NestedServletException.class,
                () -> mvc.perform(get("/support/from-model/string")
                        .param("user.name", str)));
    }

    // ~ Map 系列
    // =====================================================================================

    @Test
    void formModelOfMapStringTest(@Autowired MockMvc mvc) throws Exception {

        mvc.perform(get("/support/from-model/map/string")
                .param("user.id", "id-xxx")
                .param("user.name-xxx", "18"))
                .andExpect(status().isOk());
    }

    @Test
    void formModelOfMapIntegerTest(@Autowired MockMvc mvc) throws Exception {

        mvc.perform(get("/support/from-model/map/integer")
                .param("user.id", "123456")
                .param("user.age", "18"))
                .andExpect(status().isOk());
    }

    @Test
    void formModelOfMapObjectTest(@Autowired MockMvc mvc) throws Exception {

        mvc.perform(get("/support/from-model/map/object")
                .param("user.id", "id-xxx")
                .param("user.age", "18"))
                .andExpect(status().isOk());
    }

    @Test
    void formModelOfMapPojoTest(@Autowired MockMvc mvc) throws Exception {

        mvc.perform(get("/support/from-model/map/pojo")
                .param("user.tom", "tom-18")
                .param("user.jerry", "jerry-17"))
                .andExpect(status().isOk());
    }

    @Test
    void formModelOfMapStringUnkonwTest(@Autowired MockMvc mvc) throws Exception {

        mvc.perform(get("/support/from-model/map/string/unknow")
                .param("user.id", "id-xxx")
                .param("user.age", "18")
                .param("user.name", "name-xxx"))
                .andExpect(status().isOk());
    }

    @Test
    void formModelOfMapUnkonwUnkonwTest(@Autowired MockMvc mvc) throws Exception {

        mvc.perform(get("/support/from-model/map/unknow/unknow")
                .param("user.id", "id-xxx")
                .param("user.age", "18")
                .param("user.name", "name-xxx"))
                .andExpect(status().isOk());
    }

    // ~ 集合系列
    // =====================================================================================

    @Test
    void formModelOfListStringTest(@Autowired MockMvc mvc) throws Exception {

        mvc.perform(get("/support/from-model/list/string")
                .param("user.id", "id-xxx")
                .param("user.age", "18")
                .param("user.name", "name-xxx"))
                .andExpect(status().isOk());
    }
}
