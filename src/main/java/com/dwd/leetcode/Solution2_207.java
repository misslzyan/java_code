package com.dwd.leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.stream.IntStream;

public class Solution2_207 {

  public static void main(String[] args) {
    Solution2_207 s = new Solution2_207();
    int numCourses = 2;
    int[][] prerequisites = new int[][] {
            {0,1}
    };
    boolean re = s.canFinish(numCourses, prerequisites);
    System.out.println(re);
  }

  List<List<Integer>> edges;

  int[] indeg;

  int visit;

  Queue<Integer> queue;


  public boolean canFinish(int numCourses, int[][] prerequisites) {
    visit = 0;
    indeg = new int[numCourses];
    edges = new ArrayList<>();
    IntStream.range(0, numCourses).boxed()
        .forEach(i -> edges.add(new ArrayList<>()));
    IntStream.range(0, prerequisites.length).boxed()
        .forEach(i -> {
          edges.get(prerequisites[i][1]).add(prerequisites[i][0]);
          indeg[prerequisites[i][0]]++;
        });
    queue = new ArrayDeque<>();
    for (int i = 0; i < indeg.length; i++) {
      if (indeg[i] == 0) {
        queue.add(i);
      }
    }
    while (!queue.isEmpty()) {
      int m = queue.poll();
      visit++;
      for (int c : edges.get(m)) {
        indeg[c]--;
        if (indeg[c] == 0) {
          queue.add(c);
        }
      }
    }
    return visit == numCourses;
  }
}
