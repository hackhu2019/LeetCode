# 搜索插入位置
给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。

> 你可以假设数组中无重复元素。
 
> 示例 1: 
> 输入: [1,3,5,6], 5 
> 输出: 2
> 来源：力扣（LeetCode）
> 链接：https://leetcode-cn.com/problems/search-insert-position

思路分析：对于此类在有序数组中查找对应数、坐标问题都可以使用「二分查找」解题。
```java
class Solution {
    public int searchInsert(int[] nums, int target) {
        if (nums == null || nums.length < 1) {
            return 0;
        }
        int start = 0,end=nums.length-1,mid=(start+end)>>>1; // >>> 无符号右移，避免相加溢出
        while (start <= end) {
            if (nums[mid] == target) { // 相等直接在当前点插入
                return mid;
            } else if (nums[mid] > target) { // 大于中位数则在前半段寻找
                end = mid - 1;
            } else { // 小于中位数则在后半段寻找
                start = mid + 1;
            }
            mid=(start+end)>>>1; // mid 为中位数坐标
        }
        return start; // 小于 nums[0] 时，在起始点插入，大于 nums[len-1] 时，在 nums.length 插入
    }
}
```

