package ch1;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

class chatWindow extends JFrame implements KeyListener
{
	static JTextArea jt = null;
	JScrollPane jsp = null;    //��ӹ�����
	JTextArea jt2 = null;
	JButton b1 = null;
	static JScrollBar jsVB = null;
	public static JFrame jf;
	String windowTitle="";

	public static void addMessage(String NewMessage)
	{
		jt.append(NewMessage+"\n");
		//chatWindow.jsVB.setValues(arg0, arg1, arg2, arg3);
		jsVB.setValue(jsVB.getMaximum());  
	}
	
	public chatWindow(String windowTitle) {
		// TODO �Զ����ɵĹ��캯�����
		this.windowTitle = windowTitle;
    	System.out.println("��������");
    	jf = new JFrame(windowTitle);
		Container conn = jf.getContentPane();
		conn.setLayout(null);
			
		jt = new JTextArea();
		jt.setBounds(5,5,585,350);
		jt.setEditable(false);
		
		jsp = new JScrollPane(jt);
		jsVB = jsp.getVerticalScrollBar();
		jsp.setBounds(5,5,585,350);
		jf.add(jsp);
		
		jt2 = new JTextArea();
		jt2.setBounds(5,380,585,60);
		jf.add(jt2);
		
		b1 = new JButton("����");
		b1.setBounds(480,445,90,25);
		jf.add(b1);
		
		jt2.addKeyListener(this);
		
		b1.addActionListener(new ActionListener() { //���Ͱ�ť
			public void actionPerformed(ActionEvent e)
			{
				OutputStream outToServer;
				try 
				{
					outToServer = s_connect.server.getOutputStream();
					DataOutputStream out = new DataOutputStream(outToServer);
				    out.writeUTF(s_connect.loginName +": "+ jt2.getText());
				    jt2.setText(""); //�������֮�����
				} 
				catch (IOException e1) {
					// TODO �Զ����ɵ� catch ��
					e1.printStackTrace();
				}
			    
			}
			});
		
		System.out.println("�����������");
		
		jf.setVisible(true);
		jf.setBounds(200,200,600,515);
		jf.setResizable(false); //	��ֹ��� ������
		
		jf.addWindowListener(new WindowAdapter() 
		{
			public void windowClosing(WindowEvent e) 
			{
			   int value=JOptionPane.showConfirmDialog(null, "ȷ��Ҫ�ر���");
			    if (value==JOptionPane.OK_OPTION) 
			    {
			    	try 
			    	{
			    		OutputStream outToServer = s_connect.server.getOutputStream();
				    	DataOutputStream out = new DataOutputStream(outToServer);
						out.writeUTF("----------- " + s_connect.loginName + " �뿪������");
					} 
			    	catch (IOException e1) 
			    	{
						e1.printStackTrace();
					}
			    	System.exit(0);
			    }if(value == JOptionPane.CANCEL_OPTION)
			    {
			    	
			    }else
			    {
			    	
			    }
			}
		});
		//jf.setDefaultCloseOperation(EXIT_ON_CLOSE);
		jf.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	}

	@Override
	public void keyPressed(KeyEvent arg0) 
	{
		if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
			OutputStream outToServer;
			try 
			{
				outToServer = s_connect.server.getOutputStream();
				DataOutputStream out = new DataOutputStream(outToServer);
			    out.writeUTF(s_connect.loginName +": "+ jt2.getText());
			    jt2.setText(""); //�������֮�����
			} 
			catch (IOException e1) {
				e1.printStackTrace();
			}
			finally 
			{
				jt2.setText(jt2.getText().substring(0, jt2.getText().length()-1));
				try 
				{
					jt2.setText(jt2.getText().substring(0, jt2.getText().length()-1));
				}catch(IndexOutOfBoundsException OutIndexEof)
				{
					System.out.println("���ͻ����� ����Խ�� ����ûӰ��");
				}
			}
			
        }
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO �Զ����ɵķ������
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO �Զ����ɵķ������
		
	}
}