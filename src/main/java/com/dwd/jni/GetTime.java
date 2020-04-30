package com.dwd.jni;

/**
	* 描述：
	*
	* @author duanweidong
	* @version 1.0
	* @date 2020/1/16 下午3:53
	*/
public class GetTime {

		public native void hello();

		static {
				System.loadLibrary("GetTime");
		}
}
