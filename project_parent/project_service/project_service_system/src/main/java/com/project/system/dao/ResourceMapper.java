package com.project.system.dao;

import com.project.system.pojo.Resource;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface ResourceMapper extends Mapper<Resource> {
    @Select("SELECT r.* FROM tb_role_resource rr LEFT JOIN tb_resource r on rr.resource_id=r.id WHERE rr.role_id=#{roleId}")
    List<Resource> findByRoleId(Integer roleId);
}
