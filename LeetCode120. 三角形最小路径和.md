#  三角形最小路径和
>给定一个三角形，找出自顶向下的最小路径和。每一步只能移动到下一行中相邻的结点上。

>例如，给定三角形：
[
     [2],
    [3,4],
   [6,5,7],
  [4,1,8,3]
]
自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。

>来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/triangle

思路分析：动态规划，自底向上看。
1. 当前路径的最小值，为下一层左右两点的最小值加上当前坐标值
2.  状态转移方程：min[row][col]=min(min[row+1][col],min[row][col+1])+triangle[row][col]


```java
class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        if (triangle == null || triangle.size() == 0) {
            return 0; // 处理空指针异常
        }
        int len = triangle.get(triangle.size() - 1).size(); // 最底层数据长度
        int[] result = new int[len]; // 存储下一层结果
        for (int i = 0; i < len; i++) { // 初始化最底层数据
            result[i] = triangle.get(triangle.size() - 1).get(i);
        }
        for (int row = triangle.size()-2; row >=0 ; row--) {
            for (int col = 0; col < triangle.get(row).size(); col++) {
            	// 状态转移方程：min[row][col]=min(min[row+1][col],min[row][col+1])+triangle[row][col]
                result[col] = triangle.get(row).get(col) + Math.min(result[col], result[col + 1]);
            }
        }
        return result[0];
    }
}
```

