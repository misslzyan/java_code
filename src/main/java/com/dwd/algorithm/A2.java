package com.dwd.algorithm;

/**
 *   编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target。该矩阵具有以下特性：
 *
 * 每行的元素从左到右升序排列。
 * 每列的元素从上到下升序排列。
 * 示例:
 *
 * 现有矩阵 matrix 如下：
 *
 * [
 *   [1,   4,  7, 11, 15],
 *   [2,   5,  8, 12, 19],
 *   [3,   6,  9, 16, 22],
 *   [10, 13, 14, 17, 24],
 *   [18, 21, 23, 26, 30]
 * ]
 * 给定 target = 5，返回 true。
 *
 * 给定 target = 20，返回 false。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/search-a-2d-matrix-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class A2 {

  public static void main(String[] args) {
    int a[][] =
            {
                    {1,   4,  7, 11, 15},
                    {2,   5,  8, 12, 19},
                    {3,   6,  9, 16, 22},
                    {10, 13, 14, 17, 24},
                    {18, 21, 23, 26, 30}
 };
    System.out.println(cal(a, 5));
    System.out.println(cal(a, 20));
  }

  public static boolean cal(int[][] array, int target) {
    int i = 0;
    int j = 0;
    int width = array.length;
    boolean tag = false;
    int high = width > 0 ? array[0].length : 0;
    for (; i<width && j < high;) {
      if (array[j][i] == target) {
        return true;
      } if (array[j][i] < target) {
        if (!tag) {
          i++;
        } else {
          j++;
        }
      } else if (array[j][i] > target)  {
        if (i > 0 && !tag) {
          i--;
          j++;
          tag = true;
        } else {
          break;
        }
      }

    }
    return false;
  }
}
