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
     * 查询所有用户信息
     * @return
     */
     List<User> findAll();
     List<Book> findAllBook();
    List<BookEdit> findAllBookEdit() throws SQLException;
    /**
     * 管理员登录方法
     * @param manager
     * @return
     */
    Manager login(Manager manager);
    Student mlogin(Student student);
    /**
     * 保存User
     * @param user
     */
    void addUser(User user);
    void addBook(Book book);
    void addBookEdit(BookEdit bookedit);
    /**
     * 根据id删除User
     * @param id
     */
    void deleteUser(String id);
    void deleteBook(String id);
    /**
     * 根据id查询User
     * @param id
     */
    User findUserById(String id);
    Book findBookById(String id);
    List<Book> findBookByName(String name);
    List<Book> findBookByClassify(String classify);
    /**
     * 修改用户信息
     * @param user
     */
    void updateUser(User user);
    void updateBook(Book book);
    /**
     * 用户登录
     * @param user
     */


    /**
     * 批量删除用户
     * @param ids
     */
    void delSelectedUser(String[] ids);
    /**
     * 分页条件查询
     *   * @param currentPage
     *      * @param rows
     *      * @param condition
     *      * @return
     */
    PageBean<User> findUserByPage(String currentPage, String rows, Map<String, String[]> condition);



}
