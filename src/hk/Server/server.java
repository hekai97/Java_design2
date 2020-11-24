package hk.Server;
/**
 * 该类不进入主程序
 * 该类的作用是在主程序中反馈的服务端*/

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.*;

public class server {
    private final JFrame serverFrame;
    private final JLabel portLabel;
    private final JLabel sayLabel;
    private final JLabel nicknameLabel;
    private final JTextField portText;
    private final JTextField sayText;
    private final JTextField nicknameText;
    private final JButton startButton;
    private final JButton sayButton;
    private final JButton nicknameButton;
    private final JPanel jPanelNorth;
    private final JPanel jPanelSouth0;
    private final JPanel jPanelSouth1;
    private final JPanel jPanelSouth2;
    private final JScrollPane scroller;
    private final JTextArea serverTextArea;
    private ArrayList<PrintWriter> clientOutputStreams;
    private String nickname;



    // 初始化组件
    public server() {
        nickname = "服务器";

        serverFrame = new JFrame();
        jPanelNorth = new JPanel();
        portLabel = new JLabel("端口", JLabel.LEFT);
        portText = new JTextField(30);
        startButton = new JButton("开始");
        serverTextArea = new JTextArea();
        scroller = new JScrollPane(serverTextArea);
        nicknameLabel = new JLabel("昵称", JLabel.LEFT);
        nicknameText = new JTextField(nickname, 30);
        nicknameButton = new JButton("确认");
        jPanelSouth0 = new JPanel();
        jPanelSouth1 = new JPanel();
        jPanelSouth2 = new JPanel();
        sayLabel = new JLabel("消息", JLabel.LEFT);
        sayText = new JTextField(30);
        sayButton = new JButton("确认");
    }

    // 构建GUI
    private void buildGUI() {
        // 窗口的设置
        serverFrame.setTitle("服务器");
        serverFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        serverFrame.setSize(550, 550);
        serverFrame.setLocationRelativeTo(null);

        // 北区的组件
        jPanelNorth.add(portLabel);
        jPanelNorth.add(portText);
        jPanelNorth.add(startButton);
        serverFrame.getContentPane().add(BorderLayout.NORTH, jPanelNorth);

        // 中间的组件
        serverTextArea.setFocusable(false);
        scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        serverFrame.getContentPane().add(BorderLayout.CENTER, scroller);

        // 南区的组件
        jPanelSouth1.add(nicknameLabel);
        jPanelSouth1.add(nicknameText);
        jPanelSouth1.add(nicknameButton);
        jPanelSouth2.add(sayLabel);
        jPanelSouth2.add(sayText);
        jPanelSouth2.add(sayButton);
        jPanelSouth0.setLayout(new BoxLayout(jPanelSouth0, BoxLayout.Y_AXIS));
        jPanelSouth0.add(jPanelSouth1);
        jPanelSouth0.add(jPanelSouth2);
        serverFrame.getContentPane().add(BorderLayout.SOUTH, jPanelSouth0);

        // 设置窗口可见
        serverFrame.setVisible(true);
    }

    // 服务器运行
    public void startUp() {
        buildGUI();

        // 监听Start按钮，建立端口
        ActionListener startListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clientOutputStreams = new ArrayList<PrintWriter>();
                String aPort = portText.getText();

                if (aPort.equals("")) {
                    JOptionPane.showMessageDialog(serverFrame, "请输入正确的端口号！");
                } else {
                    try {
                        // 等待客户端连接的线程
                        Runnable serverRunnable = new Runnable() {
                            @Override
                            public void run() {
                                ServerSocket serverSocket;
                                try {
                                    serverSocket = new ServerSocket(Integer.parseInt(aPort));
                                    serverTextArea.append("正在等待客户端连接...\n");
                                    while (true) {
                                        Socket clientSocket = serverSocket.accept();
                                        serverTextArea.append("客户端已连接...\n");

                                        PrintWriter writer = new PrintWriter(clientSocket.getOutputStream());
                                        clientOutputStreams.add(writer);

                                        Thread t = new Thread(new ClientHandler(clientSocket));
                                        t.start();
                                    }
                                } catch (NumberFormatException | IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        };
                        Thread serverThread = new Thread(serverRunnable);
                        serverThread.start();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        };
        startButton.addActionListener(startListener);
        portText.addActionListener(startListener);

        // 监听nickname，设置昵称
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

        // 监听Say按钮，发送消息
        ActionListener SayListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String aText = sayText.getText();
                if (!aText.equals("")) {
                    aText = nickname + "：" + aText;
                    sendToEveryClient(aText);
                    serverTextArea.append(aText + "\n");
                    sayText.setText("");
                } else {
                    JOptionPane.showMessageDialog(serverFrame, "内容不能为空！");
                }
            }
        };
        sayButton.addActionListener(SayListener);
        sayText.addActionListener(SayListener);
    }

    // 多客户端的线程
    public class ClientHandler implements Runnable {
        BufferedReader bReader;
        Socket aSocket;

        public ClientHandler(Socket clientSocket) {
            try {
                aSocket = clientSocket;
                InputStreamReader isReader = new InputStreamReader(aSocket.getInputStream());
                bReader = new BufferedReader(isReader);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        @Override
        public void run() {
            String message;
            try {
                while ((message = bReader.readLine()) != null) {
                    sendToEveryClient(message);
                    serverTextArea.append(message + "\n");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    // 发送消息给所有客户端的方法
    private void sendToEveryClient(String message) {
        Iterator<PrintWriter> it = clientOutputStreams.iterator();
        while (it.hasNext()) {
            try {
                PrintWriter writer = it.next();
                writer.println(message);
                writer.flush();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    public static void main(String[] args) {
        server aServer = new server();
        aServer.startUp();
    }
}
