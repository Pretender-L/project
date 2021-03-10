package com.project.oauth.controller;

import com.alibaba.fastjson.JSON;
import com.project.oauth.util.CookieUtil;
import com.project.oauth.util.JwtUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/oauth")
public class AuthController {
    @RequestMapping("/toLogin")
    public String toLogin(@RequestParam(value = "FROM", required = false, defaultValue = "") String from, Model model) {
        if (StringUtils.isNotEmpty(from)) {
            model.addAttribute("from", from);
        }
        return "login";
    }

    /***
     * security通过过滤器拦截的，请求必须携带username,password才会走过滤器（登陆url需要判断是否携带参数）
     * 登陆请求参数为空不走过滤器，直接走登陆url（配置了登录处理url不会被框架拦截）
     */
    @PostMapping("/login")
    public String login(String username, String password, String from, HttpServletResponse response) {
        //生成jwt令牌，返回给客户端
        /*HashMap<Object, Object> info = new HashMap<>();
        info.put("username", username);*/
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Map<String, String> userMap = new HashMap<>();
        userMap.put("Username", username);
        //userMap.put("Credentials",authentication.getCredentials().toString());
        userMap.put("Authority", authentication.getAuthorities().toString());
        //用工具类生成令牌,工具类默认1小时过期
        String jwt = JwtUtil.createJWT(UUID.randomUUID().toString(), JSON.toJSONString(userMap), null);
        //方案1：前端获取增加请求头（请求头 token:jwt）
        /*info.put("token", jwt);*/
        //方案2：写入cookie
        CookieUtil.addCookie(response, "localhost", "/", "token", jwt, 60 * 60, false);
        /*return Result.success("登录成功", info);*/
        if (StringUtils.isNotEmpty(from)) {
            return "redirect:http://localhost:8000" + from;
        }
        return "index";
    }
}
