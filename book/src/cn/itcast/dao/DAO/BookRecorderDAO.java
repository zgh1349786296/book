package cn.itcast.dao.DAO;

import cn.itcast.domain.BookRecord;
import cn.itcast.util.JDBCUtils;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BookRecorderDAO {

    //查询已归还的图书
    public List<BookRecord> bookInAlready(String stuId) throws Exception{
        Connection conn = JDBCUtils.getConnection();
        List<BookRecord> BookRecords = new ArrayList<BookRecord>();
        String Sql = "select * from bookin where stuId = ?";
        PreparedStatement ps = conn.prepareStatement(Sql);
        ps.setString(1,stuId);
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            BookRecord bookrecord = new BookRecord();
            bookrecord.setStuId(rs.getString("stuId"));
            bookrecord.setBookId(rs.getString("bookId"));
            bookrecord.setTime(rs.getTimestamp("timeIn"));
            BookRecords.add(bookrecord);
        }
        JDBCUtils.free(rs,ps,conn);
        return BookRecords;
    }

    //查询所有未归还的图书
    public List<BookRecord> bookOut(String stuId) throws Exception{
        Connection conn = JDBCUtils.getConnection();
        List<BookRecord> BookRecords = new ArrayList<BookRecord>();
        String Sql = "select * from bookout where stuId = ?";
        PreparedStatement ps = conn.prepareStatement(Sql);
        ps.setString(1,stuId);
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            BookRecord bookrecord = new BookRecord();
            bookrecord.setStuId(rs.getString("stuId"));
            bookrecord.setBookId(rs.getString("bookId"));
            bookrecord.setTime(rs.getTimestamp("timeOut"));
            BookRecords.add(bookrecord);
        }
        JDBCUtils.free(rs,ps,conn);
        return BookRecords;
    }

    public int bookOutCount(String stuId) throws Exception{
        Connection conn = JDBCUtils.getConnection();
        String Sql = "select count(*) from bookout where stuId = ? ";
        int count=0;
        PreparedStatement ps = conn.prepareStatement(Sql);
        ps.setString(1,stuId);
        ResultSet rs = ps.executeQuery();
        rs.next();
        count=rs.getInt(1);
        return count;
    }

    //按条件查询未归还的图书
    public List<BookRecord> bookOutSearch(String stuId, String bookName) throws Exception{
        Connection conn = JDBCUtils.getConnection();
        List<BookRecord> BookRecords = new ArrayList<BookRecord>();
        String Sql = "select * from bookout where stuId = ? and bookName = ?";
        PreparedStatement ps = conn.prepareStatement(Sql);
        ps.setString(1,stuId);
        ps.setString(2,bookName);
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            BookRecord bookrecord = new BookRecord();
            bookrecord.setStuId(rs.getString("stuId"));
            bookrecord.setBookId(rs.getString("bookId"));
            bookrecord.setTime(rs.getTimestamp("timeOut"));
            BookRecords.add(bookrecord);
        }
        JDBCUtils.free(rs,ps,conn);
        return BookRecords;
    }
}
