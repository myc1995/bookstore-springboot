package com.myc.bookstore.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.myc.bookstore.entity.Book;
import com.myc.bookstore.utils.JDBCUtils;
import com.myc.bookstore.utils.Page;
import com.myc.bookstore.utils.PageAjaxResult;

public class BookDao
{

    public void list(Page<Book> page) throws SQLException
    {
        String countSql = "select count(*) from book";
        String sql = "select * from book limit ?,?";

        ResultSet rs = null;
        ResultSet countRs = null;
        Connection conn = null;
        try
        {
            conn = JDBCUtils.getConnection();
            rs = JDBCUtils.executeQuery(conn, sql, (page.getTargetPage() - 1) * page.getSize(), page.getSize());
            List<Book> bookList = JDBCUtils.packEntityList(Book.class, rs);
            page.setItems(bookList);

            countRs = JDBCUtils.executeQuery(conn, countSql);
            countRs.next();
            int totalCount = countRs.getInt(1);
            int totalPage = (totalCount + page.getSize() - 1) / page.getSize();
            page.setTotalPage(totalPage);

        }
        finally
        {
            JDBCUtils.closeResultSetAndStatement(rs);
            JDBCUtils.closeAll(countRs);
        }
    }

    public void list(Page<Book> page, Integer categoryId) throws SQLException
    {
        String countSql = "select count(*) from book where categoryId=?";
        String sql = "select * from book where categoryId=? limit ?,?";

        ResultSet rs = null;
        ResultSet countRs = null;
        Connection conn = null;
        try
        {
            conn = JDBCUtils.getConnection();
            rs = JDBCUtils.executeQuery(conn, sql, categoryId, (page.getTargetPage() - 1) * page.getSize(),
                    page.getSize());
            List<Book> bookList = JDBCUtils.packEntityList(Book.class, rs);
            page.setItems(bookList);

            countRs = JDBCUtils.executeQuery(conn, countSql, categoryId);
            countRs.next();
            int totalCount = countRs.getInt(1);
            int totalPage = (totalCount + page.getSize() - 1) / page.getSize();
            page.setTotalPage(totalPage);

        }
        finally
        {
            JDBCUtils.closeResultSetAndStatement(rs);
            JDBCUtils.closeAll(countRs);
        }
    }

    public void list(Page<Book> page, String searchText) throws SQLException
    {
        String sql = "select * from book where name like ?  or author like ? limit ?,?";
        String countSql = "select count(*) from book where name like ? or author like ?";

        ResultSet rs = null;
        ResultSet countRs = null;
        Connection conn = null;
        try
        {
            conn = JDBCUtils.getConnection();
            rs = JDBCUtils.executeQuery(conn, sql, searchText, searchText, (page.getTargetPage() - 1) * page.getSize(),
                    page.getSize());
            List<Book> bookList = JDBCUtils.packEntityList(Book.class, rs);
            page.setItems(bookList);

            countRs = JDBCUtils.executeQuery(conn, countSql, searchText, searchText);
            countRs.next();
            int totalCount = countRs.getInt(1);
            int totalPage = (totalCount + page.getSize() - 1) / page.getSize();
            page.setTotalPage(totalPage);

        }
        finally
        {
            JDBCUtils.closeResultSetAndStatement(rs);
            JDBCUtils.closeAll(countRs);
        }
    }

    public Book findById(int bookId) throws SQLException
    {
        String sql = "select * from book where id=?";
        ResultSet rs = null;
        try
        {
            rs = JDBCUtils.executeQuery(sql, bookId);
            Book book = JDBCUtils.packEntity(Book.class, rs);
            return book;
        }
        finally
        {
            JDBCUtils.closeAll(rs);
        }
    }

    public void list(PageAjaxResult pageAjaxResult) throws SQLException
    {
        String sql = "select * from book where isDeleted=false limit ?,?";
        String countSql = "select count(*) from book where isDeleted=false";

        Connection conn = null;
        ResultSet rs = null;
        ResultSet countRs = null;

        try
        {
            conn = JDBCUtils.getConnection();
            // 查询目标页数据
            rs = JDBCUtils.executeQuery(conn, sql, (pageAjaxResult.getTargetPage() - 1) * pageAjaxResult.getSize(),
                    pageAjaxResult.getSize());
            pageAjaxResult.setRows(JDBCUtils.packEntityList(Book.class, rs));

            // 查询总数量，注意不是总页数
            countRs = JDBCUtils.executeQuery(conn, countSql);
            countRs.next();
            int count = countRs.getInt(1);
            pageAjaxResult.setTotal(count);

        }
        finally
        {
            JDBCUtils.closeResultSetAndStatement(rs);
            JDBCUtils.closeAll(countRs);
        }

    }

    public void list(PageAjaxResult pageAjaxResult, String searchText) throws SQLException
    {
        String sql = "select * from book where isDeleted=false and ( name like ? or author like ?) limit ?,?";
        String countSql = "select count(*) from book  where isDeleted=false and ( name like ? or author like ?)";

        Connection conn = null;
        ResultSet rs = null;
        ResultSet countRs = null;

        try
        {
            conn = JDBCUtils.getConnection();
            // 查询目标页数据
            rs = JDBCUtils.executeQuery(conn, sql, searchText, searchText,
                    (pageAjaxResult.getTargetPage() - 1) * pageAjaxResult.getSize(), pageAjaxResult.getSize());
            pageAjaxResult.setRows(JDBCUtils.packEntityList(Book.class, rs));

            // 查询总数量，注意不是总页数
            countRs = JDBCUtils.executeQuery(conn, countSql, searchText, searchText);
            countRs.next();
            int count = countRs.getInt(1);
            pageAjaxResult.setTotal(count);
        }
        finally
        {
            JDBCUtils.closeResultSetAndStatement(rs);
            JDBCUtils.closeAll(countRs);
        }
    }

    public void add(Book book) throws SQLException
    {
        String sql = "insert into book (categoryId,name,price,author,press,publishDate,wordCount,pageCount,isbn,coverImage,description) values(?,?,?,?,?,?,?,?,?,?,?)";
        JDBCUtils.executeUpdate(sql, book.getCategoryId(), book.getName(), book.getPrice(), book.getAuthor(),
                book.getPress(), book.getPublishDate(), book.getWordCount(), book.getPageCount(), book.getIsbn(),
                book.getCoverImage(), book.getDescription());
    }

    public void update(Book book) throws SQLException
    {
        String sql = "update book set categoryId=?,name=?,price=?,author=?,publishDate=?,press=?,wordCount=?,pageCount=?,isbn=?,coverImage=?,description=? where id=?";
        JDBCUtils.executeUpdate(sql, book.getCategoryId(), book.getName(), book.getPrice(), book.getAuthor(),
                book.getPublishDate(), book.getPress(), book.getWordCount(), book.getPageCount(), book.getIsbn(),
                book.getCoverImage(), book.getDescription(), book.getId());
    }

    public void deleteById(int id) throws SQLException
    {
        String sql = "delete from book where id=?";
        JDBCUtils.executeUpdate(sql, id);
    }
}
