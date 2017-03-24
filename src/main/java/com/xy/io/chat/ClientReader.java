package com.xy.io.chat;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientReader implements Runnable{

	private Socket socket;

	public ClientReader(Socket socket){
		this.socket = socket;
		
		new Thread(this).start();
	}
	
	@Override
	public void run() {
		try {
			DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
			
			while(true){
				
				System.out.println("客户端接收到消息	"+dataInputStream.readUTF());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("客户端读取数据异常："+e);
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
