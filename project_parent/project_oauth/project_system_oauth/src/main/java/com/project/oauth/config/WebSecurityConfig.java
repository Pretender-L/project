package com.project.oauth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * security配置类
 * 任何访问都会被被拦下来(除了策略允许的)
 */
@Configuration
@EnableWebSecurity
@Order(-1)
class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    /***
     * 忽略哪些资源不用security来管理
     * 忽略安全拦截的URL（不会走过滤器）
     * WebSecurity 主要针对的全局的忽略规则
     */
    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/actuator/**", "/oauth/toLogin",
                "/oauth/logout", "/login.html", "/css/**", "/data/**", "/fonts/**", "/img/**", "/js/**");
    }

    /***
     * 创建授权管理认证对象
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /***
     * 采用BCryptPasswordEncoder对密码进行编码
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /***
     * 配置策略,比如防止csrf攻击
     * HttpSecurity主要是权限控制规则
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                //登陆页面
                .loginPage("/oauth/toLogin")
                //登陆url
                .loginProcessingUrl("/oauth/login")
                //登陆成功转发地址
                .successForwardUrl("/oauth/login")
                .permitAll()
                .and()
                .csrf()
                .disable();
    }
}

