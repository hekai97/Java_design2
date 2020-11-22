package hk.windows;
/**
 * ����ʵ���˵�¼���棬Ϊ�ó����һ�����õĽ���
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
    private final JFrame frame=new JFrame("��¼");
    private final JLabel title=new JLabel("ѧ������ϵͳ",SwingConstants.CENTER);
    private final JLabel userT=new JLabel("�û�����",SwingConstants.RIGHT);
    private final JLabel passwordT=new JLabel("���룺",JLabel.RIGHT);
    private final JTextField user=new JTextField(20);
    private final JPasswordField password=new JPasswordField(20);
    private final JButton button=new JButton(/*"��¼"*/);
    private final JPanel panel=new JPanel();
    /**
     * 2020.11.21�գ�����ע����������빦��
     */
    private final JLabel registered=new JLabel("ע��",JLabel.CENTER);
    private final JLabel forgetpassword=new JLabel("��������",JLabel.CENTER);
    public Login(){
        new BackGroundImage(frame,frame.getContentPane(),"1.jpg");
        panel.setLayout(null);
        //����panel͸������ʾ����ͼ
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
        new Login_BTListener(user,password,button);     //���û����������Ӽ������������Ϊģ����button
    }
    private void setFrame(){
        frame.setSize(500,500);
        frame.setResizable(false);          //��ֹ�ı��С
        frame.setLocationRelativeTo(null);    //�ý������
        //Container con=frame.getContentPane();
        //new BackGroundImage(frame,con,"1.jpg");
        frame.add(panel);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    //���õ�¼�����������
    private void setTitle(){
        title.setBounds(150,50,200,50);
        title.setFont(new Font("",Font.BOLD,25));
        panel.add(title);
    }
    //���õ�¼������û��ı������������20����
    private void setUserT(){
        userT.setFont(new Font("",Font.BOLD,20));
        userT.setBounds(100,150,100,20);
        panel.add(userT);
    }
    //���õ�¼����������ı������������20����
    private void setPasswordT(){
        passwordT.setFont(new Font("",Font.BOLD,20));
        passwordT.setBounds(100,190,100,20);
        panel.add(passwordT);
    }
    //���õ�¼������û��˺ſ�
    private void setUser(){
        user.setBounds(220,150,150,20);
        panel.add(user);
    }
    //���õ�¼������û������
    private void setPassword(){
        password.setBounds(220,190,150,20);
        panel.add(password);
    }
    //ע�����
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
    //�����������
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
    //���õ�¼��ť����ͼƬ�滻
    private void setButton(){
        button.setFont(new Font("",Font.BOLD,20));
        button.setBounds(230,300,90,30);
        button.setIcon(new ImageIcon("res/4.jpg"));
        panel.add(button);
        /**
         * ���õ�¼����ĵ�¼��ť�������ı����н��յ����û���������
         * ��ȷʱ�㿪����һ�������ڣ����ҽ��ô��ڹر�
         * ��֮�������Ի�����ʾ�û��˻������������
         * */

        UserAndPassword verify=new UserAndPassword();
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //�õ���¼����������û��������룬���бȽ�
                String username= user.getText();
                String userpassword=new String(password.getPassword());
                if(e.getSource()==button)
                {
                    /**
                     * �õ��û����������
                     * ���������MD5����
                     * ����verifyUser�����ķ���ֵ���ж�Ӧ�Ĳ���*/
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
        JOptionPane.showMessageDialog(frame,"��¼�ɹ�","��ʾ", JOptionPane.PLAIN_MESSAGE);
        frame.dispose();
        new MainFrame();
    }
    private void PasswordFail(){
        JOptionPane.showMessageDialog(frame,"��¼ʧ��,\n�������롣","�������", JOptionPane.PLAIN_MESSAGE);
    }
    private void UserFail(){
        JOptionPane.showMessageDialog(frame,"��¼ʧ��\n�����û���","�û�������",JOptionPane.PLAIN_MESSAGE);
    }
}
