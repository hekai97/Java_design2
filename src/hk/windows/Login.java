package hk.windows;

import hk.listener.Login_BTListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class Login {
    private final JFrame frame=new JFrame("登录");
    private final JLabel title=new JLabel("学生管理系统",SwingConstants.CENTER);
    private final JLabel userT=new JLabel("用户名：",SwingConstants.RIGHT);
    private final JLabel passwordT=new JLabel("密码：",JLabel.RIGHT);
    private final JTextField user=new JTextField(20);
    private final JPasswordField password=new JPasswordField(20);
    private final JButton button=new JButton(/*"登录"*/);
    private final JPanel panel=new JPanel();
    public Login(){
        panel.setLayout(null);
        setTitle();
        setUserT();
        setPasswordT();
        setUser();
        setPassword();
        setButton();
        setFrame();
        new Login_BTListener(user,password,button);     //给用户框，密码框添加监听，监听结果为模拟点击button
    }
    private void setFrame(){
        frame.setSize(500,500);
        frame.setResizable(false);          //禁止改变大小
        frame.setLocationRelativeTo(null);    //让界面居中
        //Container con=frame.getContentPane();
        //new BackGroundImage(frame,con,"1.jpg");
        frame.add(panel);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    private void setTitle(){
        title.setBounds(150,50,200,50);
        title.setFont(new Font("",Font.BOLD,25));
        panel.add(title);
    }
    private void setUserT(){
        userT.setFont(new Font("",Font.BOLD,20));
        userT.setBounds(100,150,100,20);
        panel.add(userT);
    }
    private void setPasswordT(){
        passwordT.setFont(new Font("",Font.BOLD,20));
        passwordT.setBounds(100,190,100,20);
        panel.add(passwordT);
    }
    private void setUser(){
        user.setBounds(220,150,150,20);
        panel.add(user);
    }
    private void setPassword(){
        password.setBounds(220,190,150,20);
        panel.add(password);
    }
    private void setButton(){
        button.setFont(new Font("",Font.BOLD,20));
        button.setBounds(230,250,90,30);
        button.setIcon(new ImageIcon("res/4.jpg"));
        panel.add(button);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //拿到登录框中输入的用户名和密码，进行比较
                String username= user.getText();
                String userpassword=new String(password.getPassword());
                if(e.getSource()==button)
                {
                    if(Objects.equals(username, "123") && Objects.equals(userpassword, "123"))
                    {
                        Success();
                    }
                    else
                    {
                        Fail();
                    }
                }
            }
        });
    }
    private void Success(){
        JOptionPane.showMessageDialog(frame,"登录成功","提示", JOptionPane.PLAIN_MESSAGE);
        frame.dispose();
        new MainFrame();
    }
    private void Fail(){
        JOptionPane.showMessageDialog(frame,"登录失败","提示", JOptionPane.PLAIN_MESSAGE);
    }
}
