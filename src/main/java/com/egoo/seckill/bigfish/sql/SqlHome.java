package com.egoo.seckill.bigfish.sql;

import com.egoo.seckill.bigfish.domain.User;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.stereotype.Service;

@Service
public class SqlHome {

    public String insertSQL(User user){
        return new SQL().INSERT_INTO("user")
                .INTO_COLUMNS("name","pass")
                .INTO_VALUES(_sqlStr(user.getName()))
                .INTO_VALUES(_sqlStr(user.getPass()))
                .toString();
    }

    private String _sqlStr(String value){
        return new String("'"+value+"'");
    }

    public String querySQL(){
        SQL sql = new SQL();
        return sql.toString();
    }

    public String findUserByName(String username){
        return new SQL().SELECT("*").FROM("user").WHERE("name = "+_sqlStr(username)).toString();
    }

}
