# 面试题 17.16. 按摩师 [题目链接](https://leetcode-cn.com/problems/the-masseuse-lcci/)
解题思路：动态规划，由果溯因

dp[n][0]表示不接受当前数的最大预约时长 = Max（dp[n-1][0],dp[n-1][1]）

dp[n][1] 表示接受当前数的最大预约时长  = dp[n-1][0]+nums[n]

当前最大预约时长= Max（dp[n][0],dp[n][1]）
```java
class Solution {
    public int massage(int[] nums) {
        if (nums == null || nums.length < 1) {
            return 0;
        }
        int dp0 = 0, dp1 = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int tempDp0 = dp0, tempDp1 = dp1;
            dp0 = Math.max(tempDp0, tempDp1);
            dp1 = tempDp0 + nums[i];
        }
        return dp0 > dp1 ? dp0 : dp1;
    }
}
```
