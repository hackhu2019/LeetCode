# 删除排序数组中的重复项 [题目链接](https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array)

> 给定一个排序数组，你需要在原地删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。
> 不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。

> 示例 1:
> 给定数组 nums = [1,1,2], 
> 函数应该返回新的长度 2, 并且原数组 nums 的前两个元素被修改为 1, 2。 
> 你不需要考虑数组中超出新长度后面的元素。

来源：力扣（LeetCode）

思路：推进法，因为是有序数组，所有只要遍历数组时判断下一数是否与当前遍历数相等即可。

若相等，记录重复个数。

若不相等，后序数向前推进重复数。数组长度缩减。

缺陷：多重重复赋值，我们只要将非重复数去除即可
```java
class Solution {
    public int removeDuplicates(int[] nums) {
        int len = nums.length; // 获取数组长度
        if (nums == null || len <= 0) { // 处理特殊情况
            return 0;
        }
        int count = 1; // 无重复数组长度
        int cus = 1; // 快指针
        int curIndex = 0; // 慢指针
        while (cus < len) {
            if (nums[cus] == nums[curIndex]) { // 快指针前景
                cus++;
            }else { // 推进
                int advance = cus - count;
                for(int i=curIndex+1;i<len-advance;i++){
                    nums[i] = nums[i + advance];
                }
                count++;
                len-=advance;
                curIndex++;
                cus = curIndex + 1;
            }
        }
        // System.out.println(Arrays.toString(nums));
        return count;
    }
}
```
优化
忽略重复数，发现不相等数，将其放置在对应索引。

```java
class Solution {
    public int removeDuplicates(int[] nums) {
        int len = nums.length;
        if (nums == null || len <= 0) {
            return 0;
        }
        int count=0; // 记录不相等数
        for (int i = 1; i < len; i++) {
            if (nums[i] != nums[count]) {
                count++;
                nums[count] = nums[i];
            }
        }
        // System.out.println(Arrays.toString(nums));
        return count+1;
    }
}
```

