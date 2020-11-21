package cn.itcast.dao.JavaBean.Student_JavaBean;

public class stu_vo {
    private String stuId;
    private String stuName;
    private String stuPassword;

    public stu_vo(){}

    public stu_vo(String id,String name,String pass){
        this.stuId=id;
        this.stuName=name;
        this.stuPassword=pass;
    }

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getStuPassword() {
        return stuPassword;
    }

    public void setStuPassword(String stuPassword) {
        this.stuPassword = stuPassword;
    }
}
