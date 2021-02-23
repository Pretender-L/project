package com.project.goods.dao;

import com.project.goods.pojo.Spec;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface SpecMapper extends Mapper<Spec> {

    @Select("SELECT `name`,`options` FROM `tb_spec` WHERE template_id in(SELECT DISTINCT(template_id) FROM tb_category WHERE name = #{categoryName})")
    List<Map> findSpecListByCategoryName(String categoryName);

}
