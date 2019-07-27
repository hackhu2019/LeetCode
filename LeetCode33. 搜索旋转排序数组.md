# 搜索旋转排序数组

> 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
> ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
> 搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。

> 你可以假设数组中不存在重复的元素。
> 你的算法时间复杂度必须是 O(log n) 级别。
 
> 示例 1:
> 输入: nums = [4,5,6,7,0,1,2], target = 0 输出: 4
 
> 来源：力扣（LeetCode）
> 链接：https://leetcode-cn.com/problems/search-in-rotated-sorted-array

思路分析：

1. 找到升序数组的分界点。优化：当左边界值小于右边界点时，说明有序区间为[ left,right]，分界点为 right。
2. 当分界点为数组最末端，则直接在整个数组中二分查找，否则根据目标与数组最末端大小比较，划分查找区域。优化：当 nums[point] < target ，说明 target 不在数组范围，直接返回 -1。
3. 在有序区间，二分查找指定目标。优化：target>nums[right] 或 target<nums[left] 时，说明 target 不在数组范围，直接返回 -1。
```java
// 找到分界点
class Solution {
    private int findPoint(int[] nums,int left,int right){
        if (nums[left] <= nums[right]) {
            return right;
        }
        int range = right - left;
        if (range == 1) {
            return left;
        } else if (range == 2) {
            return nums[left]>nums[left+1]?left :left+1;
        } else {
            int mid = (left + right) / 2;
            return nums[mid] > nums[right] ? findPoint(nums, mid, right) : findPoint(nums, left, mid);
        }
    }

    /**
     * 若分界点为数组最末端，则直接在整个数组中二分查找，
     * 否则根据目标与数组最末端大小比较，划分查找区域
     */
    public int search(int[] nums, int target) {
        if (nums == null||nums.length<1) {
            return -1;
        }
        if (nums.length == 1) {
            return nums[0] == target ? 0 : -1;
        }
        int point = findPoint(nums, 0, nums.length - 1);
        if (point == nums.length - 1) {
            return binarySearch(nums, 0, nums.length - 1, target);
        } else {
            if (nums[point] == target) {
                return point;
            } else if (nums[point] < target) {
                return -1;
            } else if (target >= nums[0]) {
                return binarySearch(nums, 0, point, target);
            }else {
                return binarySearch(nums, point + 1, nums.length - 1, target);
            }
        }
    }
// 二分查找，在有序区间寻找指定点
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

