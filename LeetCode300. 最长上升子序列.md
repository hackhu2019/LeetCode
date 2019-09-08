# 300. 最长上升子序列
>给定一个无序的整数数组，找到其中最长上升子序列的长度。

>示例:
输入: [10,9,2,5,3,7,101,18]
输出: 4 
解释: 最长的上升子序列是 [2,3,7,101]，它的长度是 4。

>来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/longest-increasing-subsequence

解法一：动态规划
1. 第 i 个数所能组成的最长上升子序列为 0..1 中小于 nums[x]<=nums[i] 最长序列 +1
2.  max[i]=max{max[0]..max[x]}+1 (nums[x]<=nums[i])
```java
class Solution {
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length < 1) {
            return 0;
        }
        int[] max = new int[nums.length]; // 存储第 i 个数所能形成的最长子序列
        max[0] = 1; // 初始化 max
        for (int i = 1; i < nums.length; i++) { // 推导 max[i]
            int len = 0;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j] && max[j] > len) {
                    len = max[j];
                }
            }
            max[i] = len + 1;
        }
        int maxValue = max[0];
        for (int i = 1; i < max.length; i++) {
            maxValue = maxValue > max[i] ? maxValue : max[i]; // 找到最大值
        }
        return maxValue;
    }
}
```
解法二：二分查找法

思路分析：
1.  维护一个递增集合，每次将遍历的数插入集合中对应位置。
2.  longSequence 实际上就是记录最长子序列所能达到的最大长度
3.  binarySearch 使用二分查找法，查找要插入数字的位置，进行数据更新
```java
class Solution {
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length < 1) {
            return 0;
        }
        List<Integer> longSequence = new ArrayList<>();
        longSequence.add(nums[0]);
        for (int i = 1; i < nums.length; i++) { // 遍历数组，更新 longSequence 数字
            int lastIndex = longSequence.size() - 1;
            if (nums[i] > longSequence.get(lastIndex)) {
                longSequence.add(nums[i]);
            }else {
                binarySearch(longSequence, 0, lastIndex, nums[i]);
            }
        }
        return longSequence.size();
    }
    /*
        二分查找法，找到要更新的位置。
        mid-1 小于 target
     */
    private void binarySearch(List<Integer> nums, int left, int right, int target){
        while (left < right) {
            int mid = left + ((right - left) >> 1); // 取中间值
            if (nums.get(mid) == target) {
                return;
            } else if (nums.get(mid) > target) {
                if (mid > left && nums.get(mid - 1) < target) {
                    left = mid;
                    break;
                }
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        nums.set(left,target); // 找到插入位置
    }
}
```

