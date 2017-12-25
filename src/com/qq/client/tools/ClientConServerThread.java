package com.qq.client.tools;

import com.qq.client.view.QQChat;
import com.qq.client.view.QQFriendList;
import com.qq.common.Message;
import com.qq.common.MessageType;

import java.net.*;
import java.io.*;
public class ClientConServerThread extends Thread{

    private Socket s;


    public ClientConServerThread(Socket s)
    {
        this.s = s;
    }

    public Socket getS() {
        return s;
    }

    public void run()
    {
        while(true)
        {
            //不停地读取从服务器端发来的消息
            try {
                ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
                Message ms = (Message)ois.readObject();
                System.out.println("读取到从服务器发来的消息"+ ms.getGetter()+" 给 "+ms.getSender()+" 内容 "+ms.getCon());

                if(ms.getMesType().equals(MessageType.message_comm_mes))
                {
                    //把从服务器获得的消息显示出来
                    QQChat qqChat = ManageQQChat.getManageQQChat(ms.getGetter()+" "+ms.getSender());
                    //显示
                    qqChat.showMessage(ms);

                }
                else if(ms.getMesType().equals(MessageType.message_ret_onLineFriend))
                {
                    String con = ms.getCon();
                    String friend[] = con.split(" ");
                    String getter = ms.getGetter();
                    //修改相应的好友列表
                    QQFriendList qqFriendList = ManageQQFriend.getQQFriendList(getter);

                    //更新在线好友
                    if(qqFriendList != null)
                    {qqFriendList.updateFriend(ms);}
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

}
