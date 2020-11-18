package hk.windows;

/*import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
/**
 * 需要进行多线程聊天
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
                    displyAppend("客户端：" + s);
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
        //enter.requestFocusInWindow(); //这块运行的话将会使得窗口在主窗口背后，无法显示
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
            displyAppend("尝试连接");
            Socket so = new Socket(host, port);
            String m;
            output = new ObjectOutputStream(so.getOutputStream());
            input = new ObjectInputStream(so.getInputStream());
            enter.setEnabled(true);
            do {
                m = (String) input.readObject();
                displyAppend("服务器端：" + m);
            } while (!isEndSession(m));
            output.writeObject("q");
            output.flush();
            enter.setEnabled(false);
            output.close();
            input.close();
            so.close();
        } catch (Exception h) {
            JOptionPane.showMessageDialog(this,"连接超时","提示",JOptionPane.CLOSED_OPTION);
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

    // 初始化组件
    public Chat() {
        nickname = "客户端";

        clientFrame = new JFrame();
        jPanelNorth = new JPanel();
        connectButton = new JButton("连接");
        clientTextArea = new JTextArea();
        scroller = new JScrollPane(clientTextArea);
        jPanelSouth0 = new JPanel();
        jPanelSouth1 = new JPanel();
        jPanelSouth2 = new JPanel();
        nicknameLabel = new JLabel("昵称", JLabel.LEFT);
        nicknameText = new JTextField(nickname, 30);
        nicknameButton = new JButton("确认");
        sayLabel = new JLabel("消息", JLabel.LEFT);
        sayText = new JTextField(30);
        sayButton = new JButton("确认");
    }

    // 构建GUI
    private void buildGUI() {
        // 窗口的设置
        clientFrame.setTitle("客户端");
        clientFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        clientFrame.setSize(550, 550);
        clientFrame.setLocationRelativeTo(null);

        // 北区的组件
        jPanelNorth.add(connectButton);
        clientFrame.getContentPane().add(BorderLayout.NORTH, jPanelNorth);

        // 中间的组件
        clientTextArea.setFocusable(false);
        scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        clientFrame.getContentPane().add(BorderLayout.CENTER, scroller);

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
        clientFrame.getContentPane().add(BorderLayout.SOUTH, jPanelSouth0);

        // 设置窗口可见
        clientFrame.setVisible(true);
    }

    // 客户端运行
    public void startUp() {
        buildGUI();

        // 接收服务器消息的线程
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

        // 监听Connect按钮，实现服务器的连接
        connectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    @SuppressWarnings("resource")
                    Socket clientSocket = new Socket(aServerIP, aServerPort);
                    InputStreamReader streamReader = new InputStreamReader(clientSocket.getInputStream());
                    reader = new BufferedReader(streamReader);
                    writer = new PrintWriter(clientSocket.getOutputStream());

                    clientTextArea.append("服务器已连接...\n");

                    Thread readerThread = new Thread(incomingReader);
                    readerThread.start();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(clientFrame, "连接不上服务器!\n请确认 IP 和 端口 输入正确。");
                }
            }
        });

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

        // 发送消息到服务器
        ActionListener SayListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String aText = sayText.getText();
                if (aText.equals("")) {
                    JOptionPane.showMessageDialog(clientFrame, "内容不能为空！");
                } else {
                    try {
                        writer.println(nickname + "：" + aText);
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
