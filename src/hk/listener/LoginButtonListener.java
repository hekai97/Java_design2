package hk.listener;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
/***********************************************************
 * 版权所有 (C)2020, hekai
 *
 * 文件名称：LoginButtonListener.java
 * 文件标识：无
 * 内容摘要：该类用来给登录界面的用户名框和密码框添加监听
 * 将两个框传进来之后,如果接收到换行符时
 * 就模拟点击一下按钮,按钮点击的事件在Login.java中已经定义
 * 其它说明：无
 * 当前版本： V1.0
 * 作   者：贺凯
 * 完成日期： 20201115
 **********************************************************/
public class LoginButtonListener {
    public LoginButtonListener(JTextField textField, JPasswordField passwordField, JButton button)
    {
        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyChar()=='\n')
                {
                    button.doClick();
                }
            }
        });
        passwordField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyChar()=='\n')
                {
                    button.doClick();
                }
            }
        });
    }
}
