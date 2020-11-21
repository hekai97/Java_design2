package hk.windows;

import hk.sql.DBCon;
import hk.verify.MD5encryption;
import hk.verify.UserAndPassword;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;

public class Registered {
    private final JFrame frame=new JFrame("ע��");
    private final JPanel panel=new JPanel();
    private final JLabel userT=new JLabel("�û���:",JLabel.RIGHT);
    private final JLabel passwordT=new JLabel("����:",JLabel.RIGHT);
    private final JTextField user=new JTextField(10);
    private final JPasswordField password=new JPasswordField(10);
    private final JButton button=new JButton("ȷ��ע��");
    private String UserName=null;
    private String PassWord=null;

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
        setButton();
        panel.add(userT);
        panel.add(user);
        panel.add(password);
        panel.add(passwordT);
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
    private void setButton(){
        button.setBounds(200,300,100,30);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()==button/*||e.getSource()==user||e.getSource()==password*/) {
                    UserName = user.getText();
                    PassWord = md5encryption.MD5encrypt(new String(password.getPassword()));
                    UserAndPassword userAndPassword = new UserAndPassword();
                    if (userAndPassword.verifyUser(UserName)) {
                        //������û����Ѿ����ڣ���ʾ�û�
                        JOptionPane.showMessageDialog(frame, "���û����Ѿ�����", "��ʾ", JOptionPane.PLAIN_MESSAGE);
                    }else if(Objects.equals(UserName, "")){
                        JOptionPane.showMessageDialog(frame, "�û�������Ϊ��", "��ʾ", JOptionPane.PLAIN_MESSAGE);
                    }
                    else if(Objects.equals(PassWord, "")) {
                        JOptionPane.showMessageDialog(frame, "���벻��Ϊ��", "��ʾ", JOptionPane.PLAIN_MESSAGE);
                    }
                    else {
                        String sql = "insert into java.User_Password values" + "(?,?)";
                        Connection con = DBCon.getConnection();
                        try {
                            PreparedStatement preparedStatement = con.prepareStatement(sql);
                            preparedStatement.setString(1, UserName);
                            preparedStatement.setString(2, PassWord);
                            preparedStatement.execute();
                        } catch (SQLException g) {
                            g.printStackTrace();
                        }
                        JOptionPane.showMessageDialog(frame, "ע��ɹ�","��ʾ",JOptionPane.PLAIN_MESSAGE);
                        frame.dispose();
                    }
                }
            }
        });
    }

/*
    public static void main(String[] args) {
        Registered registered=new Registered();
    }
    */
}
