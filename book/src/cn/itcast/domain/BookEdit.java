package cn.itcast.domain;
//图书记录实体类
import java.util.Date;

public class BookEdit {
    private String bookId;
    private String manId;
    private Date operateTime;
    private String operate;
    private int operateCount;

    public BookEdit() {
    }

    public BookEdit(String bookId, String manId, Date operateTime, String operate, int operateCount) {
        this.bookId = bookId;
        this.manId = manId;
        this.operateTime = operateTime;
        this.operate = operate;
        this.operateCount = operateCount;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getManId() {
        return manId;
    }

    public void setManId(String manId) {
        this.manId = manId;
    }

    public Date getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(Date operateTime) {
        this.operateTime = operateTime;
    }

    public String getOperate() {
        return operate;
    }

    public void setOperate(String operate) {
        this.operate = operate;
    }

    public int getOperateCount() {
        return operateCount;
    }

    public void setOperateCount(int operateCount) {
        this.operateCount = operateCount;
    }

    @Override
    public String toString() {

        return "BookEdit{" +
                "bookId='" + bookId + '\'' +
                ", manId='" + manId + '\'' +
                ", operateTime=" + operateTime +
                ", operate='" + operate + '\'' +
                ", operateCount=" + operateCount +
                '}';
    }
}
