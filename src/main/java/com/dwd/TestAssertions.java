package com.dwd;

public class TestAssertions {

    public static void main(String[] args) {
        int i = 7;
        i = 9;
        assert i == 7 : "i is 9";
    }
}
