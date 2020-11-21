package cn.itcast.dao.JavaBean.BooksService_JavaBean;

import java.sql.Timestamp;

public class BooksService_vo {
    private String stuId;
    private String bookId;
    private Timestamp time;

    public BooksService_vo(){

    }

    public BooksService_vo(String stuId,String bookId,Timestamp time){
        this.stuId=stuId;
        this.bookId=bookId;
        this.time=time;
    }

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }
}
