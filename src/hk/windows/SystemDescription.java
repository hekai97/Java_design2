package hk.windows;

import javax.swing.*;
import java.awt.*;

public class SystemDescription {
    private JFrame frame;
    public SystemDescription(){
        setFrame();
    }
    private void setFrame(){
        frame=new JFrame("系统说明");
        frame.setLayout(new BorderLayout());
        //setTextArea();

        frame.setSize(480,480);
        //new BackGroundImage(frame,frame.getContentPane(),"6.jpg");
        frame.add(new JLabel(new ImageIcon("res/6.jpg")));
        frame.setLocationRelativeTo(null);
        //frame.add(textArea,BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
    }
}
