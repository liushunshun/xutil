package com.xy.io.chat;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	
	private static final int PORT = 110;
	
	private ServerSocket serverSocket;
	
	public static void main(String[] args) {
		Server server = new Server();
		
		server.init();
	}
	
	public void init(){
		try{
			serverSocket = new ServerSocket(PORT);
			
			System.out.println("服务端启动成功");
			
			while(true){
				try{
					Socket socket = serverSocket.accept();
					
					new ServerReader(socket);
					
					if(socket.isClosed()){
						System.out.println("连接已经关闭");
						continue;
					}else{
						System.out.println("服务端接收到新连接...");
						while(true){
							DataOutputStream out = new DataOutputStream(socket.getOutputStream());  
							
			                System.out.print("请输入:\t");    
			                // 发送键盘输入的一行    
			                String s = new BufferedReader(new InputStreamReader(System.in)).readLine();    
			                out.writeUTF(s);
						}
					}
				}catch(Exception e){
					e.printStackTrace();
				}
				
			}
			
		}catch(IOException e){
			System.out.println("服务器异常+"+e.getMessage());
		}
		
	}
}
