//给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
//
// 计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回 -1 。
//
// 你可以认为每种硬币的数量是无限的。
//
//
//
// 示例 1：
//
//
//输入：coins = [1, 2, 5], amount = 11
//输出：3
//解释：11 = 5 + 5 + 1
//
// 示例 2：
//
//
//输入：coins = [2], amount = 3
//输出：-1
//
// 示例 3：
//
//
//输入：coins = [1], amount = 0
//输出：0
//
//
//
//
// 提示：
//
//
// 1 <= coins.length <= 12
// 1 <= coins[i] <= 2³¹ - 1
// 0 <= amount <= 10⁴
//
// Related Topics 广度优先搜索 数组 动态规划 👍 1819 👎 0


import java.util.Arrays;
import java.util.Map;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
//    public int coinChange(int[] coins, int amount) {
//        // 自底向上
//        int[] count = new int[amount + 1];
//        Arrays.fill(count, amount+1);
//        count[0] = 0;
//        for (int i = 1; i < count.length; i++) {
//            for (int coin : coins) {
//                if (i - coin < 0) {
//                    continue;
//                }
//                count[i] = Math.min(count[i], count[i - coin]+1);
//            }
//        }
//        return count[amount] == amount+1 ? -1 : count[amount];
//    }

    public int coinChange(int[] coins, int amount) {
        // 自顶向下
        int[] memo = new int[amount + 1];
        Arrays.fill(memo, -2);
        traverse(memo, coins, amount);
        return traverse(memo, coins, amount);
    }

    private int traverse(int[] memo, int[] coins, int amount) {
        if (amount == 0) {
            return 0;
        }
        // 说明无法凑齐
        if (amount < 0) {
            return -1;
        }
        if (memo[amount] != -2) {
            return memo[amount];
        }
        int res = Integer.MAX_VALUE;
        for (int coin : coins) {
            int subResult = traverse(memo, coins, amount - coin);
            if (subResult == -1) {
                continue;
            }
            res = Math.min(res, subResult + 1);
        }
        memo[amount] = (res == Integer.MAX_VALUE) ? -1 : res;
        return memo[amount];
    }

}
//leetcode submit region end(Prohibit modification and deletion)
