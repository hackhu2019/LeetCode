# 151. 翻转字符串里的单词 [题目链接](https://leetcode-cn.com/problems/reverse-words-in-a-string/)
解法一：去除字符串首尾空格，从尾部开始遍历字符串

```java
public String reverseWords(String s) {
        if (s == null || s.length() < 1) {
            return s;
        }
        char[] str = s.trim().toCharArray();
        StringBuilder newStr = new StringBuilder(str.length);
        int index = str.length - 1;
        while (index >= 0) {
            while (index >= 0 && str[index] == ' ') {
                index--;
            }
            int start = index;
            while (start > 0 && str[start - 1] != ' ') {
                start--;
            }
            newStr.append(Arrays.copyOfRange(str, start, index));
            newStr.append(" ");
            index = start - 1;
        }
        if (newStr.length() > 0) {
            newStr.deleteCharAt(newStr.length() - 1);
        }
        return newStr.toString();
    }
```
解法二：去除字符串首尾空格，正则表达式匹配多个空格，切分字符串，倒序加入切分字符

```java
class Solution {
    public String reverseWords(String s) {
        if (s == null || s.length() < 1) {
            return s;
        }
        String str = s.trim();
        String[] strs = str.split("\\s+");
        StringBuilder newStr = new StringBuilder(str.length());
        for (int i = strs.length - 1; i >= 0; i--) {
            newStr.append(strs[i] + " ");
        }
        if (newStr.length() > 0) {
            newStr.deleteCharAt(newStr.length() - 1);
        }
        return newStr.toString();
    }
}
```
