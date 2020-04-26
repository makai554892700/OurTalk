package www.mys.com.ourtalk.common.shiro;

import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import www.mys.com.ourtalk.common.auth.AuthRealm;
import www.mys.com.ourtalk.common.auth.CredentialsMatcher;
import www.mys.com.ourtalk.common.auth.ShiroRedisCacheManager;
import www.mys.com.ourtalk.mapper.RedisSessionDAO;

import java.util.LinkedHashMap;

@Configuration
public class ShiroConfig {

    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(@Qualifier("securityManager") SecurityManager manager) {
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        bean.setSecurityManager(manager);
        //配置登录的url和登录成功的url
        bean.setLoginUrl("/login.html");
        bean.setSuccessUrl("/index.html");
        //配置访问权限
        LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        filterChainDefinitionMap.put("/", "anon"); //表示可以匿名访问
        filterChainDefinitionMap.put("/*", "anon");//表示需要认证才可以访问
        filterChainDefinitionMap.put("/**", "anon");
        filterChainDefinitionMap.put("/*.*", "anon");
        filterChainDefinitionMap.put("/logout.html", "logout");//退出登陆，具体代码shiro已经实现
        filterChainDefinitionMap.put("/api/basesb/user/**", "authc");
        filterChainDefinitionMap.put("/api/basesb/user/register", "anon");
        bean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return bean;
    }

    //配置核心安全事务管理器
    @Bean(name = "securityManager")
    public SecurityManager securityManager(@Qualifier("authRealm") AuthRealm authRealm
            , @Qualifier("sessionManager") SessionManager sessionManager) {
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setRealm(authRealm);
        manager.setSessionManager(sessionManager);
        return manager;
    }

    //配置自定义的session管理器
    @Bean(name = "sessionManager")
    public SessionManager sessionManager(@Qualifier("redisSessionDAO") RedisSessionDAO sessionDAO
            , @Qualifier("redisCacheManager") CacheManager cacheManager) {
        MyWebSessionManager sessionManager = new MyWebSessionManager();
        sessionManager.setSessionDAO(sessionDAO);//设置session保存位置
        sessionManager.setGlobalSessionTimeout(sessionDAO.getExpireTime());//设置默认session过期时间s
        sessionManager.setCacheManager(cacheManager);
        return sessionManager;
    }

    @Bean(name = "redisCacheManager")
    public ShiroRedisCacheManager redisCacheManager() {
        return new ShiroRedisCacheManager();
    }

    //配置自定义的权限登录器
    @Bean(name = "authRealm")
    public AuthRealm authRealm(@Qualifier("credentialsMatcher") CredentialsMatcher matcher) {
        AuthRealm authRealm = new AuthRealm();
        authRealm.setCredentialsMatcher(matcher);
        return authRealm;
    }

    //配置自定义的密码比较器
    @Bean(name = "credentialsMatcher")
    public CredentialsMatcher credentialsMatcher() {
        return new CredentialsMatcher();
    }

    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator creator = new DefaultAdvisorAutoProxyCreator();
        creator.setProxyTargetClass(true);
        return creator;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(@Qualifier("securityManager") SecurityManager manager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(manager);
        return advisor;
    }

}
