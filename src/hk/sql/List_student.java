package hk.sql;

import hk.model.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class List_student {
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
