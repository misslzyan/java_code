package com.dwd;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.*;

/**
	* 描述：
	*
	* @author duanweidong
	* @version 1.0
	* @date 2020/1/2 下午9:00
	*/
public class TestFile {

		public static void main(String[] args) throws IOException {
				ExecutorService executorService = Executors.newCachedThreadPool();
				Future<Object> f = executorService.submit(new Callable<Object>() {
						@Override
						public Object call() throws Exception {
								throw new IOException("aaaa");
						}
				});
				try {
						f.get();
				} catch (InterruptedException e) {
						e.printStackTrace();
				} catch (ExecutionException e) {
						if(e.getCause() instanceof  FileNotFoundException) {
								throw  new FileNotFoundException(e.getCause().getMessage());
						}else{
								throw  new IOException();
						}
				}
		}
}
