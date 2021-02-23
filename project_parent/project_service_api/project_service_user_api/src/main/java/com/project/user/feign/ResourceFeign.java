package com.project.user.feign;

import com.project.entity.Result;
import com.project.user.pojo.Resource;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "user")
public interface ResourceFeign {
    @GetMapping(value = "/user/resource/findByUsername/{username}")
    Result<List<Resource>> findByUsername(@PathVariable String username);
}
