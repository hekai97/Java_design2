package hk.listener;

import hk.windows.*;
/***********************************************************
 * 版权所有 (C)2020, hekai
 *
 * 文件名称：MenuItemListener.java
 * 文件标识：无
 * 内容摘要：对数据库操作中的四个子项进行操作
 * 其它说明：无
 * 当前版本： V1.0
 * 作   者：贺凯
 * 完成日期： 20201115
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
