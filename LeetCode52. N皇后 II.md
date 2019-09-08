# 52. N皇后 II
>给定一个整数 n，返回 n 皇后不同的解决方案的数量。

>示例:
>输入: 4
输出: 2
解释: 4 皇后问题存在如下两个不同的解法。
[
 [".Q..",  // 解法 1
  "...Q",
  "Q...",
  "..Q."],
 ["..Q.",  // 解法 2
  "Q...",
  "...Q",
  ".Q.."]
]

>来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/n-queens-ii

解题思路：基于 51 题，将生成结果集简化为统计解数即可。
```java
private int resultCount = 0; // 记录解数
    public int totalNQueens(int n) {
        int result[] = new int[n];
        backtracking(result,0);
        return resultCount;
    }
    private void backtracking(int[] result,int n){
        if (n == result.length) {
            resultCount++; // 更新计数
            return;
        }
        for (int i = 0; i < result.length; i++) {
            if (isValidation(result, n, i)) {
                result[n] = i;
                backtracking(result, n + 1);
            }
        }
    }
    // 验证，无同行/同列棋子
    // 对角线无棋子
    private boolean isValidation(int[] result, int row, int col) {
        for (int i = 0; i < row; i++) { // 第一行可以放任意位置
            int sub = row - i; // 行数差
            // 避免同列、对角线出现皇后
            if (result[i]==col||result[i]==col-sub||result[i]==col+sub) {
                return false;
            }
        }
        return true;
    }
```

