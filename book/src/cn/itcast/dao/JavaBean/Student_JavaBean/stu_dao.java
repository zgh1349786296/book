package cn.itcast.dao.JavaBean.Student_JavaBean;




import cn.itcast.util.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class stu_dao {
    //添加学生
    public void add(stu_vo student) throws Exception {
        Connection conn = JDBCUtils.getConnection();
        String sql = "insert into stuInf(stuId,stuName,stuPassword) values(?,?,?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, student.getStuId());
        ps.setString(2, student.getStuName());
        ps.setString(3, student.getStuPassword());
        ps.executeUpdate();
        JDBCUtils.free(null, ps, conn);
    }

    //根据id查找学生
    public List<stu_vo> findStudentById(String stuId) throws Exception {
        Connection conn = JDBCUtils.getConnection();
        List<stu_vo> stuList = new ArrayList<stu_vo>();
        String sql = "select * from stuInf where stuId=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, stuId);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            stu_vo stu = new stu_vo();
            stu.setStuId(rs.getString("stuId"));
            stu.setStuName(rs.getString("stuName"));
            stu.setStuPassword(rs.getString("stuPassword"));
            stuList.add(stu);
        }
        JDBCUtils.free(rs, ps, conn);
        return stuList;
    }

    //根据名字查找学生
    public List<stu_vo> findStudentByName(String stuName) throws Exception {
        Connection conn = JDBCUtils.getConnection();
        List<stu_vo> stuList = new ArrayList<stu_vo>();
        String sql = "select * from stuInf where stuName=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, stuName);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            stu_vo stu = new stu_vo();
            stu.setStuId(rs.getString("stuId"));
            stu.setStuName(rs.getString("stuName"));
            stu.setStuPassword(rs.getString("stuPassword"));
            stuList.add(stu);
        }
        JDBCUtils.free(rs, ps, conn);
        return stuList;
    }

    //展示所有学生信息
    public List<stu_vo> selectAllStuInf() throws Exception {
        Connection conn = JDBCUtils.getConnection();
        List<stu_vo> stuList = new ArrayList<stu_vo>();
        String sql = "select * from stuInf";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            stu_vo stu = new stu_vo();
            stu.setStuId(rs.getString("stuId"));
            stu.setStuName(rs.getString("stuName"));
            stu.setStuPassword(rs.getString("stuPassword"));
            stuList.add(stu);
        }
        JDBCUtils.free(rs, ps, conn);
        return stuList;
    }

    //修改学生信息
    public void update(stu_vo stu) throws Exception {
        Connection conn = JDBCUtils.getConnection();
        String sql = "update stuInf set stuName=?,stuPassword=? where stuId=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, stu.getStuName());
        ps.setString(2, stu.getStuPassword());
        ps.setString(3, stu.getStuId());
        ps.executeUpdate();
        JDBCUtils.free(null, ps, conn);
    }

    //删除学生
    public void delete(String id) throws Exception {
        Connection conn = JDBCUtils.getConnection();
        String sql = "delete from stuInf where stuId=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,id);
        ps.executeUpdate();
        JDBCUtils.free(null, ps, conn);
    }
}
