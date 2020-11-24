package hk.sql;
import hk.model.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/***********************************************************
 * 版权所有 (C)2020, hekai
 *
 * 文件名称：StudentList.java
 * 文件标识：无
 * 内容摘要：该类得到数据库中的学生信息，并且将其放在ArrayList中返回
 * 其它说明：无
 * 当前版本： V1.0
 * 作   者：贺凯
 * 完成日期： 20201115
 **********************************************************/
public class StudentList {
    public List<Student> StudentRes(){
        Connection con= DBCon.getConnection();
        String s= "select * from java.student";
        List<Student> students= new ArrayList<>();
        try{
            PreparedStatement preparedStatement = con.prepareStatement(s);
            preparedStatement.execute();
            ResultSet st = preparedStatement.executeQuery();
            while (st.next()) {
                Student student = new Student();
                student.setSno(st.getString("Sno"));
                student.setName(st.getString("Sname"));
                student.setSex(st.getString("Sex"));
                student.setAge(st.getInt("Age"));
                student.setID(st.getString("ID"));
                student.setClassName(st.getString("ClassName"));
                student.setFaculty(st.getString("Faculty"));
                students.add(student);
            }
        }catch(SQLException e)
        {
            e.printStackTrace();
        }
        return students;
    }
}
