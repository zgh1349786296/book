package cn.itcast.service;

import cn.itcast.domain.PageBean;
import cn.itcast.domain.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * 用户管理的业务接口
 */
public interface UserService {

    /**
     * 查询所有图书信息
     * @return
     */

     List<Book> findAllBook();
    /**
     * 查询所有图书操作信息
     * @return
     */
    List<BookEdit> findAllBookEdit() throws SQLException;
    /**
     * 管理员登录方法
     * @param manager
     * @return
     */
    Manager login(Manager manager);
    /**
     * 学生登录方法
     * @param student
     * @return
     */
    Student mlogin(Student student);

//添加图书，图书操作记录
    void addBook(Book book);
    void addBookEdit(BookEdit bookedit);
    /**
     * 根据id删除User
     * @param id
     */
//删除图书
    void deleteBook(String id);
    /**
     * 根据id查询User
     * @param id
     */
    //查找图书
    Book findBookById(String id);
    List<Book> findBookByName(String name);
    List<Book> findBookByClassify(String classify);
 //修改图书
    void updateBook(Book book);




    /**
     * 分页条件查询
     *   * @param currentPage
     *      * @param rows
     *      * @param condition
     *      * @return
     */
    PageBean<User> findUserByPage(String currentPage, String rows, Map<String, String[]> condition);



}
