package cn.itcast.dao.JavaBean.BooksService_JavaBean;



import cn.itcast.dao.JavaBean.Books_JavaBean.books_dao;
import cn.itcast.util.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BooksService_dao {
    //图书借阅，插入借阅记录
    public void bookOut(BooksService_vo bookSer) throws Exception {
        Connection conn = JDBCUtils.getConnection();
        String sql = "insert into bookOut(stuId,bookId,timeOut) values(?,?,?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, bookSer.getStuId());
        ps.setString(2, bookSer.getBookId());
        ps.setTimestamp(3, bookSer.getTime());
        ps.executeUpdate();

        books_dao dao = new books_dao();
        dao.bookCountDEC(bookSer.getBookId());

        JDBCUtils.free(null, ps, conn);
    }

    //图书归还，删除图书借阅记录
    public void bookOutDelete(BooksService_vo bookSer) throws Exception {
        Connection conn = JDBCUtils.getConnection();
        String sql = "delete from bookOut where stuId=? and bookId=? and timeOut=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, bookSer.getStuId());
        ps.setString(2, bookSer.getBookId());
        ps.setTimestamp(3, bookSer.getTime());
        ps.executeUpdate();
        JDBCUtils.free(null, ps, conn);
    }

    //图书归还，插入图书归还记录
    public void bookIn(BooksService_vo bookSer) throws Exception {
        Connection conn = JDBCUtils.getConnection();
        String sql = "insert into bookIn(stuId,bookId,timeIn) values(?,?,?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, bookSer.getStuId());
        ps.setString(2, bookSer.getBookId());
        ps.setTimestamp(3, bookSer.getTime());
        ps.executeUpdate();

        books_dao dao = new books_dao();
        dao.bookCountINC(bookSer.getBookId());

        JDBCUtils.free(null, ps, conn);
    }

    //删除学生时删除归还记录
    public void bookInDelete(String id) throws Exception {
        Connection conn = JDBCUtils.getConnection();
        String sql = "delete from bookIn where stuId=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, id);
        ps.executeUpdate();
        JDBCUtils.free(null, ps, conn);
    }

    //图书借阅记录搜索（全条件符合）
    public List<BooksService_vo> booksOutSelect(BooksService_vo bookSer) throws Exception {
        Connection conn = JDBCUtils.getConnection();
        List<BooksService_vo> booksOutList = new ArrayList<BooksService_vo>();
        String sql = "select * from bookOut where stuId=? and bookId=? and timeOut=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, bookSer.getStuId());
        ps.setString(2, bookSer.getBookId());
        ps.setTimestamp(3, bookSer.getTime());
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            BooksService_vo booksOut = new BooksService_vo();
            booksOut.setStuId(rs.getString("stuId"));
            booksOut.setBookId(rs.getString("bookId"));
            booksOut.setTime(rs.getTimestamp("timeOut"));
            booksOutList.add(booksOut);
        }
        JDBCUtils.free(rs, ps, conn);
        return booksOutList;
    }

    //根据学生id搜索图书借阅记录
    public List<BooksService_vo> booksOutSelectByStuId(String Id) throws Exception {
        Connection conn = JDBCUtils.getConnection();
        List<BooksService_vo> booksOutList = new ArrayList<BooksService_vo>();
        String sql = "select * from bookOut where stuId=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, Id);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            BooksService_vo booksOut = new BooksService_vo();
            booksOut.setStuId(rs.getString("stuId"));
            booksOut.setBookId(rs.getString("bookId"));
            booksOut.setTime(rs.getTimestamp("timeOut"));
            booksOutList.add(booksOut);
        }
        JDBCUtils.free(rs, ps, conn);
        return booksOutList;
    }

    //根据书籍id搜索借阅记录
    public List<BooksService_vo> booksOutSelectByBookId(String Id) throws Exception {
        Connection conn = JDBCUtils.getConnection();
        List<BooksService_vo> booksOutList = new ArrayList<BooksService_vo>();
        String sql = "select * from bookOut where bookId=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, Id);

        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            BooksService_vo booksOut = new BooksService_vo();
            booksOut.setStuId(rs.getString("stuId"));
            booksOut.setBookId(rs.getString("bookId"));
            booksOut.setTime(rs.getTimestamp("timeOut"));
            booksOutList.add(booksOut);
        }
        JDBCUtils.free(rs, ps, conn);
        return booksOutList;
    }

    //展示所有图书借阅记录
    public List<BooksService_vo> booksOutSelectAll() throws Exception {
        Connection conn = JDBCUtils.getConnection();
        List<BooksService_vo> booksOutList = new ArrayList<BooksService_vo>();
        String sql = "select * from bookOut";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            BooksService_vo booksOut = new BooksService_vo();
            booksOut.setStuId(rs.getString("stuId"));
            booksOut.setBookId(rs.getString("bookId"));
            booksOut.setTime(rs.getTimestamp("timeOut"));
            booksOutList.add(booksOut);
        }
        JDBCUtils.free(rs, ps, conn);
        return booksOutList;
    }

    //根据学号搜索归还记录
    public List<BooksService_vo> booksInSelectByStuId(String Id) throws Exception {
        Connection conn = JDBCUtils.getConnection();
        List<BooksService_vo> booksOutList = new ArrayList<BooksService_vo>();
        String sql = "select * from bookIn where stuId=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, Id);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            BooksService_vo booksOut = new BooksService_vo();
            booksOut.setStuId(rs.getString("stuId"));
            booksOut.setBookId(rs.getString("bookId"));
            booksOut.setTime(rs.getTimestamp("timeIn"));
            booksOutList.add(booksOut);
        }
        JDBCUtils.free(rs, ps, conn);
        return booksOutList;
    }

    //根据书号搜索归还记录
    public List<BooksService_vo> booksInSelectByBookId(String Id) throws Exception {
        Connection conn = JDBCUtils.getConnection();
        List<BooksService_vo> booksOutList = new ArrayList<BooksService_vo>();
        String sql = "select * from bookIn where bookId=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, Id);

        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            BooksService_vo booksOut = new BooksService_vo();
            booksOut.setStuId(rs.getString("stuId"));
            booksOut.setBookId(rs.getString("bookId"));
            booksOut.setTime(rs.getTimestamp("timeIn"));
            booksOutList.add(booksOut);
        }
        JDBCUtils.free(rs, ps, conn);
        return booksOutList;
    }

    //展示所有归还记录
    public List<BooksService_vo> booksInSelectAll() throws Exception {
        Connection conn = JDBCUtils.getConnection();
        List<BooksService_vo> booksOutList = new ArrayList<BooksService_vo>();
        String sql = "select * from bookIn";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            BooksService_vo booksOut = new BooksService_vo();
            booksOut.setStuId(rs.getString("stuId"));
            booksOut.setBookId(rs.getString("bookId"));
            booksOut.setTime(rs.getTimestamp("timeIn"));
            booksOutList.add(booksOut);
        }
        JDBCUtils.free(rs, ps, conn);
        return booksOutList;
    }


}
