package com.project.oauth.config;

import com.project.oauth.util.UserJwt;
import com.project.user.feign.ResourceFeign;
import com.project.user.feign.UserFeign;
import com.project.user.pojo.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * security自定义授权认证类
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    ClientDetailsService clientDetailsService;
    @Autowired
    private UserFeign userFeign;
    @Autowired
    private ResourceFeign resourceFeign;

    /***
     * 自定义授权认证
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //取出身份，如果身份为空说明没有认证
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //没有认证统一采用httpbasic认证，httpbasic中存储了client_id和client_secret，开始认证client_id和client_secret
        if (authentication == null) {
            ClientDetails clientDetails = clientDetailsService.loadClientByClientId(username);
            if (clientDetails != null) {
                //秘钥
                String clientSecret = clientDetails.getClientSecret();
                //静态方式
                //return new User(username,new BCryptPasswordEncoder().encode(clientSecret), AuthorityUtils.commaSeparatedStringToAuthorityList(""));
                //数据库查找方式(commaSeparatedStringToAuthorityList(传入逗号分割的权限列表))
                return new User(username, clientSecret, AuthorityUtils.commaSeparatedStringToAuthorityList(""));
            }
        }
        if (StringUtils.isEmpty(username)) {
            return null;
        }
        //根据用户名查询用户信息
        com.project.user.pojo.User user = userFeign.findUserInfo(username).getResult();
        //根据用户名查询资源权限
        List<Resource> resourceList = resourceFeign.findByUsername(username).getResult();
        StringBuffer stringBuffer = new StringBuffer();
        for (Resource resource : resourceList) {
            stringBuffer.append(resource.getResKey()).append(",");
        }
        //逗号分割的权限列表
        String permissions = stringBuffer.substring(0, stringBuffer.length() - 1);
        //创建User对象
        UserJwt userDetails = new UserJwt(username, user.getPassword(), AuthorityUtils.commaSeparatedStringToAuthorityList(permissions));
        return userDetails;
    }
}
