/*package hk.Windows;

import hk.Model.Student;
import hk.Sql.DBCon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StatementInqure {
    private JFrame frame;
    private JTextField textField;
    private JScrollPane scrollPane;
    private String sql;
    private JTable table;
    public StatementInqure(){
        setFrame();
    }
    private void setFrame(){
        frame=new JFrame("查询");
        frame.setSize(500,500);
        frame.setLocationRelativeTo(null);
        setTextField();
        //setScrollPane();
        frame.add(textField, BorderLayout.NORTH);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    private void setTextField(){
        textField=new JTextField(10);
        textField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()==textField) {
                    sql = textField.getText();
                    showtable();
                    frame.repaint();
                }
            }
        });
    }
    private void setScrollPane(JTable table){
        scrollPane=new JScrollPane(table);
        frame.add(scrollPane,BorderLayout.CENTER);
    }
    private void showtable(){
        Connection con= DBCon.getConnection();
        List<Student> students= new ArrayList<>();
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
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

            String[] colname = {"学号", "姓名", "性别", "年龄", "身份证号码", "班级", "学院"};
            Object[][] res = new Object[students.size()][colname.length];
            for (int i = 0; i < students.size(); i++) {
                Student student = students.get(i);
                res[i][0] = student.getSno();
                res[i][1] = student.getName();
                res[i][2] = student.getSex();
                res[i][3] = student.getAge();
                res[i][4] = student.getID();
                res[i][5] = student.getClassName();
                res[i][6] = student.getFaculty();
            }
            setTable(res,colname);
        }catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
    private void setTable(Object[][] res,String[] colname)
    {
        table=new JTable(res,colname);
        setScrollPane(table);
    }
}
*/