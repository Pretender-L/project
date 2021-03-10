package com.project.oauth.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class CookieUtil {
    /***
     * 设置cookie
     * @param response 响应对象
     * @param name     cookie名字
     * @param value    cookie值
     * @param domain   cookie域 （例：localhost）
     * @param maxAge   cookie生命周期 以秒为单位（-1为关闭浏览器失效）
     * @param httpOnly 为ture js脚本是读不到该cookie的
     */
    public static void addCookie(HttpServletResponse response, String domain, String path, String name, String value, int maxAge, boolean httpOnly) {
        Cookie cookie = new Cookie(name, value);
        cookie.setDomain(domain);
        cookie.setPath(path);
        cookie.setMaxAge(maxAge);
        cookie.setHttpOnly(httpOnly);
        response.addCookie(cookie);
    }

    /***
     * 根据cookie名称读取cookie
     */
    public static Map<String, String> readCookie(HttpServletRequest request, String... cookieNames) {
        Map<String, String> cookieMap = new HashMap<>();
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                String cookieName = cookie.getName();
                String cookieValue = cookie.getValue();
                for (String name : cookieNames) {
                    if (name.equals(cookieName)) {
                        cookieMap.put(cookieName, cookieValue);
                    }
                }
            }
        }
        return cookieMap;
    }
}
