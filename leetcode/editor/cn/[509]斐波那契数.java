//斐波那契数 （通常用 F(n) 表示）形成的序列称为 斐波那契数列 。该数列由 0 和 1 开始，后面的每一项数字都是前面两项数字的和。也就是：
//
//
//F(0) = 0，F(1) = 1
//F(n) = F(n - 1) + F(n - 2)，其中 n > 1
//
//
// 给定 n ，请计算 F(n) 。
//
//
//
// 示例 1：
//
//
//输入：n = 2
//输出：1
//解释：F(2) = F(1) + F(0) = 1 + 0 = 1
//
//
// 示例 2：
//
//
//输入：n = 3
//输出：2
//解释：F(3) = F(2) + F(1) = 1 + 1 = 2
//
//
// 示例 3：
//
//
//输入：n = 4
//输出：3
//解释：F(4) = F(3) + F(2) = 2 + 1 = 3
//
//
//
//
// 提示：
//
//
// 0 <= n <= 30
//
// Related Topics 递归 记忆化搜索 数学 动态规划 👍 425 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
//    public int fib(int n) {
//        // 自底向上
//        if (n == 0) {
//            return 0;
//        }
//        int n1 = 0, n2 = 1;
//        for (int i = 2; i <= n; i++) {
//            int temp = n2;
//            n2 = n1 + n2;
//            n1 = temp;
//        }
//        return n2;
//    }

    public int fib(int n) {
        // 自顶向下
        int[] memo = new int[n + 1];
        return fn(n, memo);
    }

    private int fn(int n, int[] memo) {
        if (n == 0) {
            return 0;
        }
        if (n == 1||n==2) {
            return 1;
        }
        if (memo[n] != 0) {
            return memo[n];
        }
        memo[n] = fn(n - 1, memo) + fn(n - 2, memo);
        return memo[n];
    }
}
//leetcode submit region end(Prohibit modification and deletion)
