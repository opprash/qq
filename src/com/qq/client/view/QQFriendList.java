package com.qq.client.view;

import com.qq.client.tools.ManageQQChat;
import com.qq.common.Message;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class QQFriendList extends JFrame implements ActionListener,MouseListener{
    //处理第一张卡片
    JPanel jphy1,jphy2,jphy3;
    JButton jphy_jb1,jphy_jb2,jphy_jb3;
    JScrollPane jsp1;

    //处理第二张卡片
    JPanel jpmsr1,jpmsr2,jpmsr3;
    JButton jpmsr_jb1,jpmsr_jb2,jpmsr_jb3;
    JScrollPane jsp2;
    String owner;
    JLabel[] jbls;
    //把整个JFrame设置成Cardlayout
    CardLayout cl;
//    public static void main(String[] args)
//    {
//        QQFriendList qqFriendList = new QQFriendList("");
//    }

    //更新在线的好友
    public void updateFriend(Message ms)
    {
        String onLineFriend[] = ms.getCon().split(" ");

        for(int i=0;i<onLineFriend.length;i++)
        {
            jbls[Integer.parseInt(onLineFriend[i])-1].setEnabled(true);
        }
    }
    public QQFriendList(String ownerName)
    {
        this.owner = ownerName;
        //处理第一张卡片
        jphy_jb1 = new JButton("我的好友");
        jphy_jb2 = new JButton("陌生人");
        jphy_jb2.addActionListener(this);
        jphy_jb3 = new JButton("黑名单");

        //显示好友列表
        jphy1 = new JPanel(new BorderLayout());
        //假定有50个好友
        jphy2 = new JPanel(new GridLayout(50,1,4,4));
        //给jphy2初始化
        jbls = new JLabel[50];
        for(int i=0;i<jbls.length;i++)
        {
            jbls[i] = new JLabel(i+1+"",new ImageIcon("/home/sephiroth/文档/qq/image/touxiang.png"),JLabel.LEFT);
            jbls[i].setEnabled(false);
            if(jbls[i].getText().equals(ownerName))
            {
                jbls[i].setEnabled(true);
            }
            jbls[i].addMouseListener(this);
            jphy2.add(jbls[i]);
        }
        jphy3 = new JPanel(new GridLayout(2,1));

        jphy3.add(jphy_jb2);
        jphy3.add(jphy_jb3);

        jsp1 = new JScrollPane(jphy2);

        //对jphy1初始化
        jphy1.add(jphy_jb1,"North");
        jphy1.add(jsp1,"Center");
        jphy1.add(jphy3,"South");

        //
        //处理第二张卡片
        jpmsr_jb1 = new JButton("我的好友");
        jpmsr_jb1.addActionListener(this);
        jpmsr_jb2 = new JButton("陌生人");
        jpmsr_jb3 = new JButton("黑名单");

        //显示好友列表
        jpmsr1 = new JPanel(new BorderLayout());
        //假定有20个陌生人
        jpmsr2 = new JPanel(new GridLayout(20,1,4,4));
        //给jpmsr2初始化
        JLabel[] jbls2 = new JLabel[20];
        for(int i=0;i<jbls2.length;i++)
        {
            jbls2[i] = new JLabel(i+1+"",new ImageIcon("/home/sephiroth/文档/qq/image/touxiang.png"),JLabel.LEFT);
            jpmsr2.add(jbls2[i]);
        }
        jpmsr3 = new JPanel(new GridLayout(2,1));

        jpmsr3.add(jpmsr_jb1);
        jpmsr3.add(jpmsr_jb2);

        jsp2 = new JScrollPane(jpmsr2);

        //对jpmsr1初始化
        jpmsr1.add(jpmsr3,"North");
        jpmsr1.add(jsp2,"Center");
        jpmsr1.add(jpmsr_jb3,"South");

        cl = new CardLayout();
        this.setLayout(cl);
        this.add(jphy1,"1");
        this.add(jpmsr1,"2");
        this.setTitle(ownerName);
        this.setSize(140,400);
        this.setLocation(1000,200);
        this.setVisible(true);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == jphy_jb2)
        {
            cl.show(this.getContentPane(),"2");
        }
        else if(e.getSource() == jpmsr_jb1)
        {
            cl.show(this.getContentPane(),"1");
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //响应用户双击的时间,并得到好友的编号
        if(e.getClickCount() == 2)
        {
            //得到该好友的编号
            String friendNO = ((JLabel)e.getSource()).getText();
            //System.out.println("你希望和 "+friendNO+" 聊天");
            QQChat qqChat = new QQChat(this.owner,friendNO);

            //把聊天记录加入到
            ManageQQChat.addManageQQChat(this.owner+" "+friendNO,qqChat);

        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        JLabel jl =(JLabel) e.getSource();
        jl.setForeground(Color.BLUE);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        JLabel jl =(JLabel) e.getSource();
        jl.setForeground(Color.BLACK);
    }
}
