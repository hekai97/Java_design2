package hk.windows;
/**
 * 查询类
 * 该类的作用是在点击主界面中的查询菜单时出现的窗口
 * 删除类，更新类都是该类的子类
 * */
import hk.model.Student;
import hk.sql.StudentList;

import javax.swing.*;
import java.util.List;

public class Inquire {
    private JScrollPane scrollPane;
    public JFrame frame;
    private JTable table;
    public Inquire(){
        showTable();

    }
    private void setFrame(){
        frame=new JFrame("成绩表");
        frame.setSize(800,600);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        setScrollPane();
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(scrollPane);
        frame.setVisible(true);
    }
    //设置滚动面板
    private void setScrollPane(){
        scrollPane=new JScrollPane(table);
        scrollPane.setViewportView(table);
        scrollPane.setBounds(0,0,780,450);
    }
    private void setTable(Object[][] res,String[] s){
        table=new JTable(res,s);
    }
    private void showTable(){
        StudentList listStudent=new StudentList();
        List<Student> students=listStudent.StudentRes();
        String[] colname ={"学号","姓名","性别","年龄","身份证号码","班级","学院"};
        //将查询到的结果集转换成一个数组
        Object[][] res=new Object[students.size()][colname.length];
        for(int i=0;i<students.size();i++)
        {
            Student student = students.get(i);
            res[i][0]=student.getSno();
            res[i][1]=student.getName();
            res[i][2]=student.getSex();
            res[i][3]=student.getAge();
            res[i][4]=student.getID();
            res[i][5]=student.getClassName();
            res[i][6]=student.getFaculty();
        }

        setTable(res,colname);
        setFrame();
    }
}
