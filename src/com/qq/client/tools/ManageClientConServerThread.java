package com.qq.client.tools;

import java.util.*;

public class ManageClientConServerThread {

    private static HashMap hm = new HashMap<String,ClientConServerThread>();

    //把创建好的通讯线程放入到HashMap中
    public static void addClientConServerThread(String QQName,ClientConServerThread ccst)
    {
        hm.put(QQName,ccst);
    }

    //可以通过QQName取得该线程
    public static ClientConServerThread getClientConServerThread(String QQName)
    {
        return (ClientConServerThread)hm.get(QQName);
    }
}
