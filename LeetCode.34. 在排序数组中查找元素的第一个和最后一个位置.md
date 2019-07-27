# 在排序数组中查找元素的第一个和最后一个位置

> 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。

> 你的算法时间复杂度必须是 O(log n) 级别。
> 如果数组中不存在目标值，返回 [-1, -1]。

> 示例 1:
> 输入: nums = [5,7,7,8,8,10], target = 8 输出: [3,4] 
> 来源：力扣（LeetCode）
> 链接：https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array

解题思路：
1. 先通过二分查找法，在数组中查找指定目标索引
2. 当返回值为 -1，说明指定目标不存在数组中，则返回(-1,-1)
3. 否，则记录返回坐标 index ，index 降序遍历直到遍历目标不为 target，start 记录最小索引
4. index 升序遍历直到遍历目标不为 target，end 记录最大索引
```java
class Solution {
    public int[] searchRange(int[] nums, int target) {
        int[] range = {-1, -1}; // 默认返回值为 {-1, -1}
        if (nums == null||nums.length<1) { // 处理特殊情况
            return range;
        }
        if (nums.length == 1) { 
            return nums[0] == target ? new int[]{0,0} : range;
        }
		range[0] = range[1] = binarySearch(nums, 0, nums.length - 1, target); // 获取目标值的查找索引，不存在则为 -1
        if (range[0] == -1) {
            return range;
        }
        while (range[0] > 0 && nums[range[0]-1] == target) { // 以找到的索引为中心点，寻找起始结束的范围
            range[0]--;
        }
        while (range[1] < nums.length-1 && nums[range[1]+1] == target) {
            range[1]++;
        }
        return range;
    }
    // 二分查找
    private int binarySearch(int[] nums, int left, int right, int target) {
        if (target>nums[right]||target<nums[left]){
            return -1;
        }
        if (right - left <= 1) {
            if (nums[left] == target) {
                return left;
            } else if (nums[right] == target) {
                return right;
            } else {
                return -1;
            }
        }else {
            int mid = (left + right) / 2;
            return nums[mid] >target ? binarySearch(nums, left, mid,target) : binarySearch(nums, mid, right,target);
        }
    }
}
```

