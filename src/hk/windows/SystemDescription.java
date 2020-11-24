package hk.windows;

import javax.swing.*;
import java.awt.*;
/***********************************************************
 * 版权所有 (C)2020, hekai
 *
 * 文件名称：SystemDescription.java
 * 文件标识：无
 * 内容摘要：设置系统说明子菜单的类
 * 其它说明：无
 * 当前版本： V1.0
 * 作   者：贺凯
 * 完成日期： 20201115
 **********************************************************/
public class SystemDescription {
    private JFrame frame;
    public SystemDescription(){
        setFrame();
    }
    private void setFrame(){
        frame=new JFrame("系统说明");
        frame.setLayout(new BorderLayout());
        //setTextArea();

        frame.setSize(480,500);
        //new BackGroundImage(frame,frame.getContentPane(),"6.jpg");
        frame.add(new JLabel(new ImageIcon("res/6.jpg")));
        frame.setLocationRelativeTo(null);
        //frame.add(textArea,BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
    }
}
