package com.qq.client.tools;

import java.util.*;

public class ManageClientConServerThread {

    private static HashMap hm = new HashMap<String,ClientConServerThread>();

    //�Ѵ����õ�ͨѶ�̷߳��뵽HashMap��
    public static void addClientConServerThread(String QQName,ClientConServerThread ccst)
    {
        hm.put(QQName,ccst);
    }

    //����ͨ��QQNameȡ�ø��߳�
    public static ClientConServerThread getClientConServerThread(String QQName)
    {
        return (ClientConServerThread)hm.get(QQName);
    }
}
