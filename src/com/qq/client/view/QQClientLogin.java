/**
 * QQ�ͻ��˵�¼����
 */

package com.qq.client.view;

import com.qq.client.model.QQClientConServer;
import com.qq.client.model.QQClientUser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.qq.client.tools.ManageClientConServerThread;
import com.qq.client.tools.ManageQQFriend;
import com.qq.common.*;
public class QQClientLogin extends JFrame implements ActionListener, MouseListener {

    //����
    JLabel jbl1;

    //�в�
    JPanel jp2;
    JTextField jp2_tf;
    JPasswordField jp2_jpf;
    JLabel jp2_jbl1;
    JLabel jp2_jbl2;
    JLabel jp2_jbl3;
    JLabel jp2_jbl4;
    JLabel jp2_jbl5;
    JCheckBox jp2_jck1;
    JCheckBox jp2_jck2;
    //�ϲ�
    JPanel jp1;
    JButton jp1_jb;

    public static void main(String[] args) {
        QQClientLogin qqclientLogin = new QQClientLogin();
    }


    public QQClientLogin() {
        //������
        jbl1 = new JLabel(new ImageIcon("/home/sephiroth/�ĵ�/qq/image/001.png"));

        //�����в�
        jp2 = new JPanel(new GridLayout(3,3));
        jp2_jbl1 = new JLabel("�˺�",JLabel.CENTER);
        jp2_jbl2 = new JLabel("����",JLabel.CENTER);
        jp2_jbl3 = new JLabel("ע���˺�",JLabel.CENTER);
        jp2_jbl3.addMouseListener(this);
        jp2_jbl3.setForeground(Color.BLUE);
        jp2_jbl4 = new JLabel("��������",JLabel.CENTER);
        jp2_jbl4.setForeground(Color.BLUE);
        jp2_jck1 = new JCheckBox("��ס����");
        jp2_jck2 = new JCheckBox("�Զ���¼");
        jp2_tf = new JTextField();
        jp2_jpf = new JPasswordField();
        jp2_jbl5 = new JLabel();

        jp2.add(jp2_jbl1);
        jp2.add(jp2_tf);
        jp2.add(jp2_jbl3);
        jp2.add(jp2_jbl2);
        jp2.add(jp2_jpf);
        jp2.add(jp2_jbl4);
        jp2.add(jp2_jbl5);
        jp2.add(jp2_jck1);
        jp2.add(jp2_jck2);

        //�����ϲ�
        jp1 = new JPanel();
        jp1_jb = new JButton(new ImageIcon("/home/sephiroth/�ĵ�/qq/image/002.png"));
        //��Ӧ�û������¼
        jp1_jb.addActionListener(this);
        jp1.add(jp1_jb);


        this.add(jbl1,"North");
        this.add(jp2,"Center");
        this.add(jp1,"South");
        this.setSize(430, 300);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //����û������¼
        if(e.getSource() == jp1_jb)
        {
            QQClientUser qqClientUser = new QQClientUser();
            User u = new User();
            u.setUserName(jp2_tf.getText().trim());
            u.setUserPasswd(new String(jp2_jpf.getPassword()));
            u.setType(1);
            if(qqClientUser.checkUser(u))
            {
                //����һ��Ҫ�󷵻����ߺ��ѵ������
                try {
                    //�Ѵ��������б�������ǰ
                    QQFriendList qqFriendList = new QQFriendList(u.getUserName());
                    ManageQQFriend.addQQFriendList(u.getUserName(),qqFriendList);

                    ObjectOutputStream oos = new ObjectOutputStream(ManageClientConServerThread.getClientConServerThread
                            (u.getUserName()).getS().getOutputStream());

                    //��һ��Message
                    Message ms = new Message();
                    ms.setMesType(MessageType.message_get_onLineFriend);
                    //ָ��Ҫ��qq����ĺ������
                    ms.setSender(u.getUserName());
                    oos.writeObject(ms);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

                //ͬʱ�رյ�¼����
                this.dispose();
            }
            else{
                JOptionPane.showMessageDialog(this,"�û������������");
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource() == jp2_jbl3)
        {
            new QQRegister();
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

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}