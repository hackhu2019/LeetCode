#  不同路径 II
>一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？

>示例 1:
输入:
[
  [0,0,0],
  [0,1,0],
  [0,0,0]
]
输出: 2
解释:
3x3 网格的正中间有一个障碍物。
从左上角到右下角一共有 2 条不同的路径：
1. 向右 -> 向右 -> 向下 -> 向下
2. 向下 -> 向下 -> 向右 -> 向右

>来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/unique-paths-ii

思路分析：
1. 在 62 题的基础上多一步判断，若 obstacleGrid[m][n]=1 将当前位置的路径数置0
2. 当前坐标路径数为左一格路径数 + 上一格路径数之和
3. 状态方程：sum[m,n]=sum[m-1][n]+sum[m][n-1]; 
4.  初始化 sum[] 存储每个坐标的路径数：因为只能向下、向右移动 --> 第一行第一列的路径数受上一初始化坐标影响，若当前坐标为障碍物则路径数为 0，否则与上一遍历坐标路径数一致 `sum[0][row] = sum[0][row-1];` / `sum[col][0] = sum[col - 1][0];`
```java
class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid == null || obstacleGrid.length < 1 || obstacleGrid[0][0] == 1) {
            return 0;
        }
        int rowLen = obstacleGrid.length, colLen = obstacleGrid[0].length;
        int[][] sum = new int[rowLen][colLen]; // 存储坐标路径数
        // 初始化第一行，第一列路径数
        sum[0][0] = 1;
        for (int row = 1; row < sum[0].length; row++) {
            sum[0][row] = obstacleGrid[0][row]==1 ? 0 : sum[0][row-1];
        }
        for (int col = 1; col < sum.length; col++) {
            sum[col][0] = obstacleGrid[col][0] == 1 ? 0 : sum[col - 1][0];
        }
        // 推导每个坐标的路径数
        for (int row = 1; row < sum.length; row++) {
            for (int col = 1; col < sum[row].length; col++) {
                sum[row][col] = obstacleGrid[row][col] == 1 ? 0 :
                                sum[row - 1][col] + sum[row][col - 1];
            }
        }
        return sum[rowLen - 1][colLen - 1];
    }
}
```

