package cn.itcast.dao;

import cn.itcast.domain.Book;
import cn.itcast.domain.*;
import cn.itcast.domain.User;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface UserDao {

    List<Book> findAllBook();

    List<BookEdit> findAllBookEdit() throws SQLException;

    Manager findManagerByUsernameAndPassword(String username, String password);

    Student findStudentByUsernameAndPassword(String username, String password);

    void add(User user);

    void addBook(Book book);

    void addBookEdit(BookEdit bookedit);

    void delete(int i);

    void deleteBook(String id);

    User finById(int id);

    Book findBookById(String id);

    void update(User user);

    void updateBook(Book book);

    int findTotalCount(Map<String, String[]> condition);

    List<User> findByPage(int star, int rows, Map<String, String[]> condition);

    List<Book> findBookByName(String name);

    List<Book> findBookByClassify(String classify);
}
