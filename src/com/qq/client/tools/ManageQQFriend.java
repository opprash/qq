package com.qq.client.tools;

import com.qq.client.view.QQFriendList;

import java.util.*;

public class ManageQQFriend {

    private static HashMap hm = new HashMap<String,QQFriendList>();

    public static void addQQFriendList(String qqName,QQFriendList qqFriendList)
    {
        hm.put(qqName,qqFriendList);
    }

    public static QQFriendList getQQFriendList(String qqName)
    {
        return (QQFriendList)hm.get(qqName);
    }
}
