package com.egoo.seckill.bigfish.filters;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
@Slf4j
public class PageFilter {
//    @Override
//    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object o) throws Exception {
//
//        Subject subject =this.getSubject(request, response);
//
//        boolean isPermitted = true;
//
//        if(subject.getPrincipal() == null){
//            log.error("login time is invial. reload login");
//            this.saveRequestAndRedirectToLogin(request,response);
//        }
//
//        return isPermitted;
//    }
}
