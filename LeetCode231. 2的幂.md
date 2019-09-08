# 2的幂
>给定一个整数，编写一个函数来判断它是否是 2 的幂次方。

>示例 1:
输入: 1
输出: true
解释: 20 = 1

思路分析：
1. 挖掘 2 的幂次方数特点，二进制位有且仅有 1 个 1
2. 基于 191 题，统计 1 的个数，若不止 1 个 1 返回 fasle
```java
class Solution {
    // 2 的幂次方数特点，二进制位有且仅有 1 个 1
    // 基于 191 题，统计 1 的个数，若不止 1 个 1 返回 fasle
    public boolean isPowerOfTwo(int n) {
        if (n == 0 || n == Integer.MIN_VALUE) {
            return false; // 处理边界值
        }
        return (n&(n-1))==0; // 判断二进制位 1个数，是否为 1
    }
}
```

