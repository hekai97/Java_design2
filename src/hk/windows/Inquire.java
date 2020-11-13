package hk.windows;

import hk.model.Student;
import hk.sql.List_student;

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
        //setPanel();
        setScrollPane();
        //frame.add(panel);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(scrollPane);
        frame.setVisible(true);
    }
    /*private void setPanel(){
        panel=new JPanel();
        panel.setLayout(null);
        //panel.setOpaque(true);
        //panel.setBackground(Color.GREEN);
        //设置JPanel的大小，只能这样设置，不能用setSize();不然刷新不出来
        //panel.setLocation(0,0);
        panel.setPreferredSize(new Dimension(800,450));

        //panel.setBounds(0,0,800,450);
        setScrollPane();
        //panel.add(scrollPane);
    }*/
    private void setScrollPane(){
        scrollPane=new JScrollPane(table);
        scrollPane.setViewportView(table);
        scrollPane.setBounds(0,0,780,450);
        //scrollPane.setBackground(Color.blue);
    }
    private void setTable(Object[][] res,String[] s){
        table=new JTable(res,s);
        //table.setSize(600,450);
    }
    private void showTable(){
        /*Connection con= DBCon.getConnection();
        String s= "select * from java.student";
        List<Student> students= new ArrayList<>();
        try {
            PreparedStatement preparedStatement = con.prepareStatement(s);
            preparedStatement.execute();
            ResultSet st=preparedStatement.executeQuery();
            while(st.next()){
                Student student=new Student();
                student.setSno(st.getString("Sno"));
                student.setName(st.getString("Sname"));
                student.setSex(st.getString("Sex"));
                student.setAge(st.getInt("Age"));
                student.setID(st.getString("ID"));
                student.setClassName(st.getString("ClassName"));
                student.setFaculty(st.getString("Faculty"));
                students.add(student);
            }*/
        List_student listStudent=new List_student();
        List<Student> students=listStudent.StudentRes();
        String[] colname ={"学号","姓名","性别","年龄","身份证号码","班级","学院"};
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
