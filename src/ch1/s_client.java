package ch1;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;



class s_login extends JFrame
{
	private String host = "";
	private int port = 0;
	private String loginName = "";
	
	s_login(String windowsTitle)
	{
		JFrame jf = new JFrame(windowsTitle);
		Container conn = jf.getContentPane();
		conn.setLayout(null);
		JLabel l1 = new JLabel("��������ǳ�: ");
		l1.setBounds(10,10,100,25);
		conn.add(l1);
		
		JTextField jtf = new JTextField(15);
		jtf.setBounds(100,10,160,25);
		conn.add(jtf);
		
		JLabel l2 = new JLabel("������: ");
		l2.setBounds(10,40,80,25);
		conn.add(l2);
		
		JTextField jtf2 = new JTextField("47.95.4.219");
		jtf2.setBounds(60,40,115,25);
		conn.add(jtf2);
		
		JLabel l3 = new JLabel("�˿�: ");
		l3.setBounds(180,40,40,25);
		conn.add(l3);
		
		JTextField jtf3 = new JTextField("1235");
		jtf3.setBounds(210,40,50,25);
		conn.add(jtf3);
		
		JButton b1 = new JButton("��������");
		b1.setBounds(40,75,200,35);
		conn.add(b1);
		
		//chatWindow chat1 = new chatWindow();
		
		b1.addActionListener(new ActionListener() { //����˵�½
		public void actionPerformed(ActionEvent e)
		{
			if(jtf.getText().equals(""))
			{
				JOptionPane.showMessageDialog(null, "�ǳƲ�����Ϊ��Ŷ~", "����: ", 0);
				return ;
			}
			System.out.println("��½��ʼ");
			loginName = jtf.getText();
			host = jtf2.getText();
			port = Integer.parseInt(jtf3.getText());
			
			jf.dispose();
			s_connect s1 = new s_connect(loginName,host,port);
			s1.start();
		}
		});
		
		jf.setResizable(false); //��ֹ��� ������
		
		jf.setVisible(true);
		jf.setBounds(200,200,300,160);
		jf.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
}

public class s_client 
{
	public static void main(String[] args) 
	{
		new s_login("��½������ v0.5.30");
	}
}
	