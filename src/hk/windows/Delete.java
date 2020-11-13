package hk.windows;

import hk.sql.DBCon;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;

public class Delete extends Inquire{
    private JPanel panel;
    private JLabel label;
    private JTextField textField;
    private JButton button1;
    private JButton button2;
    private String s;
    public Delete(){
        super();
        setPanel();
        frame.setTitle("删除");
        frame.add(panel);
    }
    private void setPanel(){
        panel=new JPanel();
        panel.setLayout(null);
        panel.setBounds(0,450,800,150);
        //panel.setBackground(Color.GREEN);
        setLabel();
        setButton1();
        setTextField();
        panel.add(label);
        panel.add(textField);
        panel.add(button1);
        //panel.add(button2);
    }
    private void setLabel(){
        label=new JLabel("请输入要删除的学号:",JLabel.CENTER);
        label.setBounds(20,20,200,30);
    }
    private void setTextField(){
        textField=new JTextField(20);
        textField.setBounds(220,20,200,30);
    }
    private void setButton1(){
        button1 =new JButton("确定");
        button1.setBounds(500,20,100,30);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()== button1)
                {
                    s=textField.getText();
                    if(!Objects.equals(s, "")) {
                        delete();
                    }else{
                        JOptionPane.showMessageDialog(frame,"请输入","提示",JOptionPane.CLOSED_OPTION);
                    }
                }
            }
        });
    }
    private void delete(){
        String sql="delete from java.student where Sno=?";
        Connection con= DBCon.getConnection();
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, s);
            preparedStatement.execute();
            JOptionPane.showMessageDialog(frame,"删除成功","提示",JOptionPane.CLOSED_OPTION);
        }catch(SQLException e)
        {
            e.printStackTrace();
        }
    }
}
