package com.egoo.seckill.bigfish.controller;


import com.egoo.seckill.bigfish.domain.*;
import com.egoo.seckill.bigfish.domain.enums.WithDrawStatus;
import com.egoo.seckill.bigfish.service.ExcelService;
import com.egoo.seckill.bigfish.service.UserService;
import com.egoo.seckill.bigfish.utils.MD5Utils;
import com.egoo.seckill.bigfish.utils.StringUtil;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/bigfish/v1")
@Slf4j
public class PageController {

    @Autowired
    private UserService userService;

    @GetMapping("/userLogin")
    public ModelAndView tologin(){
        ModelAndView modelAndView =new ModelAndView();

        modelAndView.setViewName("login");
        return modelAndView;
    }

    @GetMapping("/getUsers")
    @ResponseBody
    @JsonView({JsonViewResult.app.class})
    @RequiresRoles(value = "admin")
    @RequiresPermissions(value = "user:get")
    public Result<List<User>> getUsers(){
        log.debug("Current Object is :{{}}",userService.hashCode());
        return Result.successResult(userService.query());
    }

    @Data
    class JsonResult implements Serializable {
        private static final long serialVersionUID = -52356044583588208L;
        Integer code;
        String message;
    }

    @PostMapping("/login")
    @ResponseBody
    public Result login(String username,String password,
                      HttpServletRequest request){

        log.info("数据:username={},password={}",username,password );

        Subject subject = SecurityUtils.getSubject();

        byte[] bytes = password.getBytes();

        String pass = DigestUtils.md5DigestAsHex(bytes);
        //创建令牌
        AuthenticationToken token = new UsernamePasswordToken(username,pass);

        try {
            //验证登录
            subject.login(token);
            log.info("登陆成功,IP:{{}}",StringUtil.getIpAddr(request));
            return Result.successResult("登录成功");
        }catch (Exception e){
            log.info("登陆失败:{}",e.getMessage() );
        }
        return Result.errorResult("用户名或密码错");
    }

    @RequestMapping("/error")
    public String error() {
        System.out.println("ErrControlller.error");
        return "err"; // 不使用/error， 可以返回其他的， 但是要保证这个视图存在， 比如存在 public/err.html
    }

    @Autowired
    private ExcelService excelService;

    @GetMapping("/exportExcel")
    @RequiresRoles(value = "admin")
    @RequiresPermissions(value = "user:get")
    public void export(WithDrawStatus withDrawStatus,HttpServletResponse response){


        try {
           excelService.excute(withDrawStatus,response);
        } catch (IOException e) {
           log.error("导出excel异常：{}", e.getMessage());
        }

    }


    @GetMapping("/getJson")
    @ResponseBody
    @RequiresPermissions("other:get")
    public Result  jsons(){
        String[] arr = {"1","2","3","3","4"};
        return Result.successResult(arr) ;
    }


}

