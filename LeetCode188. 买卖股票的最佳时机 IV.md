# 188. 买卖股票的最佳时机 IV
>给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
设计一个算法来计算你所能获取的最大利润。你最多可以完成 k 笔交易。
注意: 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。

>示例 1:
输入: [2,4,1], k = 2
输出: 2
解释: 在第 1 天 (股票价格 = 2) 的时候买入，在第 2 天 (股票价格 = 4) 的时候卖出，这笔交易所能获得利润 = 4-2 = 2 。

>来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iv

思路分析：
1. **动态规划，从结果向起始点推导**。
2. 第 i 天，第 j 笔 交易的最大盈利由第 i-1 天决定，**卖出股票** 或 **买入股票**，决定因素：不超过 K 笔交易，第 i-1 天持有股票。根据这两种状态由第 i-1 天状态递推。
3. **第 i 天买入，最大收益**：保持 第 i-1 天，第 k 笔 交易，持有股票； 第 i-1 天，第 k 笔 交易，不持有股票，按当天股价买入。（即 第 i 天 第 K 笔交易保持持有股票状态 最大收益）
4.  **第 i 天买出，最大收益**：保持 第 i-1 天，第 k 笔 交易，不持有股票； 第 i-1 天，第 k 笔 交易，持有股票，按当天股价卖出。（即 第 i 天 第 K 笔交易保持不持有股票状态 最大收益）
5. 题目限制「不能同时参与多笔交易」所以，完成一笔交易（买入——卖出）至少需要两天。防止 k 值过大，所以当 k > prices.length /2 时，即不限制交易次数，K 值无效，可以用「贪心」/ 「动态规划」单独求解。
```java
class Solution {
    public int maxProfit(int k, int[] prices) {
        if (prices == null || prices.length < 1 || k < 1) {
            return 0;
        }
        if (k > prices.length >> 1) {
            return maxProfit(prices);
        }
        // 初始化 dp 数组,第 i 天，第 k 笔 交易，是否持有股票,1 持有，0 不持有
        int[][][] mp = new int[prices.length][k + 1][2];
        // 初始化第一天数据，买入股票
        for (int i = 1; i < k+1; i++) {
            mp[0][i][1] = -prices[0];
        }
        // 依次递归最大收益
        for (int i = 1; i < prices.length; i++) {
            for (int j = 1; j <= k; j++) {
                // 第 i 天，第 j 笔 交易，不持有股票的最大值=
                // 前一天持有股票今天卖出 / 前一天不持有股票，保持不变
                mp[i][j][0] = Math.max(mp[i - 1][j][0], mp[i - 1][j][1] + prices[i]);
                // 第 i 天，第 j 笔 交易，持有股票的最大值=
                // 前一天不持有股票今天买入 / 前一天持有股票，保持不变
                mp[i][j][1] = Math.max(mp[i - 1][j][1], mp[i - 1][j - 1][0] - prices[i]);
            }
        }
        return mp[prices.length - 1][k][0]; // 最大利润
    }
    // 不限制交易次数
    public int maxProfit(int[] prices) {
        int profit = 0;
        // 贪婪算法，存在利润就卖出
        for (int i = 0; i < prices.length-1; i++) {
            if (prices[i] < prices[i + 1]) {
                profit += prices[i + 1] - prices[i];
            }
        }
        return profit;
    }
}
```

