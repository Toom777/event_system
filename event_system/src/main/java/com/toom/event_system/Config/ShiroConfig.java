package com.toom.event_system.Config;

import com.toom.event_system.Common.MyRealm;
import com.toom.event_system.Shiro.JwtFilter;
import com.toom.event_system.Shiro.MyCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.mgt.SubjectFactory;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Shiro配置类
 * 主要包括 ShiroFilterFactoryBean、DefaultWebSecurityManager、Realm
 * @author Toom
 */
@Configuration
public class ShiroConfig {

    @Autowired
    private MyRealm myRealm;

    @Autowired
    private MyCredentialsMatcher matcher;


    /**
     * 过滤器
     * @param securityManager
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager securityManager, JwtFilter jwtFilter) {
        ShiroFilterFactoryBean filterFactoryBean = new ShiroFilterFactoryBean();

        filterFactoryBean.setSecurityManager(securityManager);
        // 登录页面，无权限时跳转的路径
        filterFactoryBean.setLoginUrl("/login");
        filterFactoryBean.setUnauthorizedUrl("/login");

        Map<String, Filter> filter = new HashMap<>();
        filter.put("jwt", jwtFilter);
        filterFactoryBean.setFilters(filter);
        // 配置拦截规则
        Map<String, String> map = new LinkedHashMap<>();

        /*无需登录验证*/
        map.put("/", "anon");
        map.put("/login", "anon");
        map.put("/captchaImage", "anon");
        map.put("/swagger-ui/**", "anon");

        /*所有请求都通过次jwt进行过滤，需要登录验证才能访问*/
        map.put("/**", "jwt");

        filterFactoryBean.setFilterChainDefinitionMap(map);
        return filterFactoryBean;
    }

    /**
     * 注入Web安全管理器
     */
    @Bean
    public DefaultWebSecurityManager getDefaultSecurityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        myRealm.setCredentialsMatcher(matcher);
        securityManager.setRealm(myRealm);
        return securityManager;
    }


    @Bean
    public JwtFilter getJwtFiler(){
        return new JwtFilter();
    }

    /**
     * 开启AOP注解支持
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager manager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(manager);
        return advisor;
    }
}
