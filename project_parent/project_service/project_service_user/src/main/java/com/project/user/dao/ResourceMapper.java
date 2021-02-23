package com.project.user.dao;

import com.project.user.pojo.Resource;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface ResourceMapper extends Mapper<Resource> {

    @Select("SELECT r.* FROM tb_user u LEFT JOIN tb_user_resource ur on u.username = ur.user_username LEFT JOIN tb_resource r on ur.resource_id=r.id WHERE u.username = #{username}")
    List<Resource> findByUsername(String username);

}
