package hk.listener;

import hk.windows.Chat;

/*import java.net.Inet4Address;
import java.net.UnknownHostException;

public class Chat_listener implements Runnable {
    String s;
    @Override
    public void run() {
        //获取本机ip，直接进行连接，避免每次输入的麻烦
        try{
            Inet4Address address= (Inet4Address) Inet4Address.getLocalHost();
            s=address.toString();
        }catch(UnknownHostException e)
        {
            e.printStackTrace();
        }
        //将字符串进行分割，只要ip地址段
        String[] str=s.split("/");
        Chat chat=new Chat("反馈");
        chat.chatRun(str[1],5000);
    }
}*/
public class Chat_listener{

}

/**
 * 这个类暂时用不到
 * */
