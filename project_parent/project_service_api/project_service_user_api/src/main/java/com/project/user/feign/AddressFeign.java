package com.project.user.feign;

import com.project.common.entity.Result;
import com.project.user.pojo.Address;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "user")
public interface AddressFeign {
    @GetMapping(value = "/user/address/list")
    Result<List<Address>> list();
}
