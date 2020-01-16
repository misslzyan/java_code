package com.dwd;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
	* 描述：
	*
	* @author duanweidong
	* @version 1.0
	* @date 2019/11/25 下午3:48
	*/
public class TestGuavaCache {

		private static int i = 0;

		public static void main(String[] args) throws ExecutionException {
//				LoadingCache<String, String> cache = CacheBuilder.newBuilder()
//												.maximumSize(1000)
//												.build(new CacheLoader<String, String>() {
//														@Override
//														public String load(String key) throws Exception {
//																return key + i++;
//														}
//												});
//				String value = cache.get("aaa");
//				System.out.println(value);
//				Map<String, String> map = cache.asMap();
//				System.out.println(map.size());
//				map.entrySet().stream().map(entry -> {
//						System.out.println(entry.getKey() + ":" + entry.getValue());
//						return "";
//				});
//				cache.put("bbb","bbbv");
//				System.out.println(map.size());
//				for(Map.Entry<String,String> entry: map.entrySet()){
//						System.out.println(entry.getKey() + ":" + entry.getValue());
//				}
//				map.entrySet().stream().map(entry -> {
//						System.out.println(entry.getKey() + ":" + entry.getValue());
//						return "";
//				});
		}
}
