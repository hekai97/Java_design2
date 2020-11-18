package hk.windows;

/*import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
/**
 * ��Ҫ���ж��߳�����
 **/
/*public class Chat extends JFrame {
    private ObjectInputStream input;
    private ObjectOutputStream output;
    private JTextField enter;
    private JTextArea display;

    public Chat(String title) {
        super(title);
        Container con = getContentPane();
        enter = new JTextField();
        enter.setEnabled(false);
        enter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String s = e.getActionCommand();
                    output.writeObject(s);
                    output.flush();
                    displyAppend("�ͻ��ˣ�" + s);
                    enter.setText("");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        con.add(enter, BorderLayout.SOUTH);
        display = new JTextArea();
        con.add(new JScrollPane(display), BorderLayout.CENTER);
        setSize(500, 500);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public void displyAppend(String s) {
        display.append(s + "\n");
        display.setCaretPosition(display.getText().length());
        //enter.requestFocusInWindow(); //������еĻ�����ʹ�ô����������ڱ����޷���ʾ
    }

    public boolean isEndSession(String m) {
        if (m.equalsIgnoreCase("q"))
            return true;
        if (m.equalsIgnoreCase("quit"))
            return true;
        if (m.equalsIgnoreCase("exit"))
            return true;
        return false;
    }

    public void chatRun(String host, int port) {
        try {
            displyAppend("��������");
            Socket so = new Socket(host, port);
            String m;
            output = new ObjectOutputStream(so.getOutputStream());
            input = new ObjectInputStream(so.getInputStream());
            enter.setEnabled(true);
            do {
                m = (String) input.readObject();
                displyAppend("�������ˣ�" + m);
            } while (!isEndSession(m));
            output.writeObject("q");
            output.flush();
            enter.setEnabled(false);
            output.close();
            input.close();
            so.close();
        } catch (Exception h) {
            JOptionPane.showMessageDialog(this,"���ӳ�ʱ","��ʾ",JOptionPane.CLOSED_OPTION);
            this.dispose();
        }
    }
}*/


import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

import javax.swing.*;

public class Chat {
    private final JFrame clientFrame;
    private final JLabel sayLabel;
    private final JLabel nicknameLabel;
    private final JTextField nicknameText;
    private final JTextField sayText;
    private final JButton connectButton;
    private final JButton nicknameButton;
    private final JButton sayButton;
    private final JPanel jPanelNorth;
    private final JPanel jPanelSouth0;
    private final JPanel jPanelSouth1;
    private final JPanel jPanelSouth2;
    private final JTextArea clientTextArea;
    private final JScrollPane scroller;
    private BufferedReader reader;
    private PrintWriter writer;
    private String nickname;
    private final String aServerIP = "127.0.0.1";
    private final int aServerPort = 5000;

    // ��ʼ�����
    public Chat() {
        nickname = "�ͻ���";

        clientFrame = new JFrame();
        jPanelNorth = new JPanel();
        connectButton = new JButton("����");
        clientTextArea = new JTextArea();
        scroller = new JScrollPane(clientTextArea);
        jPanelSouth0 = new JPanel();
        jPanelSouth1 = new JPanel();
        jPanelSouth2 = new JPanel();
        nicknameLabel = new JLabel("�ǳ�", JLabel.LEFT);
        nicknameText = new JTextField(nickname, 30);
        nicknameButton = new JButton("ȷ��");
        sayLabel = new JLabel("��Ϣ", JLabel.LEFT);
        sayText = new JTextField(30);
        sayButton = new JButton("ȷ��");
    }

    // ����GUI
    private void buildGUI() {
        // ���ڵ�����
        clientFrame.setTitle("�ͻ���");
        clientFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        clientFrame.setSize(550, 550);
        clientFrame.setLocationRelativeTo(null);

        // ���������
        jPanelNorth.add(connectButton);
        clientFrame.getContentPane().add(BorderLayout.NORTH, jPanelNorth);

        // �м�����
        clientTextArea.setFocusable(false);
        scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        clientFrame.getContentPane().add(BorderLayout.CENTER, scroller);

        // ���������
        jPanelSouth1.add(nicknameLabel);
        jPanelSouth1.add(nicknameText);
        jPanelSouth1.add(nicknameButton);
        jPanelSouth2.add(sayLabel);
        jPanelSouth2.add(sayText);
        jPanelSouth2.add(sayButton);
        jPanelSouth0.setLayout(new BoxLayout(jPanelSouth0, BoxLayout.Y_AXIS));
        jPanelSouth0.add(jPanelSouth1);
        jPanelSouth0.add(jPanelSouth2);
        clientFrame.getContentPane().add(BorderLayout.SOUTH, jPanelSouth0);

        // ���ô��ڿɼ�
        clientFrame.setVisible(true);
    }

    // �ͻ�������
    public void startUp() {
        buildGUI();

        // ���շ�������Ϣ���߳�
        Runnable incomingReader = new Runnable() {
            @Override
            public void run() {
                String message;
                try {
                    while ((message = reader.readLine()) != null) {
                        clientTextArea.append(message + "\n");
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        };

        // ����Connect��ť��ʵ�ַ�����������
        connectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    @SuppressWarnings("resource")
                    Socket clientSocket = new Socket(aServerIP, aServerPort);
                    InputStreamReader streamReader = new InputStreamReader(clientSocket.getInputStream());
                    reader = new BufferedReader(streamReader);
                    writer = new PrintWriter(clientSocket.getOutputStream());

                    clientTextArea.append("������������...\n");

                    Thread readerThread = new Thread(incomingReader);
                    readerThread.start();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(clientFrame, "���Ӳ��Ϸ�����!\n��ȷ�� IP �� �˿� ������ȷ��");
                }
            }
        });

        // ����nickname�������ǳ�
        ActionListener nicknameListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String aText = nicknameText.getText();
                if (!aText.equals("")) {
                    nickname = aText;
                }
            }
        };
        nicknameButton.addActionListener(nicknameListener);
        nicknameText.addActionListener(nicknameListener);
        nicknameText.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
            }

            @Override
            public void focusLost(FocusEvent e) {
                String aText = nicknameText.getText();
                if (!aText.equals("")) {
                    nickname = aText;
                }
            }
        });

        // ������Ϣ��������
        ActionListener SayListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String aText = sayText.getText();
                if (aText.equals("")) {
                    JOptionPane.showMessageDialog(clientFrame, "���ݲ���Ϊ�գ�");
                } else {
                    try {
                        writer.println(nickname + "��" + aText);
                        writer.flush();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    sayText.setText("");
                }
            }
        };
        sayButton.addActionListener(SayListener);
        sayText.addActionListener(SayListener);
    }
}
