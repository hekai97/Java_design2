package hk.listener;

import hk.windows.*;
//对数据库操作中的四个子项进行操作
public class Item_Listener {
    public void doIncrease(){
        new Insert();
    }
    public void doDelete(){
        new Delete();
    }
    public void doUpdate(){
        new Upadte();
    }
    public void doInquire(){
        new Inquire();
    }
}
