package com.dwd.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 你这个学期必须选修 numCourse 门课程，记为 0 到 numCourse-1 。
 *
 * 在选修某些课程之前需要一些先修课程。 例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示他们：[0,1]
 *
 * 给定课程总量以及它们的先决条件，请你判断是否可能完成所有课程的学习？
 *
 *  
 *
 * 示例 1:
 *
 * 输入: 2, [[1,0]]
 * 输出: true
 * 解释: 总共有 2 门课程。学习课程 1 之前，你需要完成课程 0。所以这是可能的。
 * 示例 2:
 *
 * 输入: 2, [[1,0],[0,1]]
 * 输出: false
 * 解释: 总共有 2 门课程。学习课程 1 之前，你需要先完成​课程 0；并且学习课程 0 之前，你还应先完成课程 1。这是不可能的。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/course-schedule
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

public class Solution_207 {

  public static void main(String[] args) {
    Solution_207 s = new Solution_207();
    int numCourses = 2;
    int[][] prerequisites = new int[][] {
            {0,1}
    };
    boolean re = s.canFinish(numCourses, prerequisites);
    System.out.println(re);
  }

  private List<List<Integer>> edges;

  private int[] visited;

  private boolean res = true;

  public boolean canFinish(int numCourses, int[][] prerequisites) {
    edges = new ArrayList<>();
    for (int i = 0; i < numCourses; i++) {
      edges.add(new ArrayList<>());
    }
    visited = new int[numCourses];
    res = true;
    // init dag.
    for (int[] v : prerequisites) {
      edges.get(v[1]).add(v[0]);
    }

    for (int i = 0;i < numCourses && res; i++){
      if (visited[i] == 0)
        dfs(i);
    }
    return res;
  }

  public void dfs(int i) {
    visited[i] = 1;
    for (int m : edges.get(i)){
      if (visited[m] == 0) {
        dfs(m);
      }else if (visited[m] == 1){
        res = false;
        return;
      }
    }
    visited[i] = 2;
  }
}
