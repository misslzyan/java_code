package com.dwd.thread;

import org.apache.avro.generic.GenericData;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TestThreadPool2 {

  static ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 10,
        0L,TimeUnit.MILLISECONDS, new LinkedBlockingDeque<>());

  public static void main(String[] args) throws InterruptedException {
    List<Callable<Object>> calls = new ArrayList<>();
    calls.add(() -> {
      try {
        Thread.sleep(3000);
        System.out.println("aadfasdfasf");
        return new Object();
      } catch (InterruptedException e) {
        System.out.println("aadfasdfasf");
        System.out.println(e.getMessage());
        throw  e;
      }
    });
    calls.add(() -> {
      Thread.sleep(3000);
      return new Object();
    });
    calls.add(() -> {
      Thread.sleep(3000);
      return new Object();
    });
    calls.add(() -> {
      Thread.sleep(1000);
      return new Object();
    });
    List<Future<Object>> futures = executor.invokeAll(calls, 2, TimeUnit.SECONDS);
    for (Future f: futures) {
      try {
        System.out.println(f.getClass());
        f.get();
        System.out.println(f.get());
      } catch (ExecutionException e) {
        System.out.println(e);
      } catch (Exception e) {
        System.out.println(e);
      }
    }
    System.out.println("----");
  }
}
