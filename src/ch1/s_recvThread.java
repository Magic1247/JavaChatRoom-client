package ch1;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;

public class s_recvThread extends Thread
{
	private Thread t = null;
	private String ThreadName = "s_recvThread";
	private Socket server;
	//private String loginName = "";
	
	s_recvThread(Socket server) {
		// TODO �Զ����ɵĹ��캯�����
		this.server = server;
	}

	public void run()
	{
		DataInputStream in;
		String recvBuff = "";
		try 
		{
			in = new DataInputStream(server.getInputStream());
			while(true)
			{
				recvBuff = in.readUTF() + "\n";
				System.out.println(recvBuff);
				chatWindow.jf.setTitle("��ӭ��, "+ s_connect.loginName+",  " + in.readUTF());
				chatWindow.addMessage(recvBuff);
				
				chatWindow.jf.action(null, recvBuff);
				
//				chatWindow.jt.addFocusListener(new FocusListener() 
//				{
//					public void focusLost(FocusEvent e) 
//					{
//						
//					}
//					public void focusGained(FocusEvent e) 
//					{
//						chatWindow.jf.toFront(); //ʹ���ڻ�ȡ���� ���ǻ�������
//					}
//				});
//				
				
				
				//chatWindow.jf.toFront(); //ʹ���ڻ�ȡ���� ���ǻ�������
				//chatWindow.jf.requestFocus();
			}
			
		}catch(SocketException SocketEof)
		{
			System.out.println("��������Ͽ�����...");
			System.exit(-1);
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void start()
	{
		if(t == null)
		{
			t = new Thread(this,ThreadName);
			t.start();
		}
	}
}