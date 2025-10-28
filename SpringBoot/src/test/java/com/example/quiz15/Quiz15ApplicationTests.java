package com.example.quiz15;

import com.example.quiz15.dao.user.UserDao;
import com.example.quiz15.entity.user.User;
import com.example.quiz15.service.user.ifs.UserService;
import com.example.quiz15.vo.user.AddUserRequest;
import com.example.quiz15.vo.user.UserResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.Assert;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


@SpringBootTest
class Quiz15ApplicationTests {

    @Autowired
    UserDao userDao;

    @Autowired
    UserService userService;

    // mockMvc 是基於 WebApplicationContext 所建立的物件，可用來編寫 web 應用的整合測試
    @Autowired
    private WebApplicationContext wac;

    // 實現對 http 請求的模擬，主要用來測試 controller
    private MockMvc mockMvc;

    @BeforeEach
    public void setup(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    // 指定 content_type 和編碼
    private static final String CONTENT_TYPE = "application/json;charset=UTF-8";

	@Test
	void contextLoads() throws Exception {
//        AddUserRequest req = new AddUserRequest();
//        req.setEmail("test1234@gmail.com");
//        req.setName("abc");
//        req.setAge(18);
//        req.setPassword("12345678");
//        UserResponse rep = userService.addUser(req);
//        Assert.isTrue(!rep.getMessage().equalsIgnoreCase("信箱已存在"),//
//                "Email Exist Error");
//        Assert.isTrue(!rep.getMessage().equalsIgnoreCase("成功"),
//                "Email Exist Error");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String req = setReqBody("David","0912345678","AA1234@gmail.com",17,"AA123456");
        validContorller(req,"/add/user","Name Error");
	}

    private String setReqBody(String name,String phone,String email,int age,String password) throws JsonProcessingException {
        Map<String,Object> map = new HashMap<>();
        map.put("name",name);
        map.put("phone",phone);
        map.put("email",email);
        map.put("age",age);
        map.put("password",password);

        ObjectMapper objectMapper = new ObjectMapper();
        String mapString = objectMapper.writeValueAsString(map);
        return mapString;
    }

    private void validContorller(String req,String url,String check) throws Exception {
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.post(url)
                .contentType(CONTENT_TYPE).content(req));
        String repStr = response.andReturn().getResponse().getContentAsString();
        ObjectMapper mapper = new ObjectMapper();
        Map<String,Object> resMap = mapper.readValue(repStr, Map.class);
        String message = resMap.get("message").toString();
        Assert.isTrue(message.equalsIgnoreCase(check),"");
    }
}
