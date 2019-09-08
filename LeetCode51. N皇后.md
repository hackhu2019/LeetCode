# 51. N皇后
>n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
>给定一个整数 n，返回所有不同的 n 皇后问题的解决方案。

>每一种解法包含一个明确的 n 皇后问题的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。

>示例
>输入: 4
输出: [
 [".Q..",  // 解法 1
  "...Q",
  "Q...",
  "..Q."],

> ["..Q.",  // 解法 2
  "Q...",
  "...Q",
  ".Q.."]
]
解释: 4 皇后问题存在两个不同的解法

>来源：力扣（LeetCode）
>链接：https://leetcode-cn.com/problems/n-queens

解题思路：回溯+剪枝
1. 填补按顺序填补每一行皇后放置的位置，将皇后放置在符合规则的任意一列，让每一行皇后都符合要求
2. 满足条件：同列不能皇后，对角线不能出现皇后（上一行左对角线点列坐标：col-行数差，上一行右对角线点列坐标：col+行数差）
3. 记录每一行放置皇后的列坐标，当所有行都放置完，则填充当前解，加入返回结果集
```java
class Solution {
    private List<List<String>> results = new ArrayList<>(); // 结果集
    public List<List<String>> solveNQueens(int n) {
        int[] result = new int[n]; // 记录每一行皇后放置的 列坐标
        backtracking(result, 0);
        return results;
    }
    private void backtracking(int[] result,int n){
        if (n == result.length) { // 已放置完，每一行皇后都符合要求
            List<String> temp = new ArrayList<>();
            for (int i = 0; i < result.length; i++) {
                char[] str = new char[n];
                Arrays.fill(str, '.');
                str[result[i]] = 'Q';
                temp.add(new String(str)); // 根据每一行出现皇后的位置填充解
            }
            results.add(temp); // 加入当前解
        }
        for (int i = 0; i < result.length; i++) {
            if (isValidation(result, n, i)) { // 验证是否合法
                result[n] = i; // 记录放置坐标
                backtracking(result, n + 1); // 放置下一行皇后
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
}
```

