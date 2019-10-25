# 反转字符串中的元音字母 [题目链接](https://leetcode-cn.com/problems/reverse-vowels-of-a-string/)
>编写一个函数，以字符串作为输入，反转该字符串中的元音字母。

>示例 1:
输入: "hello"
输出: "holle"

思路分析：**双指针法**，left、right 指针遍历至元音字符时停止

两指针都遍历到元音字符时交换字符，继续遍历，指针相交结束遍历返回结果
```java
class Solution {
    public String reverseVowels(String s) {
        if (s == null || s.length() < 2) {
            return s;
        }
        HashSet<Character> vowels = new HashSet<>( // 元音字符表
                Arrays.asList('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'));
        StringBuilder result = new StringBuilder(s);
        int left = 0, right = result.length() - 1;
        while (left < right) {
            char charLeft = result.charAt(left),
                 charRight = result.charAt(right);
            if (!vowels.contains(charLeft)) {
                left++;
            } else if (!vowels.contains(charRight)) {
                right--;
            } else {
                result.setCharAt(left, charRight);
                result.setCharAt(right, charLeft);
                right--;
                left++;
            } 
        }
        return result.toString();
    }
}
```





