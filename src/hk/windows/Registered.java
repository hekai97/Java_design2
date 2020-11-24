package hk.windows;

import hk.sql.DBCon;
import hk.verify.MD5encryption;
import hk.verify.UserAndPassword;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;
/***********************************************************
 * 版权所有 (C)2020, hekai
 *
 * 文件名称：Registered.java
 * 文件标识：无
 * 内容摘要：该类实现了注册界面，当用户在登陆界面点击注册时，会跳转到该类中
 * 其它说明：无
 * 当前版本： V1.0
 * 作   者：贺凯
 * 完成日期： 20201115
 **********************************************************/
public class Registered {

    //设置密保问题
    String[] list={"<-请选择密保问题->",
                   "你出生地在哪里？",
                   "你的小学叫什么名字？",
                   "你定居在什么地方？"};
    //该字符串用来存放用户选择的密保问题
    String choiceAnswer=null;
    //该字符串用来存放用户输入的密保问题的答案
    String QA=null;
    private final JFrame frame=new JFrame("注册");
    private final JPanel panel=new JPanel();
    private final JLabel userT=new JLabel("用户名:",JLabel.RIGHT);
    private final JLabel passwordT=new JLabel("密码:",JLabel.RIGHT);
    private final JTextField user=new JTextField(10);
    private final JPasswordField password=new JPasswordField(10);
    private final JButton button=new JButton("确定注册");
    /**
     * 新增密保问题
     */
    private final JLabel question=new JLabel("选择密保问题:",JLabel.RIGHT);
    private final JLabel answer=new JLabel("答案:",JLabel.RIGHT);
    private final JComboBox<String> choice= new JComboBox<>(list);
    private final JTextField ans=new JTextField(10);
    /****/
    //存放用户注册时的用户名
    private String UserName=null;
    //存放用户注册时的密码
    private String PassWord=null;
    //new一个对象，该对象中的方法用来给密码和密保问题的答案进行MD5加密
    MD5encryption md5encryption=new MD5encryption();

    public Registered(){
        setFrame();
    }
    private void setFrame(){
        frame.setSize(500,500);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
        setPanel();
        frame.add(panel);
    }
    private void setPanel(){
        panel.setLayout(null);
        setUser();
        setUserT();
        setPasswordT();
        setPassword();
        setChoice();
        setButton();
        setQuestion();
        setAnswer();
        setAns();
        panel.add(userT);
        panel.add(user);
        panel.add(password);
        panel.add(passwordT);
        panel.add(choice);
        panel.add(question);
        panel.add(ans);
        panel.add(answer);
        panel.add(button);
    }
    private void setUserT(){
        userT.setBounds(80,100,100,30);
    }
    private void setUser(){
        user.setBounds(200,100,100,30);
    }
    private void setPasswordT(){
        passwordT.setBounds(80,180,100,30);
    }
    private void setPassword(){
        password.setBounds(200,180,100,30);
    }
    private void setQuestion(){
        question.setBounds(80,240,100,30);
    }
    private void setAnswer(){
        answer.setBounds(80,290,100,30);
    }
    private void setChoice(){
        choice.setBounds(200,240,200,30);
        choice.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                choiceAnswer=list[choice.getSelectedIndex()];
            }
        });
    }
    private void setAns(){
        ans.setBounds(200,290,100,30);
        ans.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                QA=ans.getText();
            }
        });
    }
    private void setButton(){
        button.setBounds(200,350,100,30);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()==button/*||e.getSource()==user||e.getSource()==password*/) {
                    UserName = user.getText();
                    PassWord = md5encryption.MD5encrypt(new String(password.getPassword()));
                    UserAndPassword userAndPassword = new UserAndPassword();
                    if (userAndPassword.verifyUser(UserName)) {
                        //如果该用户名已经存在，提示用户
                        JOptionPane.showMessageDialog(frame, "该用户名已经存在", "提示", JOptionPane.PLAIN_MESSAGE);
                    }else if(Objects.equals(UserName, "")){
                        JOptionPane.showMessageDialog(frame, "用户名不能为空", "提示", JOptionPane.PLAIN_MESSAGE);
                    }else if(Objects.equals(PassWord, "")) {
                        JOptionPane.showMessageDialog(frame, "密码不能为空", "提示", JOptionPane.PLAIN_MESSAGE);
                    }else if(Objects.equals(choiceAnswer,list[0])||choiceAnswer==null) {
                        JOptionPane.showMessageDialog(frame,"请选择密保问题","提示",JOptionPane.PLAIN_MESSAGE);
                    }else if(Objects.equals(QA,"")||QA==null){
                        JOptionPane.showMessageDialog(frame,"请输入答案","提示",JOptionPane.PLAIN_MESSAGE);
                    }
                    else {
                        String sql = "insert into java.User_Password values" + "(?,?,?,?)";
                        Connection con = DBCon.getConnection();
                        try {
                            PreparedStatement preparedStatement = con.prepareStatement(sql);
                            preparedStatement.setString(1, UserName);
                            preparedStatement.setString(2, PassWord);
                            preparedStatement.setString(3,choiceAnswer);
                            preparedStatement.setString(4,md5encryption.MD5encrypt(QA));
                            preparedStatement.execute();
                        } catch (SQLException g) {
                            g.printStackTrace();
                        }
                        JOptionPane.showMessageDialog(frame, "注册成功","提示",JOptionPane.PLAIN_MESSAGE);
                        frame.dispose();
                    }
                }
            }
        });
    }


    public static void main(String[] args) {
        new Registered();
    }

}
