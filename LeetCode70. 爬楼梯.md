# 70. 爬楼梯
>假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
注意：给定 n 是一个正整数。

>示例 1：
输入： 2
输出： 2
解释： 有两种方法可以爬到楼顶。
>1.  1 阶 + 1 阶
>2.  2 阶

>来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/climbing-stairs

思路分析：实际上和「斐波那契数列」问题一样，f(n)=f(n-1)+f(n-2) 

状态转换方程：当前走法 = 走一步走法 + 走两步走法。

正难则反，从第一层结果往上推导
```java
class Solution {
    public int climbStairs(int n) {
        int f1 = 1, f2 = 1,count=1; // 初始化第一层走法，f1、f2 分别记录走一步走法和走两步走法
        for (int i = 2; i <= n; i++) {
            count = f1 + f2;
            f2 = f1;
            f1 = count; // 更新走一步走法、走两步走法
        }
        return count;
    }
}
```

