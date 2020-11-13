package hk.windows;
/**
 **�������е������棬�ڵ�¼�����˳������ý���
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
    private final JFrame frame=new JFrame("ѧ������ϵͳ");
    private final JPanel panel=new JPanel();
    private final JMenuBar jMenuBar=new JMenuBar();
    private final JMenu filemenu=new JMenu("����");
    private final JMenuItem saveasItem=new JMenuItem("���Ϊ");
    private final JMenu Operatemenu=new JMenu("����");
    private final JMenuItem IncreaseItem=new JMenuItem("���");
    private final JMenuItem DeleteItem=new JMenuItem("ɾ��");
    private final JMenuItem UpdateItem=new JMenuItem("����");
    private final JMenuItem InquireItem=new JMenuItem("��ѯ");
    //private final JMenuItem StatementItem=new JMenuItem("������ѯ");
    //private final JMenuItem conditionItem=new JMenuItem("��������ѯ");
    private final JMenu helpmenu=new JMenu("����");
    private final JMenuItem chatItem=new JMenuItem("����");
    private final JMenuItem descriptionItem=new JMenuItem("˵��");
    public MainFrame(){
        setjMenuBar();
        setFrame();
    }
    private void setFrame(){
        frame.setSize(1366,800);
        //frame.add(panel);
        frame.setJMenuBar(jMenuBar);
        //�����ڷŵ����м��λ��
        frame.setLocationRelativeTo(null);
        //��ȡJframe�ĸ���Container
        Container con=frame.getContentPane();
        //���ͼƬ
        new BackGroundImage(frame,con,"ͼƬ1.jpg");
        //��ֹ�ı��С
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);

        //setStatementItem();

    }
    //�����������е��ļ��˵�
    private void setFilemenu(){
        setSaveasItem();
        filemenu.add(saveasItem);
    }
    //�����������е����ݿ�����˵�
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
    //�����������еİ����˵�
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
    //���¶��ǶԲ˵��е�����������ò�����Ӽ�����
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
