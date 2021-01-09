﻿# 123. 买卖股票的最佳时机 III
>给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
设计一个算法来计算你所能获取的最大利润。你最多可以完成 两笔 交易。
注意: 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。

>示例 1:
输入: [3,3,5,0,0,3,1,4]
输出: 6
解释: 在第 4 天（股票价格 = 0）的时候买入，在第 6 天（股票价格 = 3）的时候卖出，这笔交易所能获得利润 = 3-0 = 3 。
     随后，在第 7 天（股票价格 = 1）的时候买入，在第 8 天 （股票价格 = 4）的时候卖出，这笔交易所能获得利润 = 4-1 = 3 。

>来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iii

思路分析：可以先做 188 题。
1. 决定第 i 天，第 k 笔交易利润的因素有三个：第 i-1 天的收益、是否持有股票、是否超过次数限制 —— 三维数组来表示不同的决定因素。
2. **第 i 天买入，最大收益**：保持 第 i-1 天，第 k 笔 交易，持有股票； 第 i-1 天，第 k 笔 交易，不持有股票，按当天股价买入。（即 第 i 天 第 K 笔交易保持持有股票状态 最大收益）
3.  **第 i 天买出，最大收益**：保持 第 i-1 天，第 k 笔 交易，不持有股票； 第 i-1 天，第 k 笔 交易，持有股票，按当天股价卖出。（即 第 i 天 第 K 笔交易保持不持有股票状态 最大收益）
```java
public int maxProfit(int[] prices) {
        if (prices == null || prices.length < 1) {
            return 0;
        }
        // 初始化 dp 数组,第 i 天，第 k 笔 交易，是否持有股票,1 持有，0 不持有
        int[][][] mp = new int[prices.length][3][2];
        // 初始化第一天数据，买入股票
        for (int i = 1; i < 3; i++) {
            mp[0][i][1] = -prices[0];
        }
        for (int i = 1; i < prices.length; i++) {
            for (int j = 1; j <= 2; j++) {
                // 第 i 天，第 j 笔 交易，不持有股票的最大值=
                // 前一天持有股票今天卖出 / 前一天不持有股票，保持不变
                mp[i][j][0] = Math.max(mp[i - 1][j][0], mp[i - 1][j][1] + prices[i]);
                // 第 i 天，第 j 笔 交易，持有股票的最大值=
                // 前一天不持有股票今天买入 / 前一天持有股票，保持不变
                mp[i][j][1] = Math.max(mp[i - 1][j][1], mp[i - 1][j - 1][0] - prices[i]);
            }
        }
        return mp[prices.length - 1][2][0];
    }
```

思路优化：参考 [官方题解](https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iii/solution/mai-mai-gu-piao-de-zui-jia-shi-ji-iii-by-wrnt/)

在股票交易的第 N 天可能发生的 5 中情况：

1、未发生过任何操作

2、首次交易，买入当天股票

3、首次交易，前一日持有股票，今天卖出

4、第二次交易，今天买入股票

5、第二次交易，卖出当前持有股票

使用 buy1\sell1\buy2\sell2 分别代表 2、3、4、5 几种状态
1、buy1=Max(buy1,-prices[n])

2、sell1=Max(sell1,buy1+prices[n])

3、buy2=Max(buy2,sell1-prices[n])

4、sell2=Max(sell2,buy2+prices[n])

存在 sell2>=sell1 ，所以 最终返回结果为 sell2

```java
public int maxProfit(int[] prices) {
        int buy1 = -prices[0],
                buy2 = -prices[0],
                sell1 = 0,
                sell2 = 0;
        for (int i = 1; i < prices.length; i++) {
            buy1 = Math.max(buy1, -prices[i]);
            sell1 = Math.max(sell1, buy1 + prices[i]);
            buy2 = Math.max(buy2, sell1 - prices[i]);
            sell2 = Math.max(sell2, buy2 + prices[i]);
        }
        return sell2;
    }
```

