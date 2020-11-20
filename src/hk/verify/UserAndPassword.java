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

    public int verifyUser(String user,String password){
        getresult();
        MD5encryption md5encryption=new MD5encryption();
        for (UP temp : list) {
            if (Objects.equals(user, temp.getUser())) {
                if (Objects.equals(md5encryption.MD5encrypt(password), temp.getPassword())) {
                    return 1;
                } else {
                    return 2;
                }
            } else {
                return 3;
            }
        }
        return 0;
    }
}
