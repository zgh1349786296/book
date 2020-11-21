package cn.itcast.service.impl;

import cn.itcast.dao.UserDao;
import cn.itcast.dao.impl.UserDaoImpl;
import cn.itcast.domain.PageBean;
import cn.itcast.domain.*;
import cn.itcast.service.UserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class UserServiceImpl implements UserService {
    private UserDao dao = new UserDaoImpl();


//服务层实现
    @Override
    public List<Book> findAllBook() {
        return dao.findAllBook();
    }

    @Override
    public List<BookEdit> findAllBookEdit() throws SQLException {
        return dao.findAllBookEdit();
    }


    @Override
    public Manager login(Manager manager) {
        return dao.findManagerByUsernameAndPassword(manager.getManId(),manager.getManPassword());
    }
    @Override
    public Student mlogin(Student student) {
        return dao.findStudentByUsernameAndPassword(student.getStuId(),student.getStuPassword());
    }


    @Override
    public void addBook(Book book) {
        dao.addBook(book);
    }

    @Override
    public void addBookEdit(BookEdit bookedit) {
        dao.addBookEdit(bookedit);
    }



    @Override
    public void deleteBook(String id) {
        dao.deleteBook(id);
    }


    @Override
    public Book findBookById(String id) {
        return dao.findBookById(id);
    }

    @Override
    public List<Book> findBookByName(String name) {
        return dao.findBookByName(name);
    }

    @Override
    public List<Book> findBookByClassify(String classify) {
        return dao.findBookByClassify(classify);
    }




    @Override
    public void updateBook(Book book) {
        dao.updateBook(book);
    }



    public String CookieMan(Cookie[] cookies){
        if (cookies == null) {
            return null;

        }
        for (Cookie cookie : cookies) {
            if(cookie.getName().equalsIgnoreCase("manId")){  //检测管理员cookie存在否
                return cookie.getValue();
            }
        }
        return null;
    }

    public String CookieStu(Cookie[] cookies){  //检测学生cookie存在否
            if (cookies == null) {
                return null;

            }
            for (Cookie cookie : cookies) {
                if(cookie.getName().equalsIgnoreCase("stuId")){
                    return cookie.getValue();
                }
            }
            return null;
        }


    @Override
    public PageBean<User> findUserByPage(String _currentPage, String _rows, Map<String, String[]> condition) {
      //强制转换
       int currentPage = Integer.parseInt(_currentPage);
        int rows = Integer.parseInt(_rows);
        //创建空对象
        int totalCount = dao.findTotalCount(condition);
        if(currentPage<=0){
            currentPage=1;
        }
        int totalPage= (totalCount%rows) == 0?  totalCount/rows:(totalCount/rows)+1;
        if(currentPage>=totalPage){
            currentPage=totalPage;
        }
        PageBean<User> pb = new PageBean<>();
        //设置参数
       pb.setCurrentPage(currentPage);
       pb.setRows(rows);
    //调用dao查询总记录
       pb.setTotalCount(totalCount);
       //调用dao查询list集合
        int star  = (currentPage-1)*rows;
        List<User> list = dao.findByPage(star,rows,condition);
        pb.setList(list);
         pb.setTotalPage(totalPage);

        return pb;
    }


}
