package com.myc.mapper;

import com.myc.entities.Orders;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

public interface OrdersMapper {
    @Select("select count(*) from orders where status='未支付' and userId=#{userId}")
    public int findUnpaidOrdersCount(Integer userId);

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into orders values(#{orders})")
    public void add(Orders orders);
}
