package com.project.system.feign;

import com.project.common.entity.Result;
import com.project.oauth.pojo.Role;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "system")
public interface RoleFeign {
    @GetMapping(value = "/system/role/findByAdminId/{adminId}")
    Result<List<Role>> findByAdminId(@PathVariable Integer adminId);
}
