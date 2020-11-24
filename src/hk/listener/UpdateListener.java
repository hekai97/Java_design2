package hk.listener;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/***********************************************************
 * 版权所有 (C)2020, hekai
 *
 * 文件名称：UpdateListener.java
 * 文件标识：无
 * 内容摘要：该类以实现ActionListener接口的方式实现了listener类
 * 该类的操作只是简单的将在文本框上按回车模拟为点击一下按钮
 * 按钮的监听器已经在windows包中的Update类中进行了定义
 * 其它说明：无
 * 当前版本： V1.0
 * 作   者：贺凯
 * 完成日期： 20201115
 **********************************************************/
public class UpdateListener implements ActionListener {
    JButton button;

    public void setButton(JButton button) {
        this.button = button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        button.doClick();
    }
}
