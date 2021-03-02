package com.project.system.dao;

import com.project.system.pojo.Admin;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

public interface AdminMapper extends Mapper<Admin> {
    @Select("SELECT * FROM `tb_admin` WHERE login_name=#{loginName}")
    Admin findByLoginName(String loginName);
}
