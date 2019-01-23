package com.egoo.seckill.bigfish.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.egoo.seckill.bigfish.domain.User;
import com.egoo.seckill.bigfish.mapper.UsersMapper;
import com.egoo.seckill.bigfish.sql.SqlHome;
import com.egoo.seckill.bigfish.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.core.ReactiveValueOperations;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import java.util.List;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
@Service
@Slf4j
public class UserService extends ServiceImpl<UsersMapper,User> {


    @Autowired
    private ReactiveRedisTemplate reactiveRedisTemplate;

    private ReactiveValueOperations reactiveValueOperations;

    private static final String keys = "big-fish:users";


    @PostConstruct
    public void init(){reactiveValueOperations = reactiveRedisTemplate.opsForValue();}

    @Autowired
    private UsersMapper usersMapper;

    @Autowired
    private SqlHome sqlHome;

    //添加用户
    public int insertUser(User user){

        return usersMapper.insertUser(sqlHome.insertSQL(user));


    }

    //根据用户名查询
    public User findUserByName(String username){
        return usersMapper.findUserByUserName(username);
    }

    //查询所有用户
    public List<User> querys(){
        if(hasKey(formatKey())){
            log.debug("[redis is value]");
            return JsonUtils.jsonToList(String.valueOf(reactiveValueOperations.get(keys).block()), User.class);
        }

        List<User> users = usersMapper.selectUsers();
        reactiveValueOperations.set(formatKey(),JsonUtils.objectToJson(users) ).block();
        log.debug("[redis is null , DB query!]");
        return users;
    }


    private boolean hasKey(String key){
       return (boolean) reactiveRedisTemplate.hasKey(key).block();
    }

    public String formatKey() {
        String key = null;
        try {
            key = new String(keys.getBytes("ISO-8859-1"),"UTF-8");
            throw new NullPointerException("空值了");
        } catch (Exception e) {
            String method = Thread.currentThread() .getStackTrace()[1].getMethodName();
            log.error("异常方法名称：{{}},异常信息：{{}}",method,e.getMessage());
        }
        return key;
    }
}
