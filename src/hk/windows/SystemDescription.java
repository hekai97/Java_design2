package hk.windows;

import hk.background.BackGroundImage;

import javax.swing.*;
import java.awt.*;

public class SystemDescription {
    private JFrame frame;
    private JTextArea textArea;
    public SystemDescription(){
        setFrame();
    }
    private void setFrame(){
        frame=new JFrame("系统说明");
        frame.setLayout(new BorderLayout());
        //setTextArea();
        //new BackGroundImage(frame,frame.getContentPane(),"6.jpg");
        frame.setSize(500,500);
        frame.setLocationRelativeTo(null);
        //frame.add(textArea,BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }
    private void setTextArea(){
        textArea=new JTextArea();
        textArea.setSize(400,400);
        //未完成
    }
}
