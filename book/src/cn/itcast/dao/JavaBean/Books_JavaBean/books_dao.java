package cn.itcast.dao.JavaBean.Books_JavaBean;

import cn.itcast.util.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class books_dao {
    //所有图书信息展示
    public List<books_vo> QueryAll() throws Exception {
        Connection conn = JDBCUtils.getConnection();
        List<books_vo> booksList = new ArrayList<books_vo>();
        String sql = "select * from book";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            books_vo books = new books_vo();
            books.setBookId(rs.getString("bookId"));
            books.setBookName(rs.getString("bookName"));
            books.setBookClassify(rs.getString("bookClassify"));
            books.setBookCount(rs.getInt("bookCount"));
            booksList.add(books);
        }
        JDBCUtils.free(rs, ps, conn);
        return booksList;
    }

    //根据id查找图书
    public List<books_vo> findBooksById(String bookId) throws Exception {
        Connection conn = JDBCUtils.getConnection();
        List<books_vo> bookList = new ArrayList<books_vo>();
        String sql = "select * from book where bookId=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, bookId);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            books_vo book = new books_vo();
            book.setBookId(rs.getString("bookId"));
            book.setBookName(rs.getString("bookName"));
            book.setBookClassify(rs.getString("bookClassify"));
            book.setBookCount(rs.getInt("bookCount"));
            bookList.add(book);
        }
        JDBCUtils.free(rs, ps, conn);
        return bookList;
    }

    //根据名字查找图书
    public List<books_vo> findBooksByName(String bookName) throws Exception {
        Connection conn = JDBCUtils.getConnection();
        List<books_vo> bookList = new ArrayList<books_vo>();
        String sql = "select * from book where bookName=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, bookName);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            books_vo book = new books_vo();
            book.setBookId(rs.getString("bookId"));
            book.setBookName(rs.getString("bookName"));
            book.setBookClassify(rs.getString("bookClassify"));
            book.setBookCount(rs.getInt("bookCount"));
            bookList.add(book);
        }
        JDBCUtils.free(rs, ps, conn);
        return bookList;
    }

    //根据类型查找图书
    public List<books_vo> findBooksByClassify(String Classify) throws Exception {
        Connection conn = JDBCUtils.getConnection();
        List<books_vo> bookList = new ArrayList<books_vo>();
        String sql = "select * from book where bookClassify=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, Classify);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            books_vo book = new books_vo();
            book.setBookId(rs.getString("bookId"));
            book.setBookName(rs.getString("bookName"));
            book.setBookClassify(rs.getString("bookClassify"));
            book.setBookCount(rs.getInt("bookCount"));
            bookList.add(book);
        }
        JDBCUtils.free(rs, ps, conn);
        return bookList;
    }

    //图书减一操作
    public void bookCountDEC(String id) throws Exception {
        Connection conn = JDBCUtils.getConnection();
        String sql = "update book set bookCount=bookCount-1 where bookId=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, id);
        ps.executeUpdate();
        JDBCUtils.free(null, ps, conn);
    }

    //图书加一操作
    public void bookCountINC(String id) throws Exception {
        Connection conn = JDBCUtils.getConnection();
        String sql = "update book set bookCount=bookCount+1 where bookId=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, id);
        ps.executeUpdate();
        JDBCUtils.free(null, ps, conn);
    }
}
