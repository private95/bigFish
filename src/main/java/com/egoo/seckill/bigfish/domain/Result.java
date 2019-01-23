package com.egoo.seckill.bigfish.domain;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
@Data
public class Result<T> implements Serializable {

    //状态码
    @JsonView({JsonViewResult.app.class})
    private int state;

    //调用时间
    @JsonView({JsonViewResult.app.class})
    private long time;

    //数据
    @JsonView({JsonViewResult.app.class})
    private T data;

    public Result(){}

    public Result(int state,long time,T data){
        this.state = state;
        this.time = time;
        this.data = data;
    }

    public static Result successResult(Object data){
        return new Result(200,1000,data);
    }
    public static Result errorResult(Object data){
        return new Result(400,1000,data);
    }




}
