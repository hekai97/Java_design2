package hk.listener;

import hk.windows.*;
//�����ݿ�����е��ĸ�������в���
public class MenuItemListener {
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
