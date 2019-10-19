#  跳跃游戏
>给定一个非负整数数组，你最初位于数组的第一个位置。
数组中的每个元素代表你在该位置可以跳跃的最大长度。
判断你是否能够到达最后一个位置。

>示例 1:
输入: [2,3,1,1,4]
输出: true
解释: 从位置 0 到 1 跳 1 步, 然后跳 3 步到达最后一个位置。

>来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/jump-game

解法一：正推，依次遍历每个格子，记录当前所能跳跃最大索引值，当无法跳跃到当前格子时，返回 false

```java
public boolean canJump(int[] nums) {
        if (nums == null || nums.length < 1) {
            return false;
        }
        int max=0;
        for (int i = 0; i < nums.length; i++) {
            if (i > max) {
                return false;
            }
            max = Math.max(max, i + nums[i]);
        }
        return true;
    }
```
解法二：由果溯因，从终点向起点走，若能从终点走回起点，则说明可以跳跃，否，无法成功跳跃

```java
public boolean canJump(int[] nums) {
        if (nums == null || nums.length < 1) {
            return false;
        }
        int target = nums.length - 1;
        for (int i = target; i >= 0; i--) {
            if (i + nums[i] >= target) {
                target = i;
            }
        }
        return target == 0;
    }
```

