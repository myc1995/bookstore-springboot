package com.myc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.myc.entity.Category;
import com.myc.utils.JDBCUtils;

public class CategoryDao
{

    public List<Category> list() throws SQLException
    {
        String sql = "select * from category";
        ResultSet rs = null;
        try
        {
            rs = JDBCUtils.executeQuery(sql);
            return JDBCUtils.packEntityList(Category.class, rs);
        }
        finally
        {
            JDBCUtils.closeAll(rs);
        }
    }
}
