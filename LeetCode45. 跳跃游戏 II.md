# 跳跃游戏 II

> 给定一个非负整数数组，你最初位于数组的第一个位置。
> 数组中的每个元素代表你在该位置可以跳跃的最大长度。
> 你的目标是使用最少的跳跃次数到达数组的最后一个位置。
 
> 示例:
> 输入: [2,3,1,1,4] 
> 输出: 2 
> 解释: 跳到最后一个位置的最小跳跃数是 2。
> 从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。
>  来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/jump-game-ii

思路分析：基于「贪心算法」，每次选择所能跳跃最远的点作为下一跳跃点

跳跃距离=当前坐标+所能跳跃最大值

```java
public int jump(int[] nums) {
        int index = 0, count =0; // 记录当前索引以及跳跃次数
        while (index+nums[index] < nums.length-1) { // 若当前已在终点或下一次能够到达终点则结束跳跃
            int max = 1; // 默认下一跳跃点为最大点
            for (int i = 2; i <= nums[index]; i++) {
                if (nums[index + i]+ i>= nums[index+max]+max) { // 选取最大跳跃
                    max = i;
                }
            }
            index+=max; // 跳跃
            count++; // 计数
        }
        return index==nums.length-1?count:count+1; // 若下一次跳跃才能达到终点，则次数+1
    }
```

