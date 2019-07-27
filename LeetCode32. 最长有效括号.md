# 32. 最长有效括号

> 给定一个只包含 '(' 和 ')' 的字符串，找出最长的包含有效括号的子串的长度。

> 示例 1:
> 输入: "(()" 输出: 2 解释: 最长有效括号子串为 "()"
> 来源：力扣（LeetCode）
> 链接：https://leetcode-cn.com/problems/longest-valid-parentheses

解题思路：

1. 找到括号匹配的条件： 左右括号数相等
2. left、right、max 分别记录左括号数、右括号数、最大括号对数
3. 第一遍正序遍历，分别记录左右括号数，当「右括号数大于左括号数」，则配对不成立，重新计数。当「左右括号数相等」，则匹配成立，max 记录最大括号对数。
4. 解决：一次遍历结束左括号数大于右括号数，未记录最大括号数问题。
5. 逆序遍历字符串，不成立匹配条件「左括号数大于右括号数」，继续记录匹配括号数。
6. 返回最大括号对数 max*2
```java
class Solution {
    public int longestValidParentheses(String s) {
        int left=0; // 左括号数
        int right = 0; // 右括号数
        int max=0; // 匹配括号对数
        int len = s.length(); // 字符串长度
        for (int i = 0; i < len; i++) {
            if (s.charAt(i)=='('){
                left++;
            }else {
                right++;
            }
            if (right > left) { // 不满足配对则置零
                right = 0;
                left = 0;
            } else if (right == left) { // 满足匹配记录括号数
                max = max > left ? max : left;
            }
        }
        // 置零
        right=0;
        left = 0;
        for (int i = len-1; i >= 0; i--) {
            if (s.charAt(i)=='('){
                left++;
            }else {
                right++;
            }
            if (right < left) { // 不满足配对则置零
                right = 0;
                left = 0;
            } else if (right == left) { // 满足匹配记录括号数
                max = max > left ? max : left;
            }
        }

        return max*2;
    }
}
```
逆序遍历，解决一次遍历结束左括号数大于右括号数，未记录最大括号数问题。参考了[官方题解的思路](https://leetcode-cn.com/problems/longest-valid-parentheses/solution/zui-chang-you-xiao-gua-hao-by-leetcode/)。
