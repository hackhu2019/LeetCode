# 最大子序和
>给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。

>示例:
输入: [-2,1,-3,4,-1,2,1,-5,4],
输出: 6
解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。

>来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/maximum-subarray

思路分析：

由果溯因，数组第 n 个数的最大连续子串和有两个选择：加上前一个数的最大连续子串和 / 仅保留自己，取两个选择中的最大值。

--> 状态方程：`max[n]=max(max[n]+nums[n],num[n])-->max[n]>=0?max[n]+nums[n]:nums[n];`
```java
public int maxSubArray(int[] nums) {
		// 处理空指针异常
        if (nums == null || nums.length < 1) {
            return 0;
        }
        int sum = nums[0], max = nums[0];
        // 根据状态方程 max[n]=max[n]>=0?max[n]+nums[n]:nums[n]; 计算最大连续子串和
        for (int i = 1; i < nums.length; i++) {
            sum = sum >= 0 ? sum += nums[i] : nums[i];
            max = sum > max ? sum : max;
        }
        return max;
    }
```

