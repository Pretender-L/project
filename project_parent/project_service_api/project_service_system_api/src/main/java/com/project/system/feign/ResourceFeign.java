package com.project.system.feign;

import com.project.common.entity.Result;
import com.project.oauth.pojo.Resource;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Set;

@FeignClient(name = "system")
public interface ResourceFeign {
    @GetMapping(value = "/system/resource/findByAdminId/{adminId}")
    Result<Set<Resource>> findByAdminId(@PathVariable Integer adminId);
}
