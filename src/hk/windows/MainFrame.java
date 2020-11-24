package hk.windows;
/**
 **程序运行的主界面，在登录窗口退出后开启该界面
 */

import hk.background.BackGroundImage;
import hk.listener.MenuItemListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame {
    MenuItemListener itemListener=new MenuItemListener();
    private final JFrame frame=new JFrame("学生管理系统");
    private final JMenuBar jMenuBar=new JMenuBar();
    private final JMenu filemenu=new JMenu("文件");
    private final JMenuItem saveasItem=new JMenuItem("另存为");
    private final JMenuItem exitItem=new JMenuItem("退出");
    private final JMenu Operatemenu=new JMenu("操作");
    private final JMenuItem IncreaseItem=new JMenuItem("添加");
    private final JMenuItem DeleteItem=new JMenuItem("删除");
    private final JMenuItem UpdateItem=new JMenuItem("更改");
    private final JMenuItem InquireItem=new JMenuItem("查询");
    private final JMenu helpmenu=new JMenu("帮助");
    private final JMenuItem chatItem=new JMenuItem("反馈");
    private final JMenuItem descriptionItem=new JMenuItem("说明");

    /**增加修改密码和密保问题选项*/
    public MainFrame(){
        setjMenuBar();
        setFrame();
    }
    private void setFrame(){
        frame.setSize(1000,600);
        //frame.add(panel);
        frame.setJMenuBar(jMenuBar);
        //将窗口放到最中间的位置
        frame.setLocationRelativeTo(null);
        //获取Jframe的父类Container
        Container con=frame.getContentPane();
        //添加图片
        new BackGroundImage(frame,con,"1.jpg");
        //禁止改变大小
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }

    /**
     * 定义一个ActionListener监听器的变量
     * 根据是哪个子菜单发出的指令而对其进行不同的操作
     * 将该监听器分别添加至menuItem中
     * 该变量的定义，有效减少了冗余的代码
     * 否则需要对每个menuItem进行监听器的加入
     * 该变量也可以设置成一个实现了ActionListener接口的类
     * 在添加监听器时也可以直接添加这个类
     */
    ActionListener actionListener=new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource()==IncreaseItem)
            {
                itemListener.doIncrease();
            }
            if(e.getSource()==DeleteItem)
            {
                itemListener.doDelete();
            }
            if(e.getSource()==UpdateItem)
            {
                itemListener.doUpdate();
            }
            if(e.getSource()==InquireItem)
            {
                itemListener.doInquire();
            }
            if(e.getSource()==chatItem)
            {
                Chat chat=new Chat();
                chat.startUp();
            }
            if(e.getSource()==saveasItem)
            {
                new Save();
            }
            if(e.getSource()==descriptionItem)
            {
                new SystemDescription();
            }
            if(e.getSource()==exitItem)
            {
                System.exit(0);
            }
        }
    };


    //设置主窗口中的文件菜单
    private void setFilemenu(){
        //setSaveasItem();
        exitItem.addActionListener(actionListener);
        saveasItem.addActionListener(actionListener);

        filemenu.add(saveasItem);
        filemenu.add(exitItem);
    }
    //设置主窗口中的数据库操作菜单
    private void setOperatemenu(){
        //先将各个menuItem进行设置，然后添加到menu中
        /*setIncreaseItem();
        setDeleteItem();
        setUpdateItem();
        setInquireItem();*/
        IncreaseItem.addActionListener(actionListener);
        DeleteItem.addActionListener(actionListener);
        UpdateItem.addActionListener(actionListener);
        InquireItem.addActionListener(actionListener);

        Operatemenu.add(IncreaseItem);
        Operatemenu.add(DeleteItem);
        Operatemenu.add(UpdateItem);
        Operatemenu.add(InquireItem);
    }
    //设置主窗口中的帮助菜单
    private void setHelpmenu(){
        /*setChatItem();
        setDescriptionItem();*/
        chatItem.addActionListener(actionListener);
        descriptionItem.addActionListener(actionListener);

        helpmenu.add(chatItem);
        helpmenu.add(descriptionItem);
    }
    //该menuBar为主菜单，放置在主界面最上方，为一长条
    private void setjMenuBar(){
        setFilemenu();
        setOperatemenu();
        setHelpmenu();
        /**********/
        //设置菜单栏透明
        jMenuBar.setBackground(null);
        jMenuBar.setOpaque(false);
        /**********/
        jMenuBar.add(filemenu);
        jMenuBar.add(Operatemenu);
        jMenuBar.add(helpmenu);
    }

    /**
     * 以下代码是第一次所写
     * 在定义了ActionListener变量后下述方法便显得冗余
     */
    //以下都是对菜单中的子项进行设置并且添加监听器
    /*private void setIncreaseItem(){
        IncreaseItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()==IncreaseItem){
                    itemListener.doIncrease();
                }
            }
        });
    }
    private void setDeleteItem(){
        DeleteItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()==DeleteItem)
                {
                    itemListener.doDelete();
                }
            }
        });
    }
    private void setUpdateItem(){
        UpdateItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()==UpdateItem){
                    itemListener.doUpdate();
                }
            }
        });
    }
    private void setInquireItem(){
        InquireItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()==InquireItem)
                {
                    itemListener.doInquire();
                }
            }
        });
    }
    private void setChatItem(){
        chatItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()==chatItem)
                {
                    Chat chat=new Chat();
                    chat.startUp();
                }
            }
        });
    }
    private void setSaveasItem(){
        saveasItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //new SaveFile(new File("D:\\hk.txt"));
                new Save();
                //
            }
        });
    }
    private void setDescriptionItem(){
        descriptionItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SystemDescription();
            }
        });
    }*/
}
