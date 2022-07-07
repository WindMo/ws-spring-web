package ws.spring.web.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
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
}
