package com.dwd;

/**
 * 描述：
 *
 * @author duanweidong
 * @version 1.0
 * @date 2019/9/11 下午5:11
 */
public class TestClass {
    private int data = 2222;
    private String sss = "i am new ";

    public String dataAdd(){
        final int bbb = data;

        return sss+ bbb + 1;
    }
}
