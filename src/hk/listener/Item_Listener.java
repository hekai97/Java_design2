package hk.listener;

import hk.windows.*;
//�����ݿ�����е��ĸ�������в���
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
