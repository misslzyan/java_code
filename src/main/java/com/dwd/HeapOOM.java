package com.dwd;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述：
 *
 * @author duanweidong
 * @version 1.0
 * @date 2019/8/23 下午4:25
 */
public class HeapOOM {

    static class OOMObject{}

    public static void main(String[] args) {
        List<OOMObject> list = new ArrayList<OOMObject>();
        while(true){
            list.add(new OOMObject());
        }
    }
}
