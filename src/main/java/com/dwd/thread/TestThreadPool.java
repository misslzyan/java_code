package com.dwd.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
	* 描述：
	*
	* @author duanweidong
	* @version 1.0
	* @date 2020/1/9 下午2:07
	*/
public class TestThreadPool {

		private static ExecutorService es = Executors.newFixedThreadPool(2);
		public static void main(String[] args) {
				System.out.println(es.getClass());
				Future<?> submit = es.submit(new Callable<Object>() {
						@Override
						public Object call() throws Exception {
								return null;
						}
				});
				submit.isDone();
		}
}
