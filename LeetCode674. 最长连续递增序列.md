# LeetCode674. 最长连续递增序列

#### [674. 最长连续递增序列](https://leetcode-cn.com/problems/longest-continuous-increasing-subsequence/)

解题思路：记录每次递增序列的长度，max存储最大长度

```java
public int findLengthOfLCIS(int[] nums) {
        int max = 1, left = 0;
        for (int right = 1; right < nums.length; right++) {
            // 递增序列更新最大长度
            if (nums[right] > nums[right - 1]) {
                max = Math.max(right - left + 1, max);
            } else { // 非递增更新边界
                left = right;
            }
        }
        return nums.length == 0 ? 0 : max; // 处理特殊值
    }
```

