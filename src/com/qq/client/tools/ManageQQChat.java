package com.qq.client.tools;

import com.qq.client.view.QQChat;

import java.util.*;

public class ManageQQChat {

    private static HashMap hm = new HashMap<String,QQChat>();

    //����
    public static void addManageQQChat(String loginIDAndFriendID,QQChat qqChat)
    {
        hm.put(loginIDAndFriendID,qqChat);
    }
    //ȡ��
    public static QQChat getManageQQChat(String loginIDAndFriendID)
    {
        return (QQChat)hm.get(loginIDAndFriendID);
    }
}
