# 不同路径
>一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角。
问总共有多少条不同的路径？

>示例 1:
输入: m = 3, n = 2
输出: 3
解释:
从左上角开始，总共有 3 条路径可以到达右下角。
>1. 向右 -> 向右 -> 向下
>2. 向右 -> 向下 -> 向右
>3. 向下 -> 向右 -> 向右

>来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/unique-paths

思路分析：动态规划，以空间换时间
1. 当前坐标路径数为左一格路径数 + 上一格路径数之和
2. 状态方程：sum[m,n]=sum[m-1][n]+sum[m][n-1]; 
3. 初始化 sum[] 存储每个坐标的路径数：因为只能向下、向右移动 --> 第一行第一列的路径数都为 1
```java
class Solution {
    public int uniquePaths(int m, int n) {
        if (m < 1 || n < 1) {
            return 0;
        }
        int[][] sum = new int[m][n]; // 存储坐标路径数
        // 初始化第一行，第一列路径数
        for (int row = 0; row < sum[0].length; row++) {
            sum[0][row] = 1;
        }
        for (int col = 1; col < sum.length; col++) {
            sum[col][0] = 1;
        }
        // 推导每个坐标的路径数
        for (int row = 1; row < sum.length; row++) {
            for (int col = 1; col < sum[row].length; col++) {
                sum[row][col] = sum[row - 1][col] + sum[row][col - 1];
            }
        }
        return sum[m - 1][n - 1];
    }
}
```

