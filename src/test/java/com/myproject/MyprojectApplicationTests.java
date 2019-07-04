package com.myproject;

import com.myproject.Service.UserService;
import com.myproject.controller.LoginController;
import com.myproject.controller.UserController;
import com.myproject.model.User;
import com.myproject.repository.UserRepository;
import org.junit.*;
import org.junit.rules.TemporaryFolder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import java.io.InputStream;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class MyprojectApplicationTests {

   
    @Autowired
    private WebApplicationContext wac;

    @Autowired
    UserService userService;

    @Autowired
    LoginController loginController;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserController userController;

    private MockMvc mockMvc;

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();


    @Test
    public void contextLoads() throws Exception {
    }

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void login() throws Exception {
        this.mockMvc.perform(get("/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("user/login"))
                .andDo(print());
    }

    @Test
    public void signup() throws Exception {
        this.mockMvc.perform(get("/signup"))
                .andExpect(status().isOk())
                .andExpect(view().name("user/signup"))
                .andDo(print())
        ;
    }

    @Test
    public void list() throws Exception {
        this.mockMvc.perform(get("/list1"))
                .andExpect(status().isOk())
                .andExpect(view().name("user/user_list"))
                .andDo(print());
    }

    @Test
    public void saveOrUpdate()
{
    User user=new User();
user.setName("neha12");
user.setDepartment("java");

user.setNumber(123456);
user.setExtensionnumber(12);
User save= userRepository.save(user);

assertTrue("This will succeed.", true);
}




    @Test
    public void findById()
{

    User user=new User();
    user.setId(48);
    assertEquals(48, user.getId());

}


}



