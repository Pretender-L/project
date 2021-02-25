package com.project.web.filter;

/**
 * 所有需要传递令牌的地址(受保护)
 */
public class UrlFilter {
    public static final String filterPath = "/user," + "/*";

    //是否授权验证
    public static boolean hasAuthorize(String url) {
        String[] filterPaths = filterPath.split(",");
        for (String path : filterPaths) {
            if (url.contains(path)) {
                return true;//当前访问地址是需要访问令牌的
            }
        }
        return false;//当前访问地址是不需要访问令牌的
    }
}
