# 移除元素 [题目链接](https://leetcode-cn.com/problems/remove-element/submissions/)

> 给定一个数组 nums 和一个值 val，你需要原地移除所有数值等于 val 的元素，返回移除后数组的新长度。
> 不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。
  元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。

> 示例 1:
> 给定 nums = [3,2,2,3], val = 3,
> 函数应该返回新的长度 2, 并且 nums 中的前两个元素均为 2。
> 来源：力扣（LeetCode）

思路：遍历数组，若找到与 val 不同值，放置在对应索引，否则跳过
```java
class Solution {
    public int removeElement(int[] nums, int val) {
        int len = nums.length;
        if (nums == null || len <= 0) {
            return 0;
        }
        int count = 0; // 统计不重复数
        for (int i = 0; i < len; i++) {
            if (nums[i] != val) {
                nums[count] = nums[i];
                count++;
            }
        }
        // System.out.println(Arrays.toString(nums));
        return count;
    }
}
```

