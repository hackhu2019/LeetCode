# 平方数之和
>给定一个非负整数 c ，你要判断是否存在两个整数 a 和 b，使得 a2 + b2 = c。

>示例1:
输入: 5
输出: True
解释: 1 * 1 + 2 * 2 = 5

>来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/sum-of-square-numbers

解题思路：**双指针法**，从【0-sqrt ( c )】中找出评方和为 c，left 从左至右开始取数，right 从右至左开始取数。

若平方和大于 c，right--，小于 c left++，等于则返回 true，指针相交则返回 false。

```java
class Solution {
    public boolean judgeSquareSum(int c) {
        int left = 0, right = (int) Math.sqrt(c);
        while (left <= right) {
            int sum = left * left + right * right;
            if (sum < c) {
                left++;
            } else if (sum > c) {
                right--;
            } else {
                return true;
            }
        }
        return false;
    }
}
```

