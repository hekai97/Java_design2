package hk.windows;
/**
 * ����ʵ���˵�¼���棬Ϊ�ó����һ�����õĽ���
 */
import hk.listener.Login_BTListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class Login {
    private final JFrame frame=new JFrame("��¼");
    private final JLabel title=new JLabel("ѧ������ϵͳ",SwingConstants.CENTER);
    private final JLabel userT=new JLabel("�û�����",SwingConstants.RIGHT);
    private final JLabel passwordT=new JLabel("���룺",JLabel.RIGHT);
    private final JTextField user=new JTextField(20);
    private final JPasswordField password=new JPasswordField(20);
    private final JButton button=new JButton(/*"��¼"*/);
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
    //���õ�¼��ť����ͼƬ�滻
    private void setButton(){
        button.setFont(new Font("",Font.BOLD,20));
        button.setBounds(230,250,90,30);
        button.setIcon(new ImageIcon("res/4.jpg"));
        panel.add(button);
        /**
         * ���õ�¼����ĵ�¼��ť�������ı����н��յ����û���������
         * ��ȷʱ�㿪����һ�������ڣ����ҽ��ô��ڹر�
         * ��֮�������Ի�����ʾ�û��˻������������
         * */
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //�õ���¼����������û��������룬���бȽ�
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
        JOptionPane.showMessageDialog(frame,"��¼�ɹ�","��ʾ", JOptionPane.PLAIN_MESSAGE);
        frame.dispose();
        new MainFrame();
    }
    private void Fail(){
        JOptionPane.showMessageDialog(frame,"��¼ʧ��","��ʾ", JOptionPane.PLAIN_MESSAGE);
    }
}
