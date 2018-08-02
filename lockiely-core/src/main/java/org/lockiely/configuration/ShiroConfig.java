package org.lockiely.configuration;

import java.util.LinkedHashMap;
import java.util.Map;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.Cookie;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.lockiely.shiro.session.DefaultSessionManager;
import org.lockiely.shiro.session.RedisSessionDao;
import org.lockiely.shiro.RedisShiroCacheManager;
import org.lockiely.shiro.RetryLimitHashedCredentialsMatcher;
import org.lockiely.shiro.ShiroDbRealm;
import org.lockiely.shiro.session.ShireSessionFactory;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
@EnableConfigurationProperties(LockielyProperties.class)
public class ShiroConfig {

    private final LockielyProperties lockielyProperties;
    private final RedisTemplate redisTemplate;
    private final CacheManager cacheManager;

    public ShiroConfig(LockielyProperties lockielyProperties, RedisTemplate redisTemplate, CacheManager cacheManager){
        this.lockielyProperties = lockielyProperties;
        this.cacheManager = cacheManager;
        this.redisTemplate = redisTemplate;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilter(){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager());
        /**
         * 默认的登陆访问url
         */
        shiroFilterFactoryBean.setLoginUrl("/login");
        /**
         * 登陆成功后跳转的url
         */
//        shiroFilterFactoryBean.setSuccessUrl("/");
        /**
         * 没有权限跳转的url
         */
        shiroFilterFactoryBean.setUnauthorizedUrl("/global/error");
        /**
         * 配置shiro拦截器链
         *
         * anon  不需要认证
         * authc 需要认证
         * user  验证通过或RememberMe登录的都可以
         *
         */
        Map<String, String> hashMap = new LinkedHashMap<>();
        hashMap.put("/static/**", "anon");
        hashMap.put("/login", "anon");
        hashMap.put("/global/sessionError", "anon");
        hashMap.put("/kaptcha", "anon");
        hashMap.put("/**", "user");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(hashMap);
        return shiroFilterFactoryBean;
    }

    @Bean
    public SecurityManager securityManager(){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(realm());
        securityManager.setSessionManager(sessionManager());
        securityManager.setCacheManager(cacheManager);
        securityManager.setRememberMeManager(rememberMeManager());
        return securityManager;
    }

    @Bean
    public ShiroDbRealm realm() {
        ShiroDbRealm realm = new ShiroDbRealm();
        realm.setCredentialsMatcher(credentialsMatcher());
        return realm;
    }

    @Bean
    public RetryLimitHashedCredentialsMatcher credentialsMatcher(){
        RetryLimitHashedCredentialsMatcher credentialsMatcher = new RetryLimitHashedCredentialsMatcher(cacheManager);
        credentialsMatcher.setHashAlgorithmName("md5");
        credentialsMatcher.setHashIterations(3);
        credentialsMatcher.setStoredCredentialsHexEncoded(true);
        return credentialsMatcher;
    }

    @Bean
    public CookieRememberMeManager rememberMeManager(){
        //logger.info("注入Shiro的记住我(CookieRememberMeManager)管理器-->rememberMeManager", CookieRememberMeManager.class);
        CookieRememberMeManager rememberMeManager = new CookieRememberMeManager();
        rememberMeManager.setCookie(rememberMeCookie());
        //rememberme cookie加密的密钥 建议每个项目都不一样 默认AES算法 密钥长度（128 256 512 位），通过以下代码可以获取
        //KeyGenerator keygen = KeyGenerator.getInstance("AES");
        //SecretKey deskey = keygen.generateKey();
        //System.out.println(Base64.encodeToString(deskey.getEncoded()));
        byte[] cipherKey = Base64.decode("wGiHplamyXlVB11UXWol8g==");
        rememberMeManager.setCipherKey(cipherKey);
        return rememberMeManager;
    }

    @Bean
    public SimpleCookie rememberMeCookie(){
        //这个参数是cookie的名称，对应前端的checkbox的name = rememberMe
        SimpleCookie rememberMeCookie = new SimpleCookie("rememberMe");
        //如果httyOnly设置为true，则客户端不会暴露给客户端脚本代码，使用HttpOnly cookie有助于减少某些类型的跨站点脚本攻击；
        rememberMeCookie.setHttpOnly(true);
        //记住我cookie生效时间,默认7天 ,单位秒：60 * 60 * 24 * 7
        rememberMeCookie.setMaxAge(7 * 24 * 60 * 60);
        return rememberMeCookie;
    }

    @Bean
    public RedisSessionDao redisSessionDao(){
        RedisSessionDao redisSessionDao = new RedisSessionDao(redisTemplate);
        redisSessionDao.setShiroSessionPrefix(lockielyProperties.getShiroSessionPrefix());
        redisSessionDao.setExpireTime(lockielyProperties.getRedisSessionExpireTime());
        return redisSessionDao;
    }

    @Bean
    public SessionManager sessionManager(){
        DefaultSessionManager sessionManager = new DefaultSessionManager();
        sessionManager.setSessionDAO(redisSessionDao());
        sessionManager.setCacheManager(cacheManager);
        sessionManager.setSessionValidationInterval(lockielyProperties.getSessionValidationInterval());
        sessionManager.setGlobalSessionTimeout(lockielyProperties.getGlobalSessionTimeout());
        sessionManager.setDeleteInvalidSessions(true);
        sessionManager.setSessionValidationSchedulerEnabled(true);
        Cookie cookie = new SimpleCookie();
        cookie.setName("lockiely-shiro-cookie");
//        cookie.setHttpOnly(true);
        sessionManager.setSessionIdCookie(cookie);

        //重新定义session
        sessionManager.setSessionFactory(new ShireSessionFactory());
        return sessionManager;
    }

    @Configuration
    @ConditionalOnMissingBean(CacheManager.class)
    protected static class CacheManagerConfiguration{

        /**
         *
         * 缓存管理器， 使用Ehcache实现
         * @return
         */
        @Bean
        @ConditionalOnProperty(prefix = "lockiely", name = "cache-type", havingValue = "ehCache")
        public CacheManager ehCacheManager(EhCacheManagerFactoryBean ehCacheManagerFactoryBean){
            EhCacheManager ehCacheManager = new EhCacheManager();
            ehCacheManager.setCacheManager(ehCacheManagerFactoryBean.getObject());
            return ehCacheManager;
        }

        @Bean
        @ConditionalOnProperty(prefix = "lockiely", name = "cache-type", havingValue = "redisCache")
        public CacheManager redisCacheManager(RedisTemplate redisTemplate, LockielyProperties lockielyProperties){
            return new RedisShiroCacheManager(redisTemplate, lockielyProperties);
        }

    }

    @Bean
    public static LifecycleBeanPostProcessor lifecycleBeanPostProcessor(){
        return new LifecycleBeanPostProcessor();
    }

    /**
     * 开启 shiro aop 注解代理
     *
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(){
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager());
        return authorizationAttributeSourceAdvisor;
    }

    @Bean
    @DependsOn("lifecycleBeanPostProcessor")
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
        return defaultAdvisorAutoProxyCreator;
    }
}
