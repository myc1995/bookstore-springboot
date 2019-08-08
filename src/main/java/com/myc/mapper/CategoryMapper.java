package com.myc.mapper;

import com.myc.entities.Category;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CategoryMapper {
    @Select("select * from category")
    public List<Category> list();
}
