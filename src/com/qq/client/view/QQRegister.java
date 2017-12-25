package com.qq.client.view;

import com.qq.client.model.QQClientConServer;
import com.qq.client.model.QQClientUser;
import com.qq.common.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.Socket;

public class QQRegister extends JFrame implements ActionListener{

    JPanel jp1,jp2,jp3,jp4,jp5;
    JButton jb1,jb2;
    JLabel jbl1,jbl2,jbl3,jbl;
    JTextField jtf;
    JPasswordField jpf1,jpf2;

    public QQRegister()
    {
        jp1 = new JPanel();
        jp2 = new JPanel();
        jp3 = new JPanel();
        jp4 = new JPanel();
        jp5 = new JPanel();
        jb1 = new JButton("确认");
        jb1.addActionListener(this);
        jb2 = new JButton("清除");
        jb2.addActionListener(this);
        jbl1 = new JLabel("账号",JLabel.CENTER);
        jbl2 = new JLabel("密码",JLabel.CENTER);
        jbl3 = new JLabel("确认密码",JLabel.CENTER);
        jbl = new JLabel("注册",JLabel.CENTER);
        jtf = new JTextField(20);
        jpf1 = new JPasswordField(20);
        jpf2 = new JPasswordField(20);

        jp1.add(jbl);
        jp2.add(jbl1);
        jp2.add(jtf);
        jp3.add(jbl2);
        jp3.add(jpf1);
        jp4.add(jbl3);
        jp4.add(jpf2);
        jp5.add(jb1);
        jp5.add(jb2);

        this.setLayout(new GridLayout(5,1));
        this.add(jp1);
        this.add(jp2);
        this.add(jp3);
        this.add(jp4);
        this.add(jp5);
        this.setSize(300,200);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


    }

    public static void main(String [] args)
    {
        QQRegister qqRegister = new QQRegister();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == jb1)
        {
            if(String.valueOf(jpf1.getPassword()).equals(String.valueOf(jpf2.getPassword()))) {
                QQClientUser qqClientUser = new QQClientUser();
                User u = new User();
                u.setUserName(jtf.getText().trim());
                u.setUserPasswd(new String(jpf2.getPassword()));
                u.setType(2);
                if(qqClientUser.checkUser(u))
                {
                    JOptionPane.showMessageDialog(this,"注册成功");
                }
                else
                {
                    JOptionPane.showMessageDialog(this,"注册失败,该用户名已被使用");
                }
            }
            else{
                JOptionPane.showMessageDialog(this,"两次输入密码不一致");
            }
        }
        else  if(e.getSource() == jb2)
        {
            jtf.setText("");
            jpf1.setText("");
            jpf2.setText("");
        }
    }
}
