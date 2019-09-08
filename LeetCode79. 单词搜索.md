# 单词搜索
> 给定一个二维网格和一个单词，找出该单词是否存在于网格中。
> 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。

> 示例:
> board =
[
  ['A','B','C','E'],
  ['S','F','C','S'],
  ['A','D','E','E']
]

> 给定 word = "ABCCED", 返回 true.
> 给定 word = "SEE", 返回 true.
> 给定 word = "ABCB", 返回 false.

>来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/word-search

思路分析：
1. memo 记录已访问坐标，每次访问邻近坐标，上下左右移动，若与字符串当前字符相等，则寻找下一字符
2.  遍历到符合路径则返回 true，无符合路径，返回 false
```java
class Solution {
    private boolean result = false; // 默认结果为 false
    private boolean[][] memo; // 记录每个字符是否被使用
    private int[] moveRow = {-1, 1, 0, 0}, moveCol = {0, 0, -1, 1}; // 坐标上下左右移动
    public boolean exist(char[][] board, String word) {
        if (board == null || word == null) { // 处理空指针异常
            return false;
        }
        memo = new boolean[board.length][]; // 初始化备忘录
        for (int i = 0; i < board.length; i++) {
            memo[i] = new boolean[board[i].length];
        }
        for (int row = 0; row < board.length; row++) { // 以任意点为起点
            for (int col = 0; col < board[row].length; col++) {
                DFS(board, row, col, word,0);
                if (result) { // 找到答案，结束查找
                    return true;
                }
            }
        }
        return result;
    }
    private void DFS(char[][]board,int row,int col,String word,int index) {
        if (index == word.length()) { // 找到对应单词
            result = true; // 修改
            return;
        }
        if (result || row < 0 || col < 0 ||
                row >= board.length ||
                col >= board[row].length||
                memo[row][col]|| // 确认坐标未越界且未被访问
                board[row][col]!=word.charAt(index)) { // // 找到答案/不满足要求，结束查找
            return;
        }
        memo[row][col] = true; // 记录已被使用
        for (int i = 0; i < 4; i++) { // 访问符合要求的 上下左右相邻节点
            int nextRow = row + moveRow[i], nextCol = col + moveCol[i];
            DFS(board,nextRow,nextCol,word,index+1);
        }
        memo[row][col] = false; // 还原
    }
}
```

