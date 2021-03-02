package com.project.oauth.config;

import com.project.system.feign.AdminFeign;
import com.project.system.feign.ResourceFeign;
import com.project.system.feign.RoleFeign;
import com.project.system.pojo.Admin;
import com.project.system.pojo.Resource;
import com.project.system.pojo.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Set;

/**
 * security自定义授权认证类
 * security通过过滤器拦截的，请求必须携带username,password才会走过滤器（登陆url需要判断是否携带参数）
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private AdminFeign adminFeign;
    @Autowired
    private ResourceFeign resourceFeign;
    @Autowired
    private RoleFeign roleFeign;

    /****
     * 自定义授权认证
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (StringUtils.isEmpty(username)) {
            return null;
        }
        //根据用户名查询用户信息
        Admin admin = (Admin) adminFeign.findByLoginName(username).getResult();
        //根据用户id查询角色
        List<Role> roleList = roleFeign.findByAdminId(admin.getId()).getResult();
        StringBuilder stringBuilder = new StringBuilder();
        for (Role role : roleList) {
            stringBuilder.append("ROLE_" + role.getName() + ",");
        }
        //根据用户id查询资源权限
        Set<Resource> resourceSet = (Set<Resource>) resourceFeign.findByAdminId(admin.getId()).getResult();
        for (Resource resource : resourceSet) {
            stringBuilder.append(resource.getResKey() + ",");
        }
        //逗号分割的权限列表
        String permissions = stringBuilder.substring(0, stringBuilder.length() - 1).toString();
        System.out.println(username + ":" + permissions);
        //创建User对象
        // 1. commaSeparatedStringToAuthorityList放入角色时需要加前缀ROLE_，而在controller使用时不需要加ROLE_前缀 例：ROLE_USER
        // 2. 放入的是权限时，不能加ROLE_前缀，hasAuthority与放入的权限名称对应即可
        User userDetails = new User(username, admin.getPassword(), AuthorityUtils.commaSeparatedStringToAuthorityList(permissions));
        return userDetails;
    }
}
