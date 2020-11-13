package hk.windows;

import hk.model.Student;
import hk.sql.DBCon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;

public class Insert {
    private static final Connection con=DBCon.getConnection();
    private final Student student=new Student();
    private final JFrame frame=new JFrame("插入");
    private final JPanel panel=new JPanel();
    private final JButton button=new JButton("确定");
    private final JLabel SnoLabel=new JLabel("学号");
    private final JLabel SnameLabel=new JLabel("姓名");
    private final JLabel SexLabel=new JLabel("性别");
    private final JLabel AgeLabel=new JLabel("年龄");
    private final JLabel IDLabel=new JLabel("身份证号");
    private final JLabel ClassNameLabel=new JLabel("班级");
    private final JLabel FacultyLabel=new JLabel("学院");

    private final JTextField SnoTextField=new JTextField(12);
    private final JTextField SnameTextField=new JTextField(10);
    private final JTextField SexTextField=new JTextField(2);
    private final JTextField AgeTextField=new JTextField(2);
    private final JTextField IDTextField=new JTextField(18);
    private final JTextField ClassNameTextField=new JTextField(10);
    private final JTextField FacultyTextField=new JTextField(10);
    /**
     * 主
     * */
    public Insert(){
        /**在此添加要实现的功能*/
        setPanel();
        setFrame();
    }
    private void setFrame(){
        frame.setSize(500,400);
        frame.add(panel);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }
    private void setPanel(){
        panel.setLayout(new FlowLayout());
        setSnoLabel();
        setSnameLabel();
        setSexLabel();
        setAgeLabel();
        setIDLabel();
        setClassNameLabel();
        setFacultyLabel();

        panel.add(SnoLabel);
        panel.add(SnoTextField);
        panel.add(SnameLabel);
        panel.add(SnameTextField);
        panel.add(SexLabel);
        panel.add(SexTextField);
        panel.add(AgeLabel);
        panel.add(AgeTextField);
        panel.add(IDLabel);
        panel.add(IDTextField);
        panel.add(ClassNameLabel);
        panel.add(ClassNameTextField);
        panel.add(FacultyLabel);
        panel.add(FacultyTextField);
        panel.add(button);

        setButton();
    }
    private void setSnoLabel(){

    }
    private void setSnameLabel(){

    }
    private void setSexLabel(){

    }
    private void setAgeLabel(){

    }
    private void setIDLabel(){

    }
    private void setClassNameLabel(){

    }
    private void setFacultyLabel(){

    }
    private void setSnoTextField(){
        if(Objects.equals(SnoTextField.getText(), ""))
        {
            student.setSno(null);
        }
        else {
            student.setSno(SnoTextField.getText());
        }
    }
    private void setSnameTextField(){
        student.setName(SnameTextField.getText());
        if(Objects.equals(student.getName(), ""))
        {
            student.setName(null);
        }
    }
    private void setSexTextField(){
        student.setSex(SexTextField.getText());
        if(Objects.equals(student.getSex(), ""))
        {
            student.setSex("男");
        }
    }
    private void setAgeTextField(){
        try {
            student.setAge(Integer.parseInt(AgeTextField.getText()));
        }catch (NumberFormatException e)
        {
            student.setAge(0);
        }

    }
    private void setIDTextField(){
        if(Objects.equals(student.getID(),"")){
            student.setID(IDTextField.getText());
        }else
        {
            student.setID(null);
        }
    }
    private void setClassNameTextField(){
        if(Objects.equals(student.getClassName(),"")) {
            student.setClassName(ClassNameTextField.getText());
        }else
        {
            student.setClassName(null);
        }
    }
    private void setFacultyTextField(){
        if(Objects.equals(student.getFaculty(),"")) {
            student.setFaculty(FacultyTextField.getText());
        }else{
            student.setFaculty(null);
        }
    }

    private void setButton(){
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()==button)
                {
                    setSnoTextField();
                    setSnameTextField();
                    setSexTextField();
                    setAgeTextField();
                    setIDTextField();
                    setClassNameTextField();
                    setFacultyTextField();
                    try{
                        InsertStatement();
                    }catch(SQLException f)
                    {
                        //f.printStackTrace();
                        JOptionPane.showMessageDialog(frame,"已存在该学号","提示",JOptionPane.CLOSED_OPTION);
                    }
                }
            }
        });
    }

    private void InsertStatement() throws SQLException {
        String sql="insert into java.student(Sno,Sname,Sex,Age,ID,ClassName,Faculty)"+"values (?,?,?,?,?,?,?)";
        PreparedStatement preparedStatement=con.prepareStatement(sql);
        preparedStatement.setString(1,student.getSno());
        preparedStatement.setString(2,student.getName());
        preparedStatement.setString(3,student.getSex());
        preparedStatement.setInt(4,student.getAge());
        preparedStatement.setString(5,student.getID());
        preparedStatement.setString(6,student.getClassName());
        preparedStatement.setString(7,student.getFaculty());
        preparedStatement.execute();
        JOptionPane.showMessageDialog(frame,"添加成功","提示",JOptionPane.CLOSED_OPTION);
    }
}
