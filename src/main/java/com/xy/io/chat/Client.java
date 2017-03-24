package com.xy.io.chat;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Client {
	
	private static final String HOST = "127.0.0.1";
	private static final int PORT = 110;

	public static void main(String[] args){
		
		Socket socket = null;
		try {
			socket = new Socket(HOST,PORT);
			
			System.out.print("成功连接");    
			
			new ClientReader(socket);
			
			while(true){
				DataOutputStream out = new DataOutputStream(socket.getOutputStream());    
                // 发送键盘输入的一行    
				System.out.println("请输入：\t");
                String s = new BufferedReader(new InputStreamReader(System.in)).readLine();    
                out.writeUTF(s);
			}
			
		} catch (Exception e) {
			System.out.println("连接异常："+e.getMessage());
		}finally{
			if(socket != null){
				try {
					socket.close();
				} catch (IOException e) {
					System.out.println("socket关闭异常："+e.getMessage());
				}
			}
		}
	}
		
}
