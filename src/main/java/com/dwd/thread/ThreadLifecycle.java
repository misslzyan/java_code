package com.dwd.thread;

/**
 * 描述：
 *
 * @author duanweidong
 * @version 1.0
 * @date 2019/10/30 下午6:03
 */
public class ThreadLifecycle {
    static Object lock = new Object();

  public static void main(String[] args) throws InterruptedException {
        Thread t1 = new MyThread("myThread-0");
      print(1,t1.getState());
      t1.start();
      print(2,t1.getState());
      ((MyThread) t1).setStart(false);
      while(t1.getState()!= Thread.State.WAITING){
          print(3,t1.getState());
      }
      print(4,t1.getState());
      synchronized (lock) {
          lock.notify();
          print(10,t1.getState());
          lock.wait();
          print(11,t1.getState());
      }
      print(5,t1.getState());
      t1.join();
      print(6,t1.getState());
  }

  static class MyThread extends Thread {

      private String name;

      public MyThread(String name) {
          super(name);
      }

      private volatile boolean start = true;

      public void setStart(boolean start) {
          this.start = start;
      }

      @Override
      public void run() {
        while(start);
        synchronized (lock) {
            try {
                print(7,this.getState());
                System.out.println("before wait");
                lock.wait();
                print(8,this.getState());
                System.out.println("after wait");
                lock.notify();
                print(9,this.getState());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
      }
  }

  public static void print(int c, Thread.State state) {
      String name = Thread.currentThread().getName();
      System.out.println(name + ":"+c+":" + state);
  }
}
