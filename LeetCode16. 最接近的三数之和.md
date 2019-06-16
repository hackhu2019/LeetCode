# 最接近的三数之和 [题目链接](https://leetcode-cn.com/problems/3sum-closest/)

> 给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，使得它们的和与 target 最接近。返回这三个数的和。假定每组输入只存在唯一答案。

>例如，给定数组 nums = [-1，2，1，-4], 和 target = 1.
>与 target 最接近的三个数的和为 2. (-1 + 2 + 1 = 2).

思路分析：

对 nums 排序，再依序遍历 nums 数组中的每个数，为固定数 fix，与剩余数组合计算与 target 的差值。

剩下两数从 nums 剩余元素中组合（索引范围 fixIndex+1~len-1 ），start  从 fixIndex+1 开始，end 从 len-1 开始

计算与 target 的差值，可能出现的情况有 3 种

1、差值为 0 ，说明已找到正确组合，则直接返回 target

2、差值 <0 , 则增加三数和，start 索引+1

3、差值 >0 , 则减小三数和，end 索引-1

sub 存储与 target 最小差值
```java
class Solution {
    public int threeSumClosest(int[] nums, int target) {
        int sum = 0;
        int len = nums.length;
        if (len == 3) { // 若 nums 只有 3 个数则直接返回三数之和
            return nums[0] + nums[1] + nums[2];
        }
        Arrays.sort(nums); // 将nums 降序排序
        int start = 0;
        int end = len - 1;
        double sub = Integer.MAX_VALUE; // 存储最小差值，定义 double 类型防止运算后超出 int 类型取值范围
        int fixIndex = 0;
        for (int i = 0; i < len-2; i++) {
            fixIndex = i;
            start=i+1;
            end=len-1;
            while (start < end) {
                sum = nums[start] + nums[fixIndex] + nums[end] - target; // 三数和与 target 差值
                if (sum == 0) { // 若新组合三数与 target 相等则直接返回 target
                    return target;
                }
                sub = Math.abs(sub) > Math.abs(sum) ? sum : sub; // sub 存储最小与 target 最小差值
                if (sum > 0) {
                    end--;
                } else {
                    start++;
                }
            }
        }
        return (int) (target + sub); // 返回差值与 target 之和
    }
}
```

