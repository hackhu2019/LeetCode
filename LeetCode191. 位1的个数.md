#  位1的个数
>编写一个函数，输入是一个无符号整数，返回其二进制表达式中数字位数为 ‘1’ 的个数（也被称为汉明重量）。

>示例 1：
输入：00000000000000000000000000001011
输出：3
解释：输入的二进制串 00000000000000000000000000001011 中，共有三位为 '1'。

>来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/number-of-1-bits

思路分析：利用 & 运算技巧，只有二进制位同时为 1 时，运算结果为 1， x&(x-1) 从右至左消除二进制 1
```java
public class Solution {
    // 利用二进制 x&(x-1) 从右至左消除二进制 1
    public int hammingWeight(int n) {
        int count = 0;
        while (n != 0) { // 消除所有 1，归零
            n &= n - 1;
            count++;
        }
        return count;
    }
}
```


