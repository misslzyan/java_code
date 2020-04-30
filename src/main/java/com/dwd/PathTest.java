package com.dwd;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.util.Time;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class PathTest {

  private static long  sleepInterval = 20000;

  private static final int  REMOVE_TOKEN_MASK = (1 << 10) - 1;

  public static void main(String[] args) throws IOException {
//    Path path = new Path("hdfs","hadoop-meituan","/bbb");
//    System.out.println(path.toString());
//    System.out.println(path.toUri().toString());
    System.out.println(REMOVE_TOKEN_MASK);
    Set<String> set = new HashSet<>();
    for (int i = 0 ;i < 3000;i++){
      set.add(i+"");
    }
    removeStoredTokens(set);
  }

  protected static void removeStoredTokens(Set<String> expiredTokens) throws IOException {
    int i = 0;
    long start = Time.now();
    System.out.println("Starting to Remove ExpiredToken.");
    for (String ident : expiredTokens) {

      i++;
      if (sleepInterval > 0 && (i & REMOVE_TOKEN_MASK) == 0) {
        try {
          System.out.println("sleep");
          Thread.sleep(sleepInterval);
        } catch (InterruptedException ie) {
          System.out.println("Router ExpiredTokenRemover received " + ie);
        }
      }
    }
    long cost = Time.now() - start;
    System.out.println("Remove ExpiredToken " + i + " cost " + cost + " ms.");
  }
}
