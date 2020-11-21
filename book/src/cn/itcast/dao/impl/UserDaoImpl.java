package cn.itcast.dao.impl;

import cn.itcast.dao.UserDao;
import cn.itcast.domain.Book;
import cn.itcast.domain.*;
import cn.itcast.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class UserDaoImpl implements UserDao {
    private  JdbcTemplate template=new JdbcTemplate(JDBCUtils.getDataSource());
    String closeForeignKey = "SET @ORIG_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0";
    Integer close = template.update(closeForeignKey);
    @Override
    public List<Book> findAllBook() {
//使用JDBC操作数据库...
        //1.定义sql
        String sql = "select * from book";
        List<Book> books = template.query(sql, new BeanPropertyRowMapper<Book>(Book.class));
        return books;
    }

    @Override
    public List<BookEdit> findAllBookEdit() throws SQLException {
        String sql = "select * from bookedit order by operateTime desc limit 5";
        List<BookEdit> bookedits = new ArrayList<>();
        Connection con= JDBCUtils.getConnection();
        PreparedStatement ps=con.prepareStatement(sql);
        ResultSet rs=ps.executeQuery();
        while (rs.next()){
            BookEdit bookEdit =new BookEdit();
            bookEdit.setBookId(rs.getString("bookId"));
            bookEdit.setManId(rs.getString("manId"));
            Timestamp t = rs.getTimestamp("operateTime");
            bookEdit.setOperateTime(t);
            bookEdit.setOperate(rs.getString("operate"));
            bookEdit.setOperateCount(rs.getInt("operateCount"));
            bookedits.add(bookEdit);
        }
        return bookedits;
    }

    @Override
    public Manager findManagerByUsernameAndPassword(String username, String password) {
        try {
            String sql = "select * from maninf where manId = ? and manPassword = ?";
            Manager manager = template.queryForObject(sql, new BeanPropertyRowMapper<Manager>(Manager.class), username, password);
            return manager;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
    @Override
    public Student findStudentByUsernameAndPassword(String username, String password) {
        try {
            String sql = "select * from stuinf where stuId = ? and Stupassword = ?";
            Student student = template.queryForObject(sql, new BeanPropertyRowMapper<Student>(Student.class), username, password);
            return student;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public void add(User user) {
        //1.定义sql
        String sql = "insert into user values(?,?,?,?,?,?,?,?,?)";
        //2.执行sql
        template.update(sql, user.getId(),user.getName(), user.getGender(), user.getAge(), user.getAddress(), user.getQq(), user.getEmail(),user.getUsername(),user.getPassword());
    }
    @Override
    public void addBook(Book book) {
        String sql = "insert into book values(?,?,?,?)";
        template.update(sql, book.getBookId(),book.getBookName(),book.getBookClassify(),book.getBookCount());
    }

    @Override
    public void addBookEdit(BookEdit bookedit) {

        String sql = "insert into bookedit values(?,?,?,?,?)";
        Timestamp t = new Timestamp(bookedit.getOperateTime().getTime());
        template.update(sql, bookedit.getBookId(),bookedit.getManId(),t,bookedit.getOperate(),bookedit.getOperateCount());

    }

    @Override
    public void delete(int id) {
        //1.定义sql
        String sql = "delete from user where id = ?";
        //2.执行sql
        template.update(sql, id);
    }

    @Override
    public void deleteBook(String id) {

        //1.定义sql
        String sql = "delete from book where bookId = ?";
        //2.执行sql
        template.update(sql, id);
    }

    @Override
    public User finById(int id) {
        String sql = "select * from user where id = ?";
        return template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), id);
    }

    @Override
    public Book findBookById(String id) {
        try{
            String sql = "select * from book where bookId = ?";
            return template.queryForObject(sql, new BeanPropertyRowMapper<Book>(Book.class), id);
        }
        catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public void update(User user) {
        String sql = "update user set name = ?,gender = ? ,age = ? , address = ? , qq = ?, email = ? where id = ?";
        template.update(sql, user.getName(), user.getGender(), user.getAge(), user.getAddress(), user.getQq(), user.getEmail(), user.getId());

    }

    @Override
    public void updateBook(Book book) {
        String sql = "update book set bookName = ?,bookClassify = ?,bookCount= ? where bookId = ?";
        template.update(sql,book.getBookName(),book.getBookClassify(),book.getBookCount(),book.getBookId());
    }

    @Override
    public int findTotalCount(Map<String, String[]> condition) {
        //定义初始化模板
       String sql = "select count(*) from user where 1= 1";
       StringBuilder sb = new StringBuilder(sql);
       //遍历map
        Set<String> keySet=condition.keySet();
        //定义参数集合
        List<Object> params = new ArrayList<Object>();
        for(String key :keySet){
            //排出分页的条件参数
            if("currentPage".equals(key)||"rows".equals(key)){
                continue;
            }
            //获取value
            String value =condition.get(key)[0];
            //判断value 有值否
            if(value !=null && !"".equals(value)){
                sb.append(" and "+key+" like ? ");
                params.add("%"+value+"%");//加条件的值
            }
        }
        System.out.println(sb.toString());
        System.out.println(params);
        return template.queryForObject(sb.toString(),Integer.class,params.toArray());
    }

    @Override
    public List<User> findByPage(int star, int rows, Map<String, String[]> condition) {
       String sql = "select * from user where 1=1";
        StringBuilder sb = new StringBuilder(sql);
        //遍历map
        Set<String> keySet=condition.keySet();
        //定义参数集合
        List<Object> params = new ArrayList<Object>();
        for(String key :keySet){
            //排出分页的条件参数
            if("currentPage".equals(key)||"rows".equals(key)){
                continue;
            }
            //获取value
            String value =condition.get(key)[0];
            //判断value 有值否
            if(value !=null && !"".equals(value)){
                sb.append(" and "+key+" like ? ");
                params.add("%"+value+"%");//加条件的值
            }
        }
        //添加分页查询
        sb.append(" limit ?,?");
        //添加分页查询参数
        params.add(star);
        params.add(rows);
        sql=sb.toString();
        System.out.println(sql);
        System.out.println(params);
        return template.query(sql,new BeanPropertyRowMapper<User>(User.class),params.toArray());
    }

    @Override
    public List<Book> findBookByName(String name) {
        try {
            String sql = "select * from book where bookName =?";
            List<Book> books = template.query(sql, new BeanPropertyRowMapper<Book>(Book.class), name);
            return books;
        }catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public List<Book> findBookByClassify(String classify) {
        try {
        String sql = "select * from book where bookClassify =?";
        List<Book> books = template.query(sql, new BeanPropertyRowMapper<Book>(Book.class),classify);
        return books;
        }catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }


}
