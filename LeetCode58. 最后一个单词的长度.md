# 最后一个单词的长度
>给定一个仅包含大小写字母和空格 ' ' 的字符串，返回其最后一个单词的长度。
如果不存在最后一个单词，请返回 0 。
说明：一个单词是指由字母组成，但不包含任何空格的字符串。

>示例:
输入: "Hello World"
输出: 5

>来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/length-of-last-word

思路分析：从后往前匹配，从第一个非空格字符开始匹配，每遍历一个非空格字符 count+1，在非空字符后出现空字符/遍历结束时，停止计数。

```java
public int lengthOfLastWord(String s) {
        if (s == null || s.length() < 1) {
            return 0;
        }
        int count = 0;
        boolean flag = false; // 标记是否已匹配非空字符
        for (int i = s.length()-1; i >=0 ; i--) {
            if (s.charAt(i) != ' ') {
                flag = true;
                count++;
                flag = true;
            } else if (flag&& s.charAt(i) == ' ') {
                break;
            }
        }
        return count;
    }
```

