# LeetCode153. 寻找旋转排序数组中的最小值

#### [153. 寻找旋转排序数组中的最小值](https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array/)

解题思路：二分查找，当 num[mid]<nums[right] ,在左分区查找，反之在右分区查找

当左右分界点重叠时，左分界点为最小值（升序排序）

```java
public int findMin(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < nums[right]) {
                right = mid;
            } else {
                left = mid + 1;
            } 
        }
        return nums[left];
    }
```

