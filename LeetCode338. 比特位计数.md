# 比特位计数
>给定一个非负整数 num。对于 0 ≤ i ≤ num 范围中的每个数字 i ，计算其二进制数中的 1 的数目并将它们作为数组返回。

>示例 1:
输入: 2
输出: [0,1,1]

>来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/counting-bits

思路分析：
1. 方法 1，对于 0~num 中的每个数计算二进制位 1 的个数，可参考 191 题
2. 方法 2，寻找规律，5 的二进制位 个数为 4 的二进制位个数 +1， 2 的 二进制位为 0 的二进制位 +1，
3. 归纳 count[i]=count[i&(i-1)]+1，即，当前数二进制位 1 个数 = 最低位 1 消除后的数的二进制 1 个数 +1

方法 2 ，参考代码
```java
class Solution {
   public int[] countBits(int num) {
        if (num < 0) { // 避免数组越界异常
            return null;
        }
        int[] count = new int[num + 1];
        count[0] = 0; // 初始化 0 的二进位个数
        for (int i = 1; i <= num; i++) {
            // 当前数二进制位 1 个数 = 最低位 1 消除后的数的二进制 1 个数 +1
            count[i] = count[i & (i - 1)] + 1;
        }
        return count;
    }
}
```

