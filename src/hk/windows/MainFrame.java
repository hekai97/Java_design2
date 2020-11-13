package hk.windows;
/**
 **程序运行的主界面，在登录窗口退出后开启该界面
 */

import hk.background.BackGroundImage;
import hk.listener.Chat_listener;
import hk.listener.Item_Listener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame {
    Item_Listener itemListener=new Item_Listener();
    private final JFrame frame=new JFrame("学生管理系统");
    private final JPanel panel=new JPanel();
    private final JMenuBar jMenuBar=new JMenuBar();
    private final JMenu filemenu=new JMenu("导出");
    private final JMenuItem saveasItem=new JMenuItem("另存为");
    private final JMenu Operatemenu=new JMenu("操作");
    private final JMenuItem IncreaseItem=new JMenuItem("添加");
    private final JMenuItem DeleteItem=new JMenuItem("删除");
    private final JMenuItem UpdateItem=new JMenuItem("更改");
    private final JMenuItem InquireItem=new JMenuItem("查询");
    //private final JMenuItem StatementItem=new JMenuItem("按语句查询");
    //private final JMenuItem conditionItem=new JMenuItem("按条件查询");
    private final JMenu helpmenu=new JMenu("帮助");
    private final JMenuItem chatItem=new JMenuItem("反馈");
    private final JMenuItem descriptionItem=new JMenuItem("说明");
    public MainFrame(){
        setjMenuBar();
        setFrame();
    }
    private void setFrame(){
        frame.setSize(1366,800);
        //frame.add(panel);
        frame.setJMenuBar(jMenuBar);
        //将窗口放到最中间的位置
        frame.setLocationRelativeTo(null);
        //获取Jframe的父类Container
        Container con=frame.getContentPane();
        //添加图片
        new BackGroundImage(frame,con,"图片1.jpg");
        //禁止改变大小
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);

        //setStatementItem();

    }
    //设置主窗口中的文件菜单
    private void setFilemenu(){
        setSaveasItem();
        filemenu.add(saveasItem);
    }
    //设置主窗口中的数据库操作菜单
    private void setOperatemenu(){
        setIncreaseItem();
        setDeleteItem();
        setUpdateItem();
        setInquireItem();
        Operatemenu.add(IncreaseItem);
        Operatemenu.add(DeleteItem);
        Operatemenu.add(UpdateItem);
        Operatemenu.add(InquireItem);
        //Operatemenu.add(StatementItem);
        //Operatemenu.add(conditionItem);
    }
    //设置主窗口中的帮助菜单
    private void setHelpmenu(){
        setChatItem();
        setDescriptionItem();
        helpmenu.add(chatItem);
        helpmenu.add(descriptionItem);
    }
    private void setjMenuBar(){
        setFilemenu();
        setOperatemenu();
        setHelpmenu();
        jMenuBar.add(filemenu);
        jMenuBar.add(Operatemenu);
        jMenuBar.add(helpmenu);
    }
    //以下都是对菜单中的子项进行设置并且添加监听器
    private void setIncreaseItem(){
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
                    /*Chat_listener chat_listener=new Chat_listener();
                    Thread thread=new Thread(chat_listener);
                    thread.start();*/
                    //itemListener.doChat();
                    Chat chat=new Chat();
                    chat.startUp();
                }
            }
        });
    }
    /*private void setStatementItem(){
        StatementItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new StatementInqure();
            }
        });
    }*/
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
    }
}
