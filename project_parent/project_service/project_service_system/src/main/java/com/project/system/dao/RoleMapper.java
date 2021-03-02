package com.project.oauth.dao;

import com.project.oauth.pojo.Role;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface RoleMapper extends Mapper<Role> {
    @Select("SELECT r.* FROM tb_admin_role ar LEFT JOIN tb_role r ON ar.role_id=r.id WHERE ar.admin_id = #{adminId}")
    List<Role> findByAdminId(Integer adminId);
}
