package hk.windows;
/**
 * ������
 * ���˵��еĲ�������>����
 * */
import hk.listener.Update_Listener;
import hk.sql.DBCon;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;

public class Upadte extends Inquire{
    String sql="update java.student set ";
    private JPanel panel;
    private JLabel DeleteLabel;
    private JLabel TipLabel;
    private JButton button;
    private JLabel SnoLabel;
    private JLabel SnameLabel;
    private JLabel SexLabel;
    private JLabel AgeLabel;
    private JLabel IDLabel;
    private JLabel ClassNameLabel;
    private JLabel FacultyLabel;

    private JTextField DeleteTextField;
    private JTextField SnoTextField;
    private JTextField SnameTextField;
    private JTextField SexTextField;
    private JTextField AgeTextField;
    private JTextField IDTextField;
    private JTextField ClassNameTextField;
    private JTextField FacultyTextField;
    /**���캯��**/
    public Upadte(){
        super();
        setFrame();

    }
    private void setFrame(){
        frame.setSize(800,700);
        frame.setTitle("����");
        setPanel();
        frame.add(panel);
    }
    private void setPanel(){
        panel=new JPanel();
        panel.setLayout(null);
        panel.setBounds(0,450,800,250);
        setDeleteLabel();
        setTipLabel();
        setSnoLabel();
        setSnameLabel();
        setSexLabel();
        setAgeLabel();
        setIDLabel();
        setClassNameLabel();
        setFacultyLabel();
        setDeleteTextField();
        setSnoTextField();
        setSnameTextField();
        setSexTextField();
        setAgeTextField();
        setIDTextField();
        setClassNameTextField();
        setFacultyTextField();
        setButton();
        AddListener();

        panel.add(DeleteLabel);
        panel.add(TipLabel);
        panel.add(SnoLabel);
        panel.add(SnameLabel);
        panel.add(SexLabel);
        panel.add(AgeLabel);
        panel.add(IDLabel);
        panel.add(ClassNameLabel);
        panel.add(FacultyLabel);
        panel.add(DeleteTextField);
        panel.add(SnoTextField);
        panel.add(SnameTextField);
        panel.add(SexTextField);
        panel.add(AgeTextField);
        panel.add(IDTextField);
        panel.add(ClassNameTextField);
        panel.add(FacultyTextField);
        panel.add(button);
    }
    private void setDeleteLabel(){
        DeleteLabel=new JLabel("������Ҫ���ĵ�ѧ��:",JLabel.RIGHT);
        DeleteLabel.setBounds(10,10,150,30);
    }
    private void setTipLabel(){
        TipLabel=new JLabel("��������������������޸ĺ��ֵ�����������޸ġ�",JLabel.CENTER);
        TipLabel.setBounds(310,10,600,30);
    }
    private void setSnoLabel(){
        SnoLabel=new JLabel("ѧ��:",JLabel.RIGHT);
        SnoLabel.setBounds(10,60,50,30);
    }
    private void setSnameLabel(){
        SnameLabel=new JLabel("����:",JLabel.RIGHT);
        SnameLabel.setBounds(210,60,50,30);
    }
    private void setSexLabel(){
        SexLabel=new JLabel("�Ա�:",JLabel.RIGHT);
        SexLabel.setBounds(310,60,50,30);
    }
    private void setAgeLabel(){
        AgeLabel=new JLabel("����:",JLabel.RIGHT);
        AgeLabel.setBounds(410,60,50,30);
    }
    private void setIDLabel(){
        IDLabel=new JLabel("���֤��:",JLabel.RIGHT);
        IDLabel.setBounds(510,60,100,30);
    }
    private void setClassNameLabel(){
        ClassNameLabel=new JLabel("�༶:",JLabel.RIGHT);
        ClassNameLabel.setBounds(10,100,50,30);
    }
    private void setFacultyLabel(){
        FacultyLabel=new JLabel("ѧԺ:",JLabel.RIGHT);
        FacultyLabel.setBounds(110,100,50,30);
    }
    private void setDeleteTextField(){
        DeleteTextField=new JTextField(12);
        DeleteTextField.setBounds(160,10,150,30);
    }
    private void setSnoTextField(){
        SnoTextField=new JTextField(12);
        SnoTextField.setBounds(60,60,150,30);
    }
    private void setSnameTextField(){
        SnameTextField=new JTextField(10);
        SnameTextField.setBounds(260,60,50,30);
    }
    private void setSexTextField(){
        SexTextField=new JTextField(2);
        SexTextField.setBounds(360,60,50,30);
    }
    private void setAgeTextField(){
        AgeTextField=new JTextField(2);
        AgeTextField.setBounds(460,60,50,30);
    }
    private void setIDTextField(){
        IDTextField=new JTextField(18);
        IDTextField.setBounds(610,60,100,30);
    }
    private void setClassNameTextField(){
        ClassNameTextField=new JTextField(10);
        ClassNameTextField.setBounds(60,100,50,30);
    }
    private void setFacultyTextField(){
        FacultyTextField=new JTextField(10);
        FacultyTextField.setBounds(160,100,50,30);
    }

    private String getDeleteSno(){
        String s=null;
        if(!Objects.equals(DeleteTextField.getText(), ""))
        {
            s="Sno="+"'"+DeleteTextField.getText()+"'";
        }
        return s;
    }
    private String getSno(){
        String s=SnoTextField.getText();
        if(!Objects.equals(SnoTextField.getText(), ""))
        {
            s="Sno="+"'"+SnoTextField.getText()+"'"+",";
        }
        return s;
    }
    private String getSname(){
        String s=SnameTextField.getText();
        if(!Objects.equals(SnameTextField.getText(),""))
        {
            s="Sname="+"'"+SnameTextField.getText()+"'"+",";
        }
        return s;
    }
    private String getSex(){
        String s=SexTextField.getText();
        if(!Objects.equals(SexTextField.getText(),""))
        {
            s="Sex="+"'"+SexTextField.getText()+"'"+",";
        }
        return s;
    }
    private String getAge(){
        String s=AgeTextField.getText();
        if(!Objects.equals(AgeTextField.getText(),""))
        {
            s="Age="+AgeTextField.getText()+",";
        }
        return s;
    }
    private String getID(){
        String s=IDTextField.getText();
        if(!Objects.equals(IDTextField.getText(),""))
        {
            s="Sex="+"'"+IDTextField.getText()+"'"+",";
        }
        return s;
    }
    private String getClassName(){
        String s=ClassNameTextField.getText();
        if(!Objects.equals(ClassNameTextField.getText(),""))
        {
            s="Sex="+"'"+ClassNameTextField.getText()+"'"+",";
        }
        return s;
    }
    private String getFaculty(){
        String s=FacultyTextField.getText();
        if(!Objects.equals(FacultyTextField.getText(),""))
        {
            s="Sex="+"'"+FacultyTextField.getText()+"'"+",";
        }
        return s;
    }


    private void setButton(){
        button=new JButton("ȷ��");
        button.setBounds(610,100,60,30);
        //�ô�Ϊ��ť�ļ���������
        //���ڽ���ĸ����ı����ϰ��»س�ʱ��ģ����һ�������ť
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()==button)
                {
                    updatesql();
                    JOptionPane.showMessageDialog(frame,"�޸ĳɹ�","��ʾ", JOptionPane.PLAIN_MESSAGE);
                }
            }
        });
    }

    private void updatesql(){
        //String sql="update java.student set ?=?,?=?,?=?,?=?,?=?,?=?,?=? where Sno=?";
        String s=sql+getSno()+getSname()+getSex()+getAge()+getID()+getClassName()+getFaculty()+" where "+getDeleteSno();
        s=s.replace(", where"," where");
        Connection con= DBCon.getConnection();
        try {
            PreparedStatement preparedStatement = con.prepareStatement(s);
            preparedStatement.execute();
        }catch(SQLException e)
        {
            JOptionPane.showMessageDialog(frame,"�����쳣������!","��ʾ",JOptionPane.CLOSED_OPTION);
        }
    }
    private void AddListener(){
        Update_Listener listener=new Update_Listener();
        listener.setButton(button);
        SnoTextField.addActionListener(listener);
        SnameTextField.addActionListener(listener);
        SexTextField.addActionListener(listener);
        AgeTextField.addActionListener(listener);
        IDTextField.addActionListener(listener);
        ClassNameTextField.addActionListener(listener);
        FacultyTextField.addActionListener(listener);
    }
}
