package com.egoo.seckill.bigfish.config;

import com.egoo.seckill.bigfish.filters.PageFilter;
import com.egoo.seckill.bigfish.realm.MyShiroRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.apache.shiro.mgt.SecurityManager;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

/**
 * shiro权限配置
 * @author lixing
 */
@Configuration
public class ShiroConfig {


    public ShiroConfig(){
        System.out.println("ShiroConfig  init ....");
    }

    /**
     shiro过滤器配置
     */
    @Bean
    public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager) {
        System.out.println("ShiroConfiguration.shirFilter()");
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //拦截器.
        Map<String,String> filterChainDefinitionMap = new  LinkedHashMap<String,String>();
        //权限配置
        //filterChainDefinitionMap.put("/stu/addStu","perms[student:aaaa]");
        // 配置不会被拦截的链接 顺序判断  相关静态资源
        filterChainDefinitionMap.put("/assets/**", "anon");
        filterChainDefinitionMap.put("/css/**", "anon");
        filterChainDefinitionMap.put("/font/**", "anon");
        filterChainDefinitionMap.put("/static/**", "anon");
        filterChainDefinitionMap.put("/js/**", "anon");
        filterChainDefinitionMap.put("/products/**", "anon");
        filterChainDefinitionMap.put("/Widget/**", "anon");

        //配置退出 过滤器,其中的具体的退出代码Shiro已经替我们实现了
        filterChainDefinitionMap.put("/logout", "logout");

        //<!-- 过滤链定义，从上向下顺序执行，一般将/**放在最为下边 -->:这是一个坑呢，一不小心代码就不好使了;
        filterChainDefinitionMap.put("/bigfish/v1/login", "anon");
        filterChainDefinitionMap.put("/bigfish/v1/error", "anon");
        //<!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->
        filterChainDefinitionMap.put("/**", "authc");

        // 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
        shiroFilterFactoryBean.setLoginUrl("/bigfish/v1/userLogin");

        // 登录成功后要跳转的链接
        //shiroFilterFactoryBean.setSuccessUrl("/index");

        //未授权界面;
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }
    /*
    加密方式配置
    */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher(){
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("md5");//散列算法:这里使用MD5算法;
        hashedCredentialsMatcher.setHashIterations(2);//散列的次数，比如散列两次，相当于 md5(md5(""));
        return hashedCredentialsMatcher;
    }

    /*
    认证器配置
    */
    @Bean
    public MyShiroRealm myShiroRealm(){
        MyShiroRealm myShiroRelam = new MyShiroRealm();
        //myShiroRelam.setCredentialsMatcher(hashedCredentialsMatcher());
        return myShiroRelam;
    }


    /*
    安全管理器配置
    */
    @Bean
    public SecurityManager securityManager(){
        DefaultWebSecurityManager securityManager =  new DefaultWebSecurityManager();
        securityManager.setRealm(myShiroRealm());
        return securityManager;
    }

    /*
    开启@RequirePermission注解的配置，要结合DefaultAdvisorAutoProxyCreator一起使用，或者导入aop的依赖
    */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

     @Bean
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator(){
          DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
          advisorAutoProxyCreator.setProxyTargetClass(true);
          return advisorAutoProxyCreator;
    }


    /*
    定义Spring MVC的异常处理器
    */
    @Bean
    public SimpleMappingExceptionResolver createSimpleMappingExceptionResolver() {
        SimpleMappingExceptionResolver r = new SimpleMappingExceptionResolver();
        Properties mappings = new Properties();
        mappings.setProperty("DatabaseException", "databaseError");//数据库异常处理
        mappings.setProperty("UnauthorizedException","403");//处理shiro的认证未通过异常
        r.setExceptionMappings(mappings);  // None by default
//        r.setDefaultErrorView("err");    // No default
//        r.setExceptionAttribute("ex");     // Default is "exception"
        return r;
    }




}
