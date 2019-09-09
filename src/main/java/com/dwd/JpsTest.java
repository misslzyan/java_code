package com.dwd;

import java.util.Arrays;

/**
 * 描述：
 *
 * @author duanweidong
 * @version 1.0
 * @date 2019/8/30 下午7:14
 */
public class JpsTest {
    public static void main(String[] args) {
        while (true) {
            for (String tmp : Arrays.asList(args)) {
                System.out.println(tmp);
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
