# 最小路径和
>给定一个包含非负整数的 m x n 网格，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
说明：每次只能向下或者向右移动一步。

>示例:
输入:
[
  [1,3,1],
  [1,5,1],
  [4,2,1]
]
输出: 7
解释: 因为路径 1→3→1→1→1 的总和最小。

>来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/minimum-path-sum

思路分析：动态规划，推导状态方程

1. 当前坐标点有两个进入方法，上方结点向下走，左侧结点向右走，则当前路径最小值为 左 / 上 坐标点最小路径值。
2. 状态方程：min[m,n]=Min(min[m-1,n],min[m,n-1])+grid[m,n]
3. 初始化第一行第一列路径值：`min[0][i] = min[0][i-1] +grid[0][i];` `min[i][0] = min[i - 1][0] + grid[i][0];`
```java
class Solution {
    public int minPathSum(int[][] grid) {
        if (grid == null || grid.length < 1) {
            return 0;
        }
        int rowLen = grid.length, colLen = grid[0].length;
        int[][] min = new int[rowLen][colLen]; // 存储路径最小值
        // 初始化 min 第一行第一列
        min[0][0] = grid[0][0];
        for (int i = 1; i < colLen; i++) {
            min[0][i] = min[0][i-1] +grid[0][i];
        }
        for (int i = 1; i < rowLen; i++) {
            min[i][0] = min[i - 1][0] + grid[i][0];
        }
        // 推导路径最小值
        for (int row = 1; row < rowLen; row++) {
            for (int col = 1; col < colLen; col++) {
                min[row][col] = Math.min(min[row - 1][col], min[row][col - 1]) +
                                grid[row][col];
            }
        }
        return min[rowLen - 1][colLen - 1];
    }
}
```

