package com.xy.io.chat;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class ServerReader implements Runnable{
	
	private Socket socket;

	public ServerReader(Socket socket){
		this.socket = socket;
		
		new Thread(this).start();
		
	}

	@Override
	public void run() {
		try {
			
			DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
			
			while(true){
				String str = dataInputStream.readUTF();
				
				System.out.println("服务端收到消息	"+str);
			}
		} catch (Exception e) {
			System.out.println("服务端读取数据异常："+e);
		}finally{
			if(socket != null){
				try {
					socket.close();
				} catch (IOException e) {
					System.out.println("关闭socket异常："+e.getMessage());
				}
			}
			
		}
	}
}
