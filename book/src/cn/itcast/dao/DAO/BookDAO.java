package cn.itcast.dao.DAO;


import cn.itcast.domain.Book;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import cn.itcast.util.JDBCUtils;
public class BookDAO {

    //借出
    public String bookout(Book book, String stuId) throws Exception{
        Connection conn = JDBCUtils.getConnection();
        String Sql1 = "select bookCount from book where bookId = ?";
        PreparedStatement ps1 = conn.prepareStatement(Sql1);
        ps1.setString(1,book.getBookId());
        ResultSet rs = ps1.executeQuery();
        int number = 0;
        if (rs.next()){   //获取当前图书数目
            number = rs.getInt("bookCount");
        }
        if(number>0){
            number--;
            //更新数量
            String Sql2 = "update book set bookCount = ? where bookId = ?";
            PreparedStatement ps2 = conn.prepareStatement(Sql2);
            ps2.setInt(1,number);
            ps2.setString(2,book.getBookId());
            ps2.executeUpdate();
            //添加记录
            String Sql3 = "insert into bookout(stuId,bookId,timeOut) values(?,?,?)";
            PreparedStatement ps3 = conn.prepareStatement(Sql3);
            ps3.setString(1,stuId);
            ps3.setString(2,book.getBookId());
            ps3.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
            ps3.executeUpdate();
            JDBCUtils.free(null,ps2,conn);
            return "success";
        }
        else{
            return  "fail";
        }
    }

    //还书
    public String bookin(Book book, String stuId ,Timestamp time) throws Exception{
        Connection conn = JDBCUtils.getConnection();
        //查删除借阅记录
        String Sql4 = "select * from bookout where stuId = ? and bookId = ? and timeOut = ?";
        PreparedStatement ps4 = conn.prepareStatement(Sql4);
        ps4.setString(1,stuId);
        ps4.setString(2,book.getBookId());
        ps4.setTimestamp(3,time);
        ResultSet rs = ps4.executeQuery();
        if (rs.next()){
            //删除借阅记录
            String Sql3 = "delete from bookout where stuId = ? and bookId = ? and timeOut = ?";
            PreparedStatement ps3 = conn.prepareStatement(Sql3);
            ps3.setString(1,stuId);
            ps3.setString(2,book.getBookId());
            ps3.setTimestamp(3,time);
            ps3.executeUpdate();

            String Sql1 = "select bookCount from book where bookId = ?";
            PreparedStatement ps1 = conn.prepareStatement(Sql1);
            ps1.setString(1,book.getBookId());
            ResultSet rs2 = ps1.executeQuery();
            int number = 0;
            if (rs.next()){
                number = rs2.getInt("bookCount");
            }
            number++;
            //更新数量
            String Sql2 = "update book set bookCount = ? where bookId = ?";
            PreparedStatement ps2 = conn.prepareStatement(Sql2);
            ps2.setInt(1,number);
            ps2.setString(2,book.getBookId());
            ps2.executeUpdate();

            //添加归还记录
            String Sql5 = "insert into bookin(stuId,bookId,timeIn) values(?,?,?)";
            PreparedStatement ps5 = conn.prepareStatement(Sql5);
            ps5.setString(1,stuId);
            ps5.setString(2,book.getBookId());
            ps5.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
            ps5.executeUpdate();
            JDBCUtils.free(null,ps5,conn);
            return "success";
        }
        else{
            return "fail";
        }

    }

    //查询所有图书
    public List<Book> bookAll() throws Exception{
        Connection conn = JDBCUtils.getConnection();
        List<Book> allBook = new ArrayList<Book>();
        String Sql = "select * from book";
        PreparedStatement ps = conn.prepareStatement(Sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            Book book = new Book();
            book.setBookId(rs.getString("bookId"));
            book.setBookName(rs.getString("bookName"));
            book.setBookClassify(rs.getString("bookClassify"));
            book.setBookCount(rs.getInt("bookCount"));
            allBook.add(book);
        }
        JDBCUtils.free(rs,ps,conn);
        return allBook;
    }

    //由ID查询信息
    public Book searchInf(String bookId) throws Exception{
        Connection conn = JDBCUtils.getConnection();
        Book book = null;
        String Sql = "select * from book where bookId = ?";
        PreparedStatement ps = conn.prepareStatement(Sql);
        ps.setString(1,bookId);
        ResultSet rs = ps.executeQuery();
        if (rs.next()){
            book = new Book();
            book.setBookId(rs.getString("bookId"));
            book.setBookName(rs.getString("bookName"));
            book.setBookClassify(rs.getString("bookClassify"));
            book.setBookCount(rs.getInt("bookCount"));
        }
        JDBCUtils.free(rs,ps,conn);
        return book;
    }

    //按条件查询图书
    public List<Book> search(String bookName, String bookClassify) throws Exception{
        Connection conn = JDBCUtils.getConnection();
        List<Book> books = new ArrayList<Book>();
        if(!Objects.equals(bookName, "")&&!Objects.equals(bookClassify, "")) {
            String Sql = "select * from book where bookName = ? and bookClassify = ?";
            PreparedStatement ps = conn.prepareStatement(Sql);
            ps.setString(1, bookName);
            ps.setString(2, bookClassify);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Book book = new Book();
                book.setBookId(rs.getString("bookId"));
                book.setBookName(rs.getString("bookName"));
                book.setBookClassify(rs.getString("bookClassify"));
                book.setBookCount(rs.getInt("bookCount"));
                books.add(book);
            }
            JDBCUtils.free(rs, ps, conn);
            return books;
        }
        else{
            String Sql = "select * from book where bookName = ? or bookClassify = ?";
            PreparedStatement ps = conn.prepareStatement(Sql);
            ps.setString(1, bookName);
            ps.setString(2, bookClassify);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Book book = new Book();
                book.setBookId(rs.getString("bookId"));
                book.setBookName(rs.getString("bookName"));
                book.setBookClassify(rs.getString("bookClassify"));
                book.setBookCount(rs.getInt("bookCount"));
                books.add(book);
            }
            JDBCUtils.free(rs, ps, conn);
            return books;
        }
    }
}
