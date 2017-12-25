package com.qq.client.view;

import com.qq.common.*;
import com.qq.client.model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Date;
import com.qq.client.tools.*;
public class QQChat extends JFrame implements ActionListener{

    JTextArea jta;
    JTextField jtf;
    JButton jb;
    JPanel jp;
    String ownerName;
    String friend;

    public static void main(String[] args)
    {
     //   QQChat qqChat = new QQChat("");
    }

    public QQChat(String ownerName,String friend)
    {
        this.ownerName = ownerName;
        this.friend = friend;
        jta = new JTextArea();
        jtf = new JTextField(15);
        jb = new JButton("����");
        jb.addActionListener(this);
        jp = new JPanel();
        jp.add(jtf);
        jp.add(jb);

        this.add(jta,"Center");
        this.add(jp,"South");
        this.setTitle(ownerName+" ���ں� "+friend+" ����");
        this.setSize(300,200);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == jb)
        {
            //����û�����˷��Ͱ�ť
            Message ms = new Message();
            ms.setMesType(MessageType.message_comm_mes);
            ms.setSender(this.ownerName);
            ms.setGetter(this.friend);
            ms.setCon(jtf.getText());
            ms.setSendTime(new java.util.Date().toString());
            //���͸�������
            try {
                ObjectOutputStream oos = new ObjectOutputStream(ManageClientConServerThread.getClientConServerThread(ownerName).getS().getOutputStream());
                oos.writeObject(ms);
                this.jta.append(this.ownerName+" �� "+this.friend+" : "+jtf.getText()+"\r\n"+ new java.util.Date().toString()+"\r\n");

            } catch (IOException e1) {
                e1.printStackTrace();
            }

        }
    }

    //��ʾ��Ϣ
    public void showMessage(Message ms)
    {
        String info = ms.getSender()+" �� "+ms.getGetter()+" : "+ms.getCon()+"\r\n"+ms.getSendTime()+"\r\n";
        this.jta.append(info);
    }

//    @Override
//    public void run() {
//        while(true)
//        {
//            //��ȡ
//            try {
//                ObjectInputStream ois = new ObjectInputStream(QQClientConServer.s.getInputStream());
//
//                Message ms = (Message)ois.readObject();
//
//                //��ʾ
//                String info = ms.getSender()+" �� "+ms.getGetter()+" : "+ms.getCon()+"\r\n"+ms.getSendTime()+"\r\n";
//                this.jta.append(info);
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            } catch (ClassNotFoundException e) {
//                e.printStackTrace();
//            }
//        }

}
