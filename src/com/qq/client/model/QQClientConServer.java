package com.qq.client.model;


import java.net.*;
import java.io.*;

import com.qq.client.tools.ClientConServerThread;
import com.qq.common.*;
import com.qq.client.tools.*;
public class QQClientConServer {

    public Socket s;

    public boolean SendLoginInfoToServer(Object o)
    {
        boolean b = false;
        try {
            s = new Socket("127.0.0.1",8888);
            ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
            oos.writeObject(o);

            ObjectInputStream ois = new ObjectInputStream(s.getInputStream());

            Message ms = (Message)ois.readObject();
            //这里就是验证用户登录的地方
            if(ms.getMesType().equals("1"))
            {
                //创建一个该QQ和服务器端保持通讯连接的线程
                ClientConServerThread ccst = new ClientConServerThread(s);
                //启动该通讯线程
                ccst.start();
                ManageClientConServerThread.addClientConServerThread(((User)o).getUserName(),ccst);
                b = true;
            }
            System.out.println(ms.getMesType().toString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return b;
    }

}
