package hk.verify;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5encryption {
    public String MD5encrypt(String s)
    {
        if(s.isEmpty())
        {
            return "";
        }
        MessageDigest md5=null;
        try {
            md5=MessageDigest.getInstance("MD5");
            byte[] bytes=md5.digest(s.getBytes(StandardCharsets.UTF_8));
            StringBuilder res= new StringBuilder();
            for(byte b:bytes)
            {
                String temp=Integer.toHexString(b&0xff);
                if(temp.length()==1)
                {
                    temp="0"+temp;
                }
                res.append(temp);
            }
            return res.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static void main(String[] args) {
        MD5encryption md5encryption=new MD5encryption();
        String s="123";
        System.out.println(s);
        String res=md5encryption.MD5encrypt(s);
        System.out.println(res);
    }
}
