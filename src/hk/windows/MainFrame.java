package hk.windows;
/**
 **�������е������棬�ڵ�¼�����˳������ý���
 */

import hk.background.BackGroundImage;
import hk.listener.MenuItemListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame {
    MenuItemListener itemListener=new MenuItemListener();
    private final JFrame frame=new JFrame("ѧ������ϵͳ");
    private final JMenuBar jMenuBar=new JMenuBar();
    private final JMenu filemenu=new JMenu("�ļ�");
    private final JMenuItem saveasItem=new JMenuItem("���Ϊ");
    private final JMenuItem exitItem=new JMenuItem("�˳�");
    private final JMenu Operatemenu=new JMenu("����");
    private final JMenuItem IncreaseItem=new JMenuItem("���");
    private final JMenuItem DeleteItem=new JMenuItem("ɾ��");
    private final JMenuItem UpdateItem=new JMenuItem("����");
    private final JMenuItem InquireItem=new JMenuItem("��ѯ");
    private final JMenu helpmenu=new JMenu("����");
    private final JMenuItem chatItem=new JMenuItem("����");
    private final JMenuItem descriptionItem=new JMenuItem("˵��");

    /**�����޸�������ܱ�����ѡ��*/
    public MainFrame(){
        setjMenuBar();
        setFrame();
    }
    private void setFrame(){
        frame.setSize(1000,600);
        //frame.add(panel);
        frame.setJMenuBar(jMenuBar);
        //�����ڷŵ����м��λ��
        frame.setLocationRelativeTo(null);
        //��ȡJframe�ĸ���Container
        Container con=frame.getContentPane();
        //���ͼƬ
        new BackGroundImage(frame,con,"1.jpg");
        //��ֹ�ı��С
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }

    /**
     * ����һ��ActionListener�������ı���
     * �������ĸ��Ӳ˵�������ָ���������в�ͬ�Ĳ���
     * ���ü������ֱ������menuItem��
     * �ñ����Ķ��壬��Ч����������Ĵ���
     * ������Ҫ��ÿ��menuItem���м������ļ���
     * �ñ���Ҳ�������ó�һ��ʵ����ActionListener�ӿڵ���
     * ����Ӽ�����ʱҲ����ֱ����������
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


    //�����������е��ļ��˵�
    private void setFilemenu(){
        //setSaveasItem();
        exitItem.addActionListener(actionListener);
        saveasItem.addActionListener(actionListener);

        filemenu.add(saveasItem);
        filemenu.add(exitItem);
    }
    //�����������е����ݿ�����˵�
    private void setOperatemenu(){
        //�Ƚ�����menuItem�������ã�Ȼ����ӵ�menu��
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
    //�����������еİ����˵�
    private void setHelpmenu(){
        /*setChatItem();
        setDescriptionItem();*/
        chatItem.addActionListener(actionListener);
        descriptionItem.addActionListener(actionListener);

        helpmenu.add(chatItem);
        helpmenu.add(descriptionItem);
    }
    //��menuBarΪ���˵������������������Ϸ���Ϊһ����
    private void setjMenuBar(){
        setFilemenu();
        setOperatemenu();
        setHelpmenu();
        /**********/
        //���ò˵���͸��
        jMenuBar.setBackground(null);
        jMenuBar.setOpaque(false);
        /**********/
        jMenuBar.add(filemenu);
        jMenuBar.add(Operatemenu);
        jMenuBar.add(helpmenu);
    }

    /**
     * ���´����ǵ�һ����д
     * �ڶ�����ActionListener�����������������Ե�����
     */
    //���¶��ǶԲ˵��е�����������ò�����Ӽ�����
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
