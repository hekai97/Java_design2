package hk.listener;
/**
 * 该类以实现ActionListener接口的方式实现了listener类
 * 该类的操作只是简单的将在文本框上按回车模拟为点击一下按钮
 * 按钮的监听器已经在windows包中的Update类中进行了定义
 * */
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Update_Listener implements ActionListener {
    JButton button;

    public void setButton(JButton button) {
        this.button = button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        button.doClick();
    }
}
