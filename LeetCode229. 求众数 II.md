# 求众数 II

>  给定一个大小为 n 的数组，找出其中所有出现超过 ⌊ n/3 ⌋ 次的元素。
> 说明: 要求算法的时间复杂度为 O(n)，空间复杂度为 O(1)。

> 示例 1:
> 输入: [3,2,3]
> 输出: [3]
> 来源：力扣（LeetCode）
> 链接：https://leetcode-cn.com/problems/majority-element-ii

思路分析：借鉴 [169 题 求众数](https://leetcode-cn.com/problems/majority-element/) 思路

假定 num1，num2 为出现次数大于 nums.length / 3 的两个数。（最多出现两个）

1. 遍历 nums， 若出现 num1、num2 中任意一数，计数+1，若都不等，则计数-1.
2. 若 num1、num2 有一个计数 < 0，则替换成当前遍历数（更换新的众数）
3. 数组可能出现 **无众数** 或只有 **一众数** 情况，所以需要再次遍历数组，统计所选众数出现次数，将满足条件（出现次数大于 nums.length / 3 ）的数加入返回集合。

```java
class Solution {
   public List<Integer> majorityElement(int[] nums) {
        List<Integer> lists = new ArrayList<>();
        if (nums.length < 2) { // 只有 1 个数则必为众数
            for (int i : nums) {
                lists.add(i);
            }
            return lists;
        }
        int num1 = nums[0], num2 = nums[1]; // 选出两数
        int len = nums.length / 3, count1 = 0, count2 = 0;
        for (int i = 0; i < nums.length; i++) {
            if (num1 == nums[i]) { //  若出现 num1、num2 中任意一数，计数+1
                count1++;
                continue;
            }else if (num2 == nums[i]) {
                count2++;
                continue;
            }else { // 都不等，则计数-1
                count1--;
                count2--;
            }
            if (count1 < 0) { // 更换新的众数
                num1 = nums[i];
                count1 = 1;
                count2++; // 确保每次只更换 1 个
            }
            if (count2 < 0) {
                num2 = nums[i];
                count2 = 1;
                count1++;
            }
        }
        count1 = 0; // 归零，重新计数
        count2 = 0;
        for (int num : nums) { // 验证次数，符合则加入集合
            if (num == num1) {
                count1++;
            } else if (num == num2) {
                count2++;
            }
        }
        if (count1 > len) {
            lists.add(num1);
        }
        if (count2 > len) {
            lists.add(num2);
        }
        return lists;
    }
}
```
思路参考：[多数投票的升级版](https://leetcode-cn.com/problems/majority-element-ii/solution/duo-shu-tou-piao-de-sheng-ji-ban-hao-li-jie-java-b/)
