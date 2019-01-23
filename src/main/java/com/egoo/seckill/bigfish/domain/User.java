package com.egoo.seckill.bigfish.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Data
@Component
@EqualsAndHashCode(callSuper = true)
@TableName(value = "user")
public class User extends Model<User> implements Serializable {

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    @JsonView({JsonViewResult.app.class})
    private String name;

    private String pass;
}
