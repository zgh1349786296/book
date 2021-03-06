package cn.itcast.dao.DAO;




import cn.itcast.domain.Student;
import cn.itcast.util.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class StudentDAO {

    //修改学生信息
    public void update(Student student) throws Exception{
        Connection conn = JDBCUtils.getConnection();
        String Sql = "update stuinf set stuName = ?, stuPassword = ? where stuId = ?";
        PreparedStatement ps = conn.prepareStatement(Sql);
        ps.setString(1,student.getStuName());
        ps.setString(2,student.getStuPassword());
        ps.setString(3,student.getStuId());
        ps.executeUpdate();
        JDBCUtils.free(null,ps,conn);
    }

    //查询
    public Student searchStuInf(String stuId) throws Exception{
        Connection conn = JDBCUtils.getConnection();
        Student student = null;
        String Sql = "select * from stuinf where stuId = ?";
        PreparedStatement ps = conn.prepareStatement(Sql);
        ps.setString(1,stuId);
        ResultSet rs = ps.executeQuery();
        if (rs.next()){
            student = new Student();
            student.setStuId(rs.getString("stuId"));
            student.setStuName(rs.getString("stuName"));
            student.setStuPassword(rs.getString("stuPassword"));
        }
        JDBCUtils.free(rs,ps,conn);
        return student;
    }
}
