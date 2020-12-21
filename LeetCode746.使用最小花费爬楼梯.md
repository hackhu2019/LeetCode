# LeetCode746.使用最小花费爬楼梯

#### [746. 使用最小花费爬楼梯](https://leetcode-cn.com/problems/min-cost-climbing-stairs/)

解题思路：动态规划

当前楼梯最小值=Math.min(前一步最小值,前两步最小值)

```java
public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        int[] min = new int[n];
        min[0] = cost[0];
        min[1] = cost[1];
        for (int i = 2; i < n; i++) {
            min[i] = Math.min(min[i - 1], min[i - 2]) + cost[i];
        }
        return Math.min(min[n - 1], min[n - 2]);
    }
```

简化 minN1、minN2 分别记录 前一步最小值,前两步最小值

```java
public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        int minN1 = cost[0];
        int minN2 = cost[1];
        for (int i = 2; i < n; i++) {
            int next= Math.min(minN1, minN2) + cost[i];
            minN2 = minN1;
            minN1 = next;
        }
        return Math.min(minN1, minN2);
    }
```

