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
            //��ͣ�ض�ȡ�ӷ������˷�������Ϣ
            try {
                ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
                Message ms = (Message)ois.readObject();
                System.out.println("��ȡ���ӷ�������������Ϣ"+ ms.getGetter()+" �� "+ms.getSender()+" ���� "+ms.getCon());

                if(ms.getMesType().equals(MessageType.message_comm_mes))
                {
                    //�Ѵӷ�������õ���Ϣ��ʾ����
                    QQChat qqChat = ManageQQChat.getManageQQChat(ms.getGetter()+" "+ms.getSender());
                    //��ʾ
                    qqChat.showMessage(ms);

                }
                else if(ms.getMesType().equals(MessageType.message_ret_onLineFriend))
                {
                    String con = ms.getCon();
                    String friend[] = con.split(" ");
                    String getter = ms.getGetter();
                    //�޸���Ӧ�ĺ����б�
                    QQFriendList qqFriendList = ManageQQFriend.getQQFriendList(getter);

                    //�������ߺ���
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
