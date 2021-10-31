import com.powernode.beans.User;
import com.powernode.mapper.UserMapper;
import com.powernode.service.UserService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext.xml"})
public class Test {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    @org.junit.Test
    public void getUser(){
        User u = userMapper.getUser("llm", "123");
        System.out.println(u);
    }

    @org.junit.Test
    public void getUser2(){
        User llm = userService.LoginUser("llm", "1234", "127.0.0.3");
        System.out.println("ok");
    }
}
