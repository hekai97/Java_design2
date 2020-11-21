package hk.verify;

import hk.model.UP;
import hk.sql.DBCon;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserAndPassword {
    List<UP> list = new ArrayList<>();
    /**
     * 该函数从数据库中取值
     * 然后将取到的用户名和密码放在ArrayList中
     * */
    private void getresult()
    {
        String sql="select * from java.user_password";
        Connection con= DBCon.getConnection();
        try{
            PreparedStatement preparedStatement=con.prepareStatement(sql);
            preparedStatement.execute();
            ResultSet st=preparedStatement.executeQuery();
            while(st.next())
            {
                UP up=new UP();
                up.setUser(st.getString("User"));
                up.setPassword(st.getString("Password"));
                list.add(up);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 该函数的作用是用来判断传入的用户名和密码是否正确
     * 返回值：1、代表用户名正确，密码正确
     *       2、代表用户名正确但是密码错误
     *       3、代表用户名不正确
     *       */
    public int verifyUserPassword(String user, String password){
        getresult();
        MD5encryption md5encryption=new MD5encryption();
        for (UP temp : list) {
            if (Objects.equals(user, temp.getUser())) {
                if (Objects.equals(md5encryption.MD5encrypt(password), temp.getPassword())) {
                    return 1;
                } else {
                    return 2;
                }
            }
        }
        return 3;
    }
    public boolean verifyUser(String User)
    {
        getresult();
        for(UP temp:list){
            if(Objects.equals(User,temp.getUser())){
                return true;
            }
        }
        return false;
    }
}
