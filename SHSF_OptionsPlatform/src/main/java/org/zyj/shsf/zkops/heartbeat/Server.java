package org.zyj.shsf.zkops.heartbeat;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import org.zyj.sfsh.common.StringHelper;



/**
 * 参考文章：https://www.cnblogs.com/rond/p/3565113.html
 *
 */
public class Server {

	private int port = 65432;
	
	private volatile boolean running = false;
	
	private ServerSocket serverSocket;
	
	public static void main(String[] args) {
		Server s = new Server();
		s.start();
	}
	
	public void start() {
		if (running) {
			return;
		}
		running = true;
		try {
			serverSocket = new ServerSocket(port, 5);//backlog客户端连接请求的队列长度
			while (running) {
				//从连接队列中取出一个连接，如果没有则等待
				Socket socket = serverSocket.accept();
				System.out.println("收到来自" + ":" + socket.getPort() + "的连接");
				//党接收到客户但socket连接后，启动另外一个线程专门处理改客户端的socket连接
				new Thread(new KeepAliveListenerSocket(socket)).start();
				
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void stop() {
		if (running) {
			running = false;
		}
		if (serverSocket != null) {
			try {
				serverSocket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	class KeepAliveListenerSocket implements Runnable {
		private Socket socket;
		boolean flag = true;
		long lastReceiveTime;
		
		// 如果10秒内没收到客户端的信息，就自动端口连接
		// 但是这里检测用的是11秒
		private long receiveTimeDelay = 11000;
		private long checkDelay = 100;
		
		private String remoteAppServerIP = "";
		
		public KeepAliveListenerSocket(Socket socket) {
			this.socket = socket;
			lastReceiveTime = System.currentTimeMillis();
		}
		
		@Override
		public void run() {
			while(running && flag) {
				if ((System.currentTimeMillis() - lastReceiveTime) > receiveTimeDelay ) {
					if(!StringHelper.isEmpty(remoteAppServerIP)) {
						//将异常服务器写入DB， 并产生告警信息，告警信息可以在其他的子系统里面生成。
					}
					//关闭服务器端socket
					shutdownSocket();
				}else {
					try {
						InputStream in = socket.getInputStream();
						if (in.available() > 0) {//如果有客户端发送的消息
							//接受客户端发送过来的心跳信息
							ObjectInputStream ois = new ObjectInputStream(in);
							Object obj = ois.readObject();
							
							//重新设置最后接受消息的时间
							lastReceiveTime = System.currentTimeMillis();
							remoteAppServerIP = socket.getInetAddress().getHostAddress();
							System.out.println("接收客户端的信息来自"
									+ remoteAppServerIP
									+ ":" + socket.getPort() + "的信息，" + obj);

							//响应心跳信息给客户端
							ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
							oos.writeObject(new KeepAliveOfServer().toString());
							oos.flush();
						}else { // 暂时还没有客户端发送的信息，则停顿一个时间段后再回到循环检查有没有消息
							Thread.sleep(checkDelay);
						}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						//关闭服务器端Socket
						shutdownSocket();
						e.printStackTrace();
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						//关闭服务器端Socket
						shutdownSocket();
						e.printStackTrace();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						//关闭服务器端Socket
						shutdownSocket();
						e.printStackTrace();
					}
				}
			}
			
		}
		
		public void shutdownSocket() {
			System.out.println("服务器端主动关闭与客户端之间的Socket，如下：");
			if (flag) {
				flag = false;
			}
			if (socket != null) {
				System.out.println(socket.getInetAddress().getHostAddress() + "端口：" + socket.getPort() + "被关闭");
				try {
					socket.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}
	
	
}
