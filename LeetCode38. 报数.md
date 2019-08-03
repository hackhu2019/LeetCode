# 报数
> 报数序列是一个整数序列，按照其中的整数的顺序进行报数，得到下一个数。其前五项如下：

1.     1
2.     11
3.     21
4.     1211
5.     111221

> 1 被读作  "one 1"  ("一个一") , 即 11。 
> 11 被读作 "two 1s" ("两个一"）, 即 21。
>  21 被读作"one 2",  "one 1" （"一个二" ,  "一个一") , 即 1211。
> 给定一个正整数 n（1 ≤ n ≤ 30），输出报数序列的第 n 项。

> 注意：整数顺序将表示为一个字符串。
> 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/count-and-say

注意理解题意：题目其实是报前一个数，即第二个人报第一个数：1一个 1，记 11，第三个人报第二个数：2个 1，记 21，以此类推。

解题思路：对此类，依据上一情况求解，存在重复操作题目，采用递归解题（同样能用递归也能用循环解决）。

递归结束条件，n == 1
操作：记录上一个数中每个数字出现的次数。
返回记录数据。
```java
class Solution {
    public String countAndSay(int n) {
        if (n == 1) {
            return "1";
        }
        StringBuilder before =new StringBuilder(countAndSay(n - 1)) ; // 递归调用
        // 根据字符出现的次数统计
        StringBuilder result = new StringBuilder(""); // 字符串频繁增加，使用 StringBuilder 减少内存消耗
        int count = 1;
        char target = before.charAt(0);
        for (int i = 1; i < before.length(); i++) {
            if (before.charAt(i) != target) {
                result .append(count +""+ target);
                target = before.charAt(i);
                count = 1;
            } else {
                count++;
            }
        }
        result .append(count +""+ target);
        return result.toString();
    }
}
```

