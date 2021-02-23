package com.project.user.feign;

import com.project.entity.Result;
import com.project.user.pojo.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user")
public interface UserFeign {
    @GetMapping("/user/user/load/{username}")
    Result<User> findUserInfo(@PathVariable("username") String username);
}
