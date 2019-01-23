package com.egoo.seckill.bigfish.realm;

import com.egoo.seckill.bigfish.domain.User;
import com.egoo.seckill.bigfish.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 权限验证
 *
 * @author lixing
 */
@Slf4j
public class MyShiroRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    /**
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        log.info("用户权限配置");

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();

        User userInfo = (User) principalCollection.getPrimaryPrincipal();
        log.info("用户信息：{{}}", userInfo);

        //根据用户查询对应的角色...TODO 未实现
        authorizationInfo.addRole("admin");

        //根据角色查询对应的权限...TODO 未实现
        authorizationInfo.addStringPermission("user:get");
//        authorizationInfo.addStringPermission("other:get");

        return authorizationInfo;
    }

    /**
     * 认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        log.info("用户登录验证");

        //获取用户名
        String username = (String) authenticationToken.getPrincipal();

        User user = userService.findUserByName(username);

        if(user == null){return null;}

        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user, user.getPass(),user.getName());

        return authenticationInfo;
    }
}
