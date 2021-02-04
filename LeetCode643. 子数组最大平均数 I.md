# LeetCode643. 子数组最大平均数 I

#### [643. 子数组最大平均数 I](https://leetcode-cn.com/problems/maximum-average-subarray-i/)

解题思路：滑动窗口

1. sum记录当前滑动窗口元素和，max 记录滑动窗口元素和最大值
2. 每次更新滑动窗口移出左边界元素，加入右边界元素
3. 遍历结束 返回 max*1.0/k

```java
public double findMaxAverage(int[] nums, int k) {
        int max = Integer.MIN_VALUE, sum = 0;
        for (int i = 0; i < nums.length; i++) {
            // 记录当前滑动窗口元素和
            sum += nums[i];
            // 滑动窗口初始化
            if (i < k - 1) {
                continue;
            }
            // 更新最大值
            max = Math.max(sum, max);
            // 移出左边界元素
            sum -= nums[i - k + 1];
        }
        return max * 1.0 / k;
    }
```

