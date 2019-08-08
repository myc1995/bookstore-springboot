package com.myc.mapper;

import com.myc.entities.Book;
import com.myc.utils.Page;
import com.myc.utils.PageAjaxResult;
import org.apache.ibatis.annotations.*;

public interface BookMapper {
    public void list(Page<Book> page);

    public void list(Page<Book> page, Integer categoryId);

    public void list(Page<Book> page, String searchText);

    public void list(PageAjaxResult pageAjaxResult);

    public void list(PageAjaxResult pageAjaxResult, String searchText);

    @Select("select * from book where id=#{bookId}")
    public Book findById(int bookId);

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into book values(#{book})")
    public void add(Book book);

    @Update("update book set categoryId=#{categoryId},name=#{name},price=#{price},author=#{author},publishDate=#{publishDate},press=#{press},wordCount=#{wordCount},pageCount=#{pageCount},isbn=#{isbn},coverImage=#{coverImage},description=#{description} where id=#{id}")
    public void update(Book book);

    @Delete("delete from book where id=#{id}")
    public void deleteById(int id);
}
