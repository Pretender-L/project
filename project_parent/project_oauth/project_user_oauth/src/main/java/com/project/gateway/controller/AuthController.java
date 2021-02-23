package com.project.gateway.controller;

import com.project.excetion.BadException;
import com.project.gateway.service.AuthService;
import com.project.gateway.util.AuthToken;
import com.project.gateway.util.CookieUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/oauth")
public class AuthController {

    public static final String REDIRECT_URL_PREFIX = "http://localhost:9000";

    @Autowired
    private AuthService authService;

    @Value("${auth.clientId}")
    private String clientId;

    @Value("${auth.clientSecret}")
    private String clientSecret;

    @Value("${auth.cookieDomain}")
    private String cookieDomain;

    @Value("${auth.cookieMaxAge}")
    private int cookieMaxAge;

    @RequestMapping("/toLogin")
    public String toLogin(@RequestParam(value = "FROM", required = false, defaultValue = "") String from, Model model) {
        model.addAttribute("from", from);
        return "login";
    }

    @PostMapping("/login")
    public String login(String username, String password, String from, HttpServletResponse response) throws BadException {
        //校验参数
        if (StringUtils.isEmpty(username)) {
            throw new BadException("请输入用户名");
        }
        if (StringUtils.isEmpty(password)) {
            throw new BadException("请输入密码");
        }
        //申请令牌 authtoken
        AuthToken authToken = authService.login(username, password, clientId, clientSecret);
        //将jti的值存入cookie中
        this.saveJtiToCookie(authToken.getJti(), response);
        //返回结果
        //return Result.success("登录成功", authToken.getJti());
        if (StringUtils.isNotEmpty(from)) {
            return "redirect:" + REDIRECT_URL_PREFIX + from;
        }
        return "index";
    }

    //将令牌的断标识jti存入到cookie中
    private void saveJtiToCookie(String jti, HttpServletResponse response) {
        CookieUtil.addCookie(response, cookieDomain, "/", "uid", jti, cookieMaxAge, false);
    }

}
