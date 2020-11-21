package cn.itcast.domain;

public class Book {
    private String bookId;
    private String bookName;
    private String bookClassify;
    private  int bookCount;
    public Book(String bookId) {
        this.bookId = bookId;
    }

    public Book() {

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

    @Override
    public String toString() {
        return "Book{" +
                "bookId='" + bookId + '\'' +
                ", bookName='" + bookName + '\'' +
                ", bookClassify='" + bookClassify + '\'' +
                ", bookCount='" + bookCount + '\'' +
                '}';
    }
}
