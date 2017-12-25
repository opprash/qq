package com.qq.client.tools;

import com.qq.client.view.QQChat;

import java.util.*;

public class ManageQQChat {

    private static HashMap hm = new HashMap<String,QQChat>();

    //加入
    public static void addManageQQChat(String loginIDAndFriendID,QQChat qqChat)
    {
        hm.put(loginIDAndFriendID,qqChat);
    }
    //取出
    public static QQChat getManageQQChat(String loginIDAndFriendID)
    {
        return (QQChat)hm.get(loginIDAndFriendID);
    }
}
