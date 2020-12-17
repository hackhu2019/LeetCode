# LeetCode714. 买卖股票的最佳时机含手续费

#### [714. 买卖股票的最佳时机含手续费](https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/)

参考 [官方题解](https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/solution/mai-mai-gu-piao-de-zui-jia-shi-ji-han-sh-rzlz/)

解法一：贪心算法

思路分析：

1、求利润最大化，则在 **每次** 交易过程中，最低价买入，最高价卖出（利润需要高于手续费）

2、buy 记录当前买入股票价格+本次交易手续费，初始化为 prices[0]+fee；profit 记录当前股票买卖收益

3、依次遍历股价，若当天股价+费用< buy，则更换股票买入时间为当天（buy=prince[i]+fee）

4、若 当天股价+费用> buy，则当天卖出再买入 profit= 当天股价 - buy，buy=prince[i]+fee （此时还在一笔交易过程，手续费只需扣除一次）

```java
public int maxProfit(int[] prices, int fee) {
        int buy = prices[0] + fee;
        int profit = 0;
        for (int i = 1; i < prices.length; i++) {
            int price = prices[i] + fee;
            // 当天股价+费用< buy，则更换股票买入
            if (buy > price) {
                buy = price;
            } else if (buy < prices[i]) {  // 在第二次交易之前找到更高价交易，不需要交第二笔手续费
                profit += prices[i] - buy;
                buy = prices[i];
            }
        }
        return profit;
    }
```



解法二：动态规划

当前最大收益 max(未持有股票收益，持有股票收益) profit[n]\[0]，profit[n]\[1]（当前未购入股票收益更高）

未持有股票收益 profit[n]\[0]=max(profit[n-1]\[0],profit[n-1]\[1]+prices[n]-fee)

持有股票收益 profit[n]\[0]=max(profit[n-1]\[0]-prices[n],profit[n-1]\[1])

```java
public int maxProfit(int[] prices, int fee) {
        int n = prices.length;
        int[][] profits = new int[n][2];
        // 初始化状态方程
        profits[0][0] = 0;
        profits[0][1] = -prices[0];
        for (int i = 1; i < n; i++) {
            profits[i][0] = Math.max(profits[i - 1][0], profits[i - 1][1] + prices[i] - fee);
            profits[i][1] = Math.max(profits[i - 1][1], profits[i - 1][0] - prices[i]);
        }
        return profits[n - 1][0];
    }
```

简化代码，使用 notHoldProfit 、holdProfit 代替 int[]\[] profits = new int[n]\[2]  表示当前持有、未持有股票收益

```java
public int maxProfit(int[] prices, int fee) {
        int n = prices.length;
        // 初始化状态方程
        int notHoldProfit = 0;
        int holdProfit = -prices[0];
        for (int i = 1; i < n; i++) {
            notHoldProfit = Math.max(notHoldProfit, holdProfit + prices[i] - fee);
            holdProfit = Math.max(holdProfit, notHoldProfit - prices[i]);
        }
        return notHoldProfit;
    }
```

