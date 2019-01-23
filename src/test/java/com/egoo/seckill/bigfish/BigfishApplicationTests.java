package com.egoo.seckill.bigfish;

import com.egoo.seckill.bigfish.domain.User;
import com.egoo.seckill.bigfish.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class BigfishApplicationTests {

    @Autowired
    private UserService userService;

    @Test
    public void contextLoads() {
        User user =new User();
        user.setName("张三");
        user.setPass("123");
        int i = userService.insertUser(user);
        log.info("result is [{}]",i );
    }
    @Test
    public void select() {
        User user = userService.findUserByName("tom");
        log.info("result is [{}]",user );
    }

}
