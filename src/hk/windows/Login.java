package hk.windows;
/**
 * 该类实现了登录界面，为该程序第一个调用的界面
 */
import hk.background.BackGroundImage;
import hk.listener.Login_BTListener;
import hk.verify.UserAndPassword;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Login {
    private final JFrame frame=new JFrame("登录");
    private final JLabel title=new JLabel("学生管理系统",SwingConstants.CENTER);
    private final JLabel userT=new JLabel("用户名：",SwingConstants.RIGHT);
    private final JLabel passwordT=new JLabel("密码：",JLabel.RIGHT);
    private final JTextField user=new JTextField(20);
    private final JPasswordField password=new JPasswordField(20);
    private final JButton button=new JButton(/*"登录"*/);
    private final JPanel panel=new JPanel();
    /**
     * 2020.11.21日，新增注册和忘记密码功能
     */
    private final JLabel registered=new JLabel("注册",JLabel.CENTER);
    private final JLabel forgetpassword=new JLabel("忘记密码",JLabel.CENTER);
    public Login(){
        new BackGroundImage(frame,frame.getContentPane(),"1.jpg");
        panel.setLayout(null);
        //设置panel透明，显示背景图
        panel.setBackground(null);
        panel.setOpaque(false);
        setTitle();
        setUserT();
        setPasswordT();
        setUser();
        setPassword();
        setRegistered();
        setForgetpassword();
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
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    //设置登录界面的主标题
    private void setTitle(){
        title.setBounds(150,50,200,50);
        title.setFont(new Font("",Font.BOLD,25));
        panel.add(title);
    }
    //设置登录界面的用户文本，将字体调到20号字
    private void setUserT(){
        userT.setFont(new Font("",Font.BOLD,20));
        userT.setBounds(100,150,100,20);
        panel.add(userT);
    }
    //设置登录界面的密码文本，将字体调到20号字
    private void setPasswordT(){
        passwordT.setFont(new Font("",Font.BOLD,20));
        passwordT.setBounds(100,190,100,20);
        panel.add(passwordT);
    }
    //设置登录界面的用户账号框
    private void setUser(){
        user.setBounds(220,150,150,20);
        panel.add(user);
    }
    //设置登录界面的用户密码框
    private void setPassword(){
        password.setBounds(220,190,150,20);
        panel.add(password);
    }
    //注册界面
    private void setRegistered(){
        registered.setBounds(230,220,50,30);
        panel.add(registered);
        registered.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new Registered();
            }
        });
    }
    //忘记密码界面
    private void setForgetpassword(){
        forgetpassword.setBounds(230,260,50,30);
        panel.add(forgetpassword);
        forgetpassword.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new Forget();
            }
        });
    }
    //设置登录按钮，用图片替换
    private void setButton(){
        button.setFont(new Font("",Font.BOLD,20));
        button.setBounds(230,300,90,30);
        button.setIcon(new ImageIcon("res/4.jpg"));
        panel.add(button);
        /**
         * 设置登录界面的登录按钮，并从文本框中接收到的用户名和密码
         * 正确时便开启下一个主窗口，并且将该窗口关闭
         * 反之，弹出对话框提示用户账户密码输入错误
         * */

        UserAndPassword verify=new UserAndPassword();
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //拿到登录框中输入的用户名和密码，进行比较
                String username= user.getText();
                String userpassword=new String(password.getPassword());
                if(e.getSource()==button)
                {
                    /**
                     * 得到用户名和密码后
                     * 将密码进行MD5加密
                     * 根据verifyUser函数的返回值进行对应的操作*/
                    switch (verify.verifyUserPassword(username,userpassword))
                    {
                        case 1:
                            Success();break;
                        case 2:
                            PasswordFail();break;
                        case 3:
                            UserFail();break;
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
    private void PasswordFail(){
        JOptionPane.showMessageDialog(frame,"登录失败,\n请检查密码。","密码错误", JOptionPane.PLAIN_MESSAGE);
    }
    private void UserFail(){
        JOptionPane.showMessageDialog(frame,"登录失败\n请检查用户名","用户名错误",JOptionPane.PLAIN_MESSAGE);
    }
}
