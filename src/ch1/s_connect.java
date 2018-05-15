package ch1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class s_connect extends Thread
{
	private String host = "127.0.0.1"; //localhost
	private int port = 1235;
	public static String loginName = "";
	private Thread t = null;
	private String ThreadName = "s_connectThread";
	public String chatWindowTitle;
	public static Socket server = null;
	
	
	
	s_connect(String loginName,String host,int port)
	{
		//chatWindow c1 = new chatWindow(loginName+", ��ӭ����Starsky������"); //�������촰��
		//chatWindow();
		this.loginName = loginName;
		this.host = host;
		this.port = port;
		
	}
	
	
	
	public void run()
	{
		Scanner input = new Scanner(System.in);
		String sendBuff = "";
		try {
			System.out.println("���ӵ�������" + host + " ���˿ںţ�" + port);
		    server = new Socket(host, port);
			new chatWindow(loginName + "��ӭ�����Starsky������"); //�������촰��
		    //��������
		    DataInputStream count = new DataInputStream(server.getInputStream());
		    System.out.println(count.readUTF());
//		    count.close();
		    
		    s_recvThread s = new s_recvThread(server); //���ӵ��׽��� �򿪽����߳�
		    s.start();
		    
		    System.out.println("Զ��������ַ��" + server.getRemoteSocketAddress());
		    OutputStream outToServer = server.getOutputStream();
		    DataOutputStream out = new DataOutputStream(outToServer);
		    out.writeUTF("-----------��ӭ "+loginName + " ����������");
		    while(true)
		    {
		    	//System.out.print("��˵: ");
		    	sendBuff = loginName +": "+ input.nextLine();
		    	out.writeUTF(sendBuff);
		    }
//		    input.close();
		}
		catch(ConnectException ConnectEof)
		{
			System.out.println("���Ӵ���");
			JOptionPane.showMessageDialog(null, "����ʧ��!\n�����Ƿ�������ַ���߶˿ںŲ���ȷ.\n�����˳�...", "����: ", 0);
			System.exit(-1);
		}catch(UnknownHostException UnknownHostExceptionEof)
		{
			JOptionPane.showMessageDialog(null, "����ʧ��!\n�����Ƿ�������ַ���߶˿ںŲ���ȷ.\n�����˳�...", "����: ", 0);
			System.exit(-1);
		}catch(IOException e)
		{
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