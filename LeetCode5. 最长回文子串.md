#  最长回文子串 [题目链接](https://leetcode-cn.com/problems/longest-palindromic-substring/)
>给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。

>示例 1：
>输入: "babad"
>输出: "bab"
>注意: "aba" 也是一个有效答案。

思路分析：

先找到回文数的判定条件，以某分界点左右对称，若是奇数长则从中间点对称。

特殊情况：回文串所有字母相同。

拆解：

1、依序遍历字符串，若遍历字符与相邻字符相等或是与相隔字符相等，则形成回文串。

2、判断字符串长度，先过滤相等字符部分。再通过两个游标一个向前一个向后判断是否还有对称字符。

3、判断新的回文串是否大于上一回文串，只保留最大回文子串。

```java
class Solution {
    public String longestPalindrome(String s) {
        // 回文串特点，从分界点对称，若为奇数长，则从中间点对称
        // 依序遍历字符串的每个字符，每次判断它是否与相邻或是相隔 1 个的字符相等，相等则产生回文串
        // 产生回文串时用两个游标从分界点开始一个向前一个向后，判断回文串的最大长度

        if (s.isEmpty())
            return s;
        String returnStr = s.substring(0, 1); // 默认最大回文串为起始有效字符
        int len = s.length();
        int startIndex = 0; // 初始化回文串的起始与结束
        int endIndex = 0;
        for (int i = 1; i < len; i++) { // 判断是否构成回文串基础条件
            if (s.charAt(i) == s.charAt(i - 1)) { // 处理特殊情况全部相等的回文字符串
                startIndex = i - 1;
                while(i+1<len&&s.charAt(i+1) == s.charAt(i - 1))
                {
                    i++;
                }
                endIndex = i;
            }
            else if (i > 1 && s.charAt(i) == s.charAt(i - 2)) {
                startIndex = i - 2;
                endIndex = i;
            }
            if (startIndex != endIndex) { // 不相等则存在新回文串
                while (startIndex-1>= 0 && endIndex+1 < len && s.charAt(startIndex-1) == s.charAt(endIndex+1)) { // 判断是否还有对称部分
                    startIndex--;
                    endIndex++;
                }
                
                if (endIndex - startIndex + 1 > returnStr.length()) { // 保留最长回文串
                    returnStr = s.substring(startIndex, endIndex+1);
                }
                startIndex = endIndex = 0; // 游标重置初始状态
            }
        }
        return returnStr;
    }
}
```

