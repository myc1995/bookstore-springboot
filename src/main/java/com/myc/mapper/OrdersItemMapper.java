package com.myc.mapper;

import com.myc.entities.OrdersItem;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;

public interface OrdersItemMapper {
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into ordersItem values(#{ordersItem})")
    public void add(OrdersItem ordersItem);
}
