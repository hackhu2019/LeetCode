#  零钱兑换
>给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回 -1。

>示例 1:
输入: coins = [1, 2, 5], amount = 11
输出: 3 
解释: 11 = 5 + 5 + 1

>来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/coin-change

思路分析：推导， min[amount]=min(min[amount-coins1]..min[amount-coinsN])

```java
class Solution {
    public int coinChange(int[] coins, int amount) {
        if (coins == null || coins.length < 1) { // 处理空指针异常
            return -1;
        }
        long[] min = new long[amount + 1]; // 存储最小硬币数，long 处理溢出
        Arrays.fill(min, Integer.MAX_VALUE); // 初始化
        min[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (i >= coins[j]) {
                    min[i] = min[i] > min[i - coins[j]] + 1 ? min[i - coins[j]] + 1 : min[i];
                }
            }
        }
        return (int)(min[amount] == Integer.MAX_VALUE ? -1 : min[amount]);
    }
}
```

