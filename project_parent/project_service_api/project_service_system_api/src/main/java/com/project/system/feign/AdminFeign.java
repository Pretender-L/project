package com.project.system.feign;

import com.project.common.entity.Result;
import com.project.system.pojo.Admin;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "system")
public interface AdminFeign {
    /***
     * feign返回不确定类型需要加泛型
     * @param loginName
     * @return
     */
    @PostMapping(value = "/system/admin/findByLoginName/{loginName}")
    Result<Admin> findByLoginName(@PathVariable("loginName") String loginName);
}
