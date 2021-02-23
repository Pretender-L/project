package com.project.web.filter;

/**
 * 所有需要传递令牌的地址(受保护)
 */
public class UrlFilter {

    /**
     * /user：保护整个服务
     * /worder/**这种格式：api/worder请求无法保护；
     */
    public static final String filterPath = "/worder/**,/wseckillorder,/seckill,/wxpay,/wxpay/**,/worder/**,/user," +
            "/address/**,/wcart/**,/cart/**,/categoryReport/**,/orderConfig/**,/order/**,/orderItem/**," +
            "/orderLog/**,/preferential/**,/returnCause/**,/returnOrder/**,/returnOrderItem/**";

    //是否授权验证
    public static boolean hasAuthorize(String url) {
        String[] filterPaths = filterPath.replace("**", "").split(",");
        for (String path : filterPaths) {
            if (url.startsWith(path)) {
                return true;//当前访问地址是需要访问令牌的
            }
        }
        return false;//当前访问地址是不需要访问令牌的
    }

}
