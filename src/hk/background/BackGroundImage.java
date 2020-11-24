package hk.background;
import javax.swing.*;
import java.awt.*;
/***********************************************************
 * 版权所有 (C)2020, hekai
 *
 * 文件名称：BackGroundImage.java
 * 文件标识：无
 * 内容摘要：添加背景图片
 * 其它说明：无
 * 当前版本： V1.0
 * 作   者：贺凯
 * 完成日期： 20201115
 **********************************************************/
public class BackGroundImage {
    public BackGroundImage(JFrame frame, Container container, String ImageName) {
        // 限定加载图片路径
        ImageIcon icon= new ImageIcon("res/" + ImageName);
        final JLabel labelBackground = new JLabel();
        ImageIcon iconBookManageSystemBackground = icon;
        labelBackground.setIcon(iconBookManageSystemBackground);
        // 设置label的大小
        labelBackground.setBounds(0,0,iconBookManageSystemBackground.getIconWidth()
                ,iconBookManageSystemBackground.getIconHeight());
        // 将背景图片标签放入桌面面板的最底层
        frame.getLayeredPane().add(labelBackground, Integer.valueOf(Integer.MIN_VALUE));
        // 将容器转换为面板设置为透明
        JPanel panel = (JPanel)container;
        panel.setOpaque(false);

    }
}
