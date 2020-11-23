package hk.windows;
/**
 * �������������е��ļ��˵���������Ϊѡ��
 * �ڵ����ѡ���
 * �����˴���
 * ѯ�ʴ�ŵ�λ�ò��ҽ��д洢
 * */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Objects;

public class Save {
    private JFrame frame;
    private JLabel label;
    private JTextField textField;
    private String Filename=null;
    public Save(){
        setFrame();
    }
    private void setFrame(){
        frame=new JFrame("���Ϊ");
        frame.setSize(300,150);
        frame.setLayout(new FlowLayout());
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
        setLabel();
        setTextField();
        frame.add(label);
        frame.add(textField);
    }
    private void setLabel(){
        label=new JLabel("������Ҫ��ŵ�λ���Լ��ļ�����");
    }
    private void setTextField(){
        textField=new JTextField(16);
        textField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Filename=textField.getText();
                if(Objects.equals(Filename, "")||Filename==null){
                    JOptionPane.showMessageDialog(frame,"�ļ�·�������Ʋ���Ϊ��","��ʾ",JOptionPane.PLAIN_MESSAGE);
                }else{
                new SaveFile(new File(Filename));
                frame.dispose();
                }
            }
        });
    }

    public JFrame getFrame() {
        return frame;
    }
}
