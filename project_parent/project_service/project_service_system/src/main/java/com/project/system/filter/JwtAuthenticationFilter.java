package com.project.system.filter;

import com.alibaba.fastjson.JSON;
import com.project.system.util.JwtUtil;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

@Configuration
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        //获得请求头
        String jwtToken = httpServletRequest.getHeader("Token") == null ? "" : httpServletRequest.getHeader("Token");
        if (!StringUtils.isEmpty(jwtToken)) {
            try {
                //解析jwt
                Claims claims = JwtUtil.parseJWT(jwtToken);
                //获得主题
                String subject = claims.getSubject();
                System.out.println(subject);
                //json转map
                Map map = JSON.parseObject(subject, Map.class);
                String username = (String) map.get("Username");
                String authoritys = (String) map.get("Authority");
                //去除[]
                String substring = authoritys.substring(1, authoritys.length() - 1);
                ArrayList<GrantedAuthority> arrayList = new ArrayList<>();
                //创建权限对象
                for (String authority : substring.split(",")) {
                    SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(authority);
                    arrayList.add(simpleGrantedAuthority);
                }
                //构造鉴权对象
                Authentication authentication = new UsernamePasswordAuthenticationToken(username, null, arrayList);
                //放入security上下文中
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //放行
        //没有权限会被security拦截
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
