package ws.spring.web.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import ws.spring.web.SpringWebApplicationTests;
import ws.spring.web.pojo.City;
import ws.spring.web.pojo.User;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author WindShadow
 * @version 2022-06-23.
 * @see WebBindController
 */

@AutoConfigureMockMvc
public class WebBindControllerTests extends SpringWebApplicationTests {

    @Test
    void paramPrefixTest(@Autowired MockMvc mvc) throws Exception {

        User user = new User("tom", "tom ca", "123@qq.com");
        City city = new City("北京", "中国首都");
        String content = mvc.perform(get("/bind/param-prefix")
                .param("user.name", user.getName())
                .param("user.desc", user.getDesc())
                .param("user.email", user.getEmail())
                .param("city.name", city.getName())
                .param("city.desc", city.getDesc()))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        Assertions.assertEquals(WebBindController.toString(user,city), content);
    }

    @Test
    void allowedFieldsTest(@Autowired MockMvc mvc) throws Exception {

        User user = new User("tom", "tom ca", "123@qq.com");
        String content = mvc.perform(get("/bind/allowed-fields")
                .param("name", user.getName())
                .param("desc", user.getDesc())
                .param("email", user.getEmail()))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        user.setDesc(null);
        user.setEmail(null);
        Assertions.assertEquals(WebBindController.toString(user), content);
    }
}
