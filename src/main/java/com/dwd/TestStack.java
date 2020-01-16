package com.dwd;

/**
	* 描述：
	*
	* @author duanweidong
	* @version 1.0
	* @date 2020/1/7 下午2:38
	*/
public class TestStack {

		public static void main(String[] args) {
				StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
				for(StackTraceElement stack : stackTrace) {
						System.out.println("stack===:"+stack);
				}
		}
}
