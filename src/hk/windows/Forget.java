package hk.windows;

import hk.sql.DBCon;
import hk.verify.MD5encryption;
import hk.verify.UserAndPassword;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class Forget{
    private final JFrame frame=new JFrame("忘记密码");
    private final JPanel panel=new JPanel();
    private final JLabel userT=new JLabel("忘记密码的用户名:",JLabel.RIGHT);
    private final JLabel passwordT=new JLabel("输入新密码:",JLabel.RIGHT);
    private final JTextField user=new JTextField(10);
    private final JPasswordField password=new JPasswordField(10);
    private final JButton button=new JButton("修改");

    /**密保问题*/
    private final JLabel questionT=new JLabel("密保问题:",JLabel.RIGHT);
    private final JLabel answerT=new JLabel("答案:",JLabel.RIGHT);
    private final JTextField question=new JTextField(10);
    private final JTextField answer=new JTextField(10);
    private String UserName=null;
    private String PassWord=null;
    private String Qusetion=null;
    private String Answer=null;
    public Forget(){
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

        setQuestionT();
        setQuestion();
        setAnswerT();
        setAnswer();
        panel.add(userT);
        panel.add(user);
        panel.add(password);
        panel.add(passwordT);

        panel.add(questionT);
        panel.add(question);
        panel.add(answerT);
        panel.add(answer);

        panel.add(button);
    }
    private void setUserT(){
        userT.setBounds(80,100,100,30);
    }
    private void setUser(){
        user.setBounds(200,100,100,30);
        user.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                UserName=user.getText();
                String sql="select Question,Answer from java.user_password where User=?";
                Connection con = DBCon.getConnection();
                try {
                    PreparedStatement preparedStatement = con.prepareStatement(sql);
                    preparedStatement.setString(1, UserName);
                    preparedStatement.execute();
                    ResultSet st=preparedStatement.executeQuery();
                    while(st.next()){
                        Qusetion=st.getString("Question");
                        Answer=st.getString("Answer");
                    }
                } catch (SQLException g) {
                    g.printStackTrace();
                }
                question.setText(Qusetion);
            }
        });
    }
    private void setPasswordT(){
        passwordT.setBounds(80,180,100,30);
    }
    private void setPassword(){
        password.setBounds(200,180,100,30);
    }

    private void setQuestionT(){
        questionT.setBounds(80,240,100,30);
    }
    private void setQuestion(){
        question.setBounds(200,240,200,30);
        question.setEnabled(false);
    }
    private void setAnswerT(){
        answerT.setBounds(80,290,100,30);
    }
    private void setAnswer(){
        answer.setBounds(200,290,100,30);
    }



    private void setButton(){
        button.setBounds(200,350,100,30);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()==button)
                {
                    MD5encryption md5encryption=new MD5encryption();
                    UserName = user.getText();
                    PassWord = md5encryption.MD5encrypt(new String(password.getPassword()));
                    UserAndPassword userAndPassword = new UserAndPassword();
                    if (!userAndPassword.verifyUser(UserName)) {
                        //如果该用户名不存在，提示用户
                        JOptionPane.showMessageDialog(frame, "该用户名不存在", "提示", JOptionPane.PLAIN_MESSAGE);
                    }else if(Objects.equals(UserName, "")){
                        JOptionPane.showMessageDialog(frame, "用户名不能为空", "提示", JOptionPane.PLAIN_MESSAGE);
                    }else if(!Objects.equals(md5encryption.MD5encrypt(answer.getText()), Answer)){
                        JOptionPane.showMessageDialog(frame, "密保答案错误", "提示", JOptionPane.PLAIN_MESSAGE);
                    }
                    else if(Objects.equals(PassWord, "")) {
                        JOptionPane.showMessageDialog(frame, "密码不能为空", "提示", JOptionPane.PLAIN_MESSAGE);
                    } else {
                        String sql = "UPDATE java.User_Password set password=? where User=?";
                        Connection con = DBCon.getConnection();
                        try {
                            PreparedStatement preparedStatement = con.prepareStatement(sql);
                            preparedStatement.setString(1, PassWord);
                            preparedStatement.setString(2, UserName);
                            preparedStatement.execute();
                        } catch (SQLException g) {
                            g.printStackTrace();
                        }
                        JOptionPane.showMessageDialog(frame, "修改成功","提示",JOptionPane.PLAIN_MESSAGE);
                        frame.dispose();
                    }
                }
            }
        });
    }

    public static void main(String[] args) {
        new Forget();
    }
}
