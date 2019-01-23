package com.egoo.seckill.bigfish.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.egoo.seckill.bigfish.domain.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface UsersMapper extends BaseMapper<User> {

    int insertUser(@Param("sql") String sql);

    int deleteUser(Long id);

    List<User> selectUsers();

    User findUserByUserName(@Param("username")String username);

    /*@Results(id="userResultMap",value = {
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "name",property = "name"),
            @Result(column = "pass",property = "pass")
    })
    User userResultMap();


    @ResultMap("userResultMap")
    @Select("select * from user where id = #{id}")
    User queryUserById(int id );*/
}
