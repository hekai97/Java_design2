package hk.windows;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Objects;
/***********************************************************
 * 版权所有 (C)2020, hekai
 *
 * 文件名称：Save.java
 * 文件标识：无
 * 内容摘要：该类是主界面中的文件菜单里面的另存为选项，在点击该选项后
 * 弹出此窗口，询问存放的位置并且进行存储
 * 其它说明：无
 * 当前版本： V1.0
 * 作   者：贺凯
 * 完成日期： 20201115
 **********************************************************/
public class Save {
    private JFrame frame;
    private JLabel label;
    private JTextField textField;
    private String Filename=null;
    public Save(){
        setFrame();
    }
    private void setFrame(){
        frame=new JFrame("另存为");
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
        label=new JLabel("请输入要存放的位置以及文件名：");
    }
    private void setTextField(){
        textField=new JTextField(16);
        textField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Filename=textField.getText();
                if(Objects.equals(Filename, "")||Filename==null){
                    JOptionPane.showMessageDialog(frame,"文件路径和名称不能为空","提示",JOptionPane.PLAIN_MESSAGE);
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
