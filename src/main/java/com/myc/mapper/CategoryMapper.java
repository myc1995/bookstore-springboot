package com.myc.mapper;

import com.myc.bean.Category;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CategoryMapper {
    @Select("select * from category")
    public List<Category> list();
}
