package cn.itcast.dao.JavaBean.Books_JavaBean;

public class books_vo {
    private String bookId;
    private String bookName;
    private String bookClassify;
    private int bookCount;

    public books_vo() {
    }

    public books_vo(String bookId, String bookName, String bookClassify, int bookCount) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.bookClassify = bookClassify;
        this.bookCount = bookCount;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookClassify() {
        return bookClassify;
    }

    public void setBookClassify(String bookClassify) {
        this.bookClassify = bookClassify;
    }

    public int getBookCount() {
        return bookCount;
    }

    public void setBookCount(int bookCount) {
        this.bookCount = bookCount;
    }
}
