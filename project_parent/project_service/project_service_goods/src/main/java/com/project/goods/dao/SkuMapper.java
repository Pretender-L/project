package com.project.goods.dao;

import com.project.goods.pojo.Sku;
import com.project.order.pojo.OrderItem;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;

public interface SkuMapper extends Mapper<Sku> {

    /***
     * 扣减库存增强销量
     * @param orderItem
     * @return
     */
    @Update("update tb_sku set num=num-#{num},sale_num=sale_num+#{num} where id=#{skuId} and num>=#{num}")
    int decrCount(OrderItem orderItem);

    /***
     * 增加库存减少销量(回滚库存)
     * @param skuId
     * @param num
     */
    @Update("update tb_sku set num=num+#{num},sale_num=sale_num-#{num} where id=#{skuId}")
    void resumeStockNum(@Param("skuId") String skuId, @Param("num") Integer num);

}
