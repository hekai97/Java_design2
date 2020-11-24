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
 * ��Ȩ���� (C)2020, hekai
 *
 * �ļ����ƣ�Registered.java
 * �ļ���ʶ����
 * ����ժҪ������ʵ����ע����棬���û��ڵ�½������ע��ʱ������ת��������
 * ����˵������
 * ��ǰ�汾�� V1.0
 * ��   �ߣ��ؿ�
 * ������ڣ� 20201115
 **********************************************************/
public class Registered {

    //�����ܱ�����
    String[] list={"<-��ѡ���ܱ�����->",
                   "������������",
                   "���Сѧ��ʲô���֣�",
                   "�㶨����ʲô�ط���"};
    //���ַ�����������û�ѡ����ܱ�����
    String choiceAnswer=null;
    //���ַ�����������û�������ܱ�����Ĵ�
    String QA=null;
    private final JFrame frame=new JFrame("ע��");
    private final JPanel panel=new JPanel();
    private final JLabel userT=new JLabel("�û���:",JLabel.RIGHT);
    private final JLabel passwordT=new JLabel("����:",JLabel.RIGHT);
    private final JTextField user=new JTextField(10);
    private final JPasswordField password=new JPasswordField(10);
    private final JButton button=new JButton("ȷ��ע��");
    /**
     * �����ܱ�����
     */
    private final JLabel question=new JLabel("ѡ���ܱ�����:",JLabel.RIGHT);
    private final JLabel answer=new JLabel("��:",JLabel.RIGHT);
    private final JComboBox<String> choice= new JComboBox<>(list);
    private final JTextField ans=new JTextField(10);
    /****/
    //����û�ע��ʱ���û���
    private String UserName=null;
    //����û�ע��ʱ������
    private String PassWord=null;
    //newһ�����󣬸ö����еķ���������������ܱ�����Ĵ𰸽���MD5����
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
                        //������û����Ѿ����ڣ���ʾ�û�
                        JOptionPane.showMessageDialog(frame, "���û����Ѿ�����", "��ʾ", JOptionPane.PLAIN_MESSAGE);
                    }else if(Objects.equals(UserName, "")){
                        JOptionPane.showMessageDialog(frame, "�û�������Ϊ��", "��ʾ", JOptionPane.PLAIN_MESSAGE);
                    }else if(Objects.equals(PassWord, "")) {
                        JOptionPane.showMessageDialog(frame, "���벻��Ϊ��", "��ʾ", JOptionPane.PLAIN_MESSAGE);
                    }else if(Objects.equals(choiceAnswer,list[0])||choiceAnswer==null) {
                        JOptionPane.showMessageDialog(frame,"��ѡ���ܱ�����","��ʾ",JOptionPane.PLAIN_MESSAGE);
                    }else if(Objects.equals(QA,"")||QA==null){
                        JOptionPane.showMessageDialog(frame,"�������","��ʾ",JOptionPane.PLAIN_MESSAGE);
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
                        JOptionPane.showMessageDialog(frame, "ע��ɹ�","��ʾ",JOptionPane.PLAIN_MESSAGE);
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
