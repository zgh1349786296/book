package cn.itcast.dao;

import cn.itcast.domain.Book;
import cn.itcast.domain.*;
import cn.itcast.domain.User;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface UserDao {   //dao层接口
        //查询书籍库存
    List<Book> findAllBook();
        //查询书籍操作记录
    List<BookEdit> findAllBookEdit() throws SQLException;
        //查询管理员账号密码
    Manager findManagerByUsernameAndPassword(String username, String password);
        //查询学生账号密码
    Student findStudentByUsernameAndPassword(String username, String password);

        //添加图书
    void addBook(Book book);
        //添加操作记录
    void addBookEdit(BookEdit bookedit);

        //删除图书
    void deleteBook(String id);

        //通过图书ID 查询
    Book findBookById(String id);

        //修改图书
    void updateBook(Book book);

    int findTotalCount(Map<String, String[]> condition);

    List<User> findByPage(int star, int rows, Map<String, String[]> condition);
        //通过书名查询
    List<Book> findBookByName(String name);
        //通过类别查询
    List<Book> findBookByClassify(String classify);
}
