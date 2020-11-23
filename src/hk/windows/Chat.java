package hk.windows;
/**
 * �û����з�������
 * ����Ҳ�Ǳ����ʵ����һ��Java�����ҵ�����
 * �ڿͻ��˽������ӣ���֤����˿���
 * ����Խ�������
 * ������ʾ����ʧ��
 * */
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
                    Socket clientSocket = new Socket(aServerIP, aServerPort);
                    InputStreamReader streamReader = new InputStreamReader(clientSocket.getInputStream());
                    reader = new BufferedReader(streamReader);
                    writer = new PrintWriter(clientSocket.getOutputStream());

                    clientTextArea.append("������������...\n");

                    Thread readerThread = new Thread(incomingReader);
                    readerThread.start();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(clientFrame, "�޷����ӷ�����!\n��ȷ�Ϸ�����Ƿ���");
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
