package com.qq.client.view;

import com.qq.client.tools.ManageQQChat;
import com.qq.common.Message;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class QQFriendList extends JFrame implements ActionListener,MouseListener{
    //�����һ�ſ�Ƭ
    JPanel jphy1,jphy2,jphy3;
    JButton jphy_jb1,jphy_jb2,jphy_jb3;
    JScrollPane jsp1;

    //����ڶ��ſ�Ƭ
    JPanel jpmsr1,jpmsr2,jpmsr3;
    JButton jpmsr_jb1,jpmsr_jb2,jpmsr_jb3;
    JScrollPane jsp2;
    String owner;
    JLabel[] jbls;
    //������JFrame���ó�Cardlayout
    CardLayout cl;
//    public static void main(String[] args)
//    {
//        QQFriendList qqFriendList = new QQFriendList("");
//    }

    //�������ߵĺ���
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
        //�����һ�ſ�Ƭ
        jphy_jb1 = new JButton("�ҵĺ���");
        jphy_jb2 = new JButton("İ����");
        jphy_jb2.addActionListener(this);
        jphy_jb3 = new JButton("������");

        //��ʾ�����б�
        jphy1 = new JPanel(new BorderLayout());
        //�ٶ���50������
        jphy2 = new JPanel(new GridLayout(50,1,4,4));
        //��jphy2��ʼ��
        jbls = new JLabel[50];
        for(int i=0;i<jbls.length;i++)
        {
            jbls[i] = new JLabel(i+1+"",new ImageIcon("/home/sephiroth/�ĵ�/qq/image/touxiang.png"),JLabel.LEFT);
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

        //��jphy1��ʼ��
        jphy1.add(jphy_jb1,"North");
        jphy1.add(jsp1,"Center");
        jphy1.add(jphy3,"South");

        //
        //����ڶ��ſ�Ƭ
        jpmsr_jb1 = new JButton("�ҵĺ���");
        jpmsr_jb1.addActionListener(this);
        jpmsr_jb2 = new JButton("İ����");
        jpmsr_jb3 = new JButton("������");

        //��ʾ�����б�
        jpmsr1 = new JPanel(new BorderLayout());
        //�ٶ���20��İ����
        jpmsr2 = new JPanel(new GridLayout(20,1,4,4));
        //��jpmsr2��ʼ��
        JLabel[] jbls2 = new JLabel[20];
        for(int i=0;i<jbls2.length;i++)
        {
            jbls2[i] = new JLabel(i+1+"",new ImageIcon("/home/sephiroth/�ĵ�/qq/image/touxiang.png"),JLabel.LEFT);
            jpmsr2.add(jbls2[i]);
        }
        jpmsr3 = new JPanel(new GridLayout(2,1));

        jpmsr3.add(jpmsr_jb1);
        jpmsr3.add(jpmsr_jb2);

        jsp2 = new JScrollPane(jpmsr2);

        //��jpmsr1��ʼ��
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
        //��Ӧ�û�˫����ʱ��,���õ����ѵı��
        if(e.getClickCount() == 2)
        {
            //�õ��ú��ѵı��
            String friendNO = ((JLabel)e.getSource()).getText();
            //System.out.println("��ϣ���� "+friendNO+" ����");
            QQChat qqChat = new QQChat(this.owner,friendNO);

            //�������¼���뵽
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
