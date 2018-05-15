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
		//chatWindow c1 = new chatWindow(loginName+", 欢迎加入Starsky聊天室"); //创建聊天窗口
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
			System.out.println("连接到主机：" + host + " ，端口号：" + port);
		    server = new Socket(host, port);
			new chatWindow(loginName + "欢迎你进入Starsky聊天室"); //启动聊天窗口
		    //接收人数
		    DataInputStream count = new DataInputStream(server.getInputStream());
		    System.out.println(count.readUTF());
//		    count.close();
		    
		    s_recvThread s = new s_recvThread(server); //连接的套接字 打开接收线程
		    s.start();
		    
		    System.out.println("远程主机地址：" + server.getRemoteSocketAddress());
		    OutputStream outToServer = server.getOutputStream();
		    DataOutputStream out = new DataOutputStream(outToServer);
		    out.writeUTF("-----------欢迎 "+loginName + " 加入聊天室");
		    while(true)
		    {
		    	//System.out.print("我说: ");
		    	sendBuff = loginName +": "+ input.nextLine();
		    	out.writeUTF(sendBuff);
		    }
//		    input.close();
		}
		catch(ConnectException ConnectEof)
		{
			System.out.println("连接错误");
			JOptionPane.showMessageDialog(null, "连接失败!\n可能是服务器地址或者端口号不正确.\n即将退出...", "错误: ", 0);
			System.exit(-1);
		}catch(UnknownHostException UnknownHostExceptionEof)
		{
			JOptionPane.showMessageDialog(null, "连接失败!\n可能是服务器地址或者端口号不正确.\n即将退出...", "错误: ", 0);
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