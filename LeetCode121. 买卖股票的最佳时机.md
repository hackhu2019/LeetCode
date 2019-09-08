#  买卖股票的最佳时机
>给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
如果你最多只允许完成一笔交易（即买入和卖出一支股票），设计一个算法来计算你所能获取的最大利润。

>注意你不能在买入股票前卖出股票。

>示例 1:
输入: [7,1,5,3,6,4]
输出: 5
解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
     注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格。

>来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock

思路分析：当前收益最大值 = 当前股价 - 前期最低买入股价（或不买）

min 记录前面的最小值，max 存储当前数减去最小值的最大值

```java
class Solution {
    public int maxProfit(int[] prices) {
    	// 处理空指针异常
        if (prices == null || prices.length < 1) { 
            return 0;
        }
        int min = prices[0], max = 0;
        for (int i = 1; i < prices.length; i++) {
            int money = prices[i] - min; // 当前卖出最大值
            max = max < money ? money : max; // 更新最大值
            min = min > prices[i] ? prices[i] : min; // 更新最小值
        }
        return max;
    }
}
```

