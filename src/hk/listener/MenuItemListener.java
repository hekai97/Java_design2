package hk.listener;

import hk.windows.*;
/***********************************************************
 * ��Ȩ���� (C)2020, hekai
 *
 * �ļ����ƣ�MenuItemListener.java
 * �ļ���ʶ����
 * ����ժҪ�������ݿ�����е��ĸ�������в���
 * ����˵������
 * ��ǰ�汾�� V1.0
 * ��   �ߣ��ؿ�
 * ������ڣ� 20201115
 **********************************************************/
public class MenuItemListener {
    public void doIncrease(){
        new Insert();
    }
    public void doDelete(){
        new Delete();
    }
    public void doUpdate(){
        new Update();
    }
    public void doInquire(){
        new Inquire();
    }
}
