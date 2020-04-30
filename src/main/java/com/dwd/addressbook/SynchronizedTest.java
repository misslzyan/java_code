package com.dwd.addressbook;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * 描述：
 *
 * @author duanweidong
 * @version 1.0
 * @date 2020/3/10 下午6:43
 */
public class SynchronizedTest {

    public long a = 0;

    synchronized public void incr(){
        a++;
    }

    synchronized public long get(){
        return a;
    }

    public static void main(String[] args) throws InterruptedException {
        int num = 10;
        long nn = 1000000000L;
        SynchronizedTest t = new SynchronizedTest();
        CountDownLatch cdl = new CountDownLatch(num);
        Runnable r = new Runnable() {
            @Override
            public void run() {
                cdl.countDown();
                long ttt = 0;
                while(true){
//                    t.incr();
//                    if(t.get() > nn){
//                        break;
//                    }
                    ttt++;
                    if(ttt>nn/10){
                        break;
                    }
                }
            }
        };
        List<Thread> l = new ArrayList<Thread>();
        for(int i =0 ;i<10;i++){
            Thread thread = new Thread(r);
            l.add(thread);
            thread.start();

        }
        long start = System.currentTimeMillis();
//        while (true){
//            if(t.get() > nn){
//                break;
//            }
//        }
        for(Thread m :l){
            m.join();
        }

        long end = System.currentTimeMillis();

        System.out.println(end-start);


    }


}
