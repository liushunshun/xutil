package com.xy.nio;

import java.nio.ByteBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;

public class ByteBuffer2String {
	public static void main(String[] args) throws CharacterCodingException {
		/**
		 * ByteBuffer和String互转
		 */
		Charset charset = Charset.forName("UTF-8");
		
		ByteBuffer byteBuffer = ByteBuffer.wrap("中文".getBytes(charset));
		
		System.out.println(charset.decode(byteBuffer));
		
		
	}
}
