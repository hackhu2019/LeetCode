//n 皇后问题 研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
//
// 给你一个整数 n ，返回所有不同的 n 皇后问题 的解决方案。
//
//
//
// 每一种解法包含一个不同的 n 皇后问题 的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
//
//
//
// 示例 1：
//
//
//输入：n = 4
//输出：[[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
//解释：如上图所示，4 皇后问题存在两个不同的解法。
//
//
// 示例 2：
//
//
//输入：n = 1
//输出：[["Q"]]
//
//
//
//
// 提示：
//
//
// 1 <= n <= 9
//
//
//
// Related Topics 数组 回溯 👍 1232 👎 0


import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    private List<List<String>> resultList;

    public List<List<String>> solveNQueens(int n) {
        //  初始化棋盘
        String[] board = new String[n];
        for (int i = 0; i < n; i++) {
            StringBuilder line = new StringBuilder();
            for (int j = 0; j < n; j++) {
                line.append(".");
            }
            board[i] = line.toString();
        }
        resultList = new ArrayList<>();
        traverse(board, 0);
        return resultList;
    }

    private void traverse(String[] board, int row) {
        if (row == board.length) {
            List<String> result = new ArrayList<>();
            for (String s : board) {
                result.add(s);
            }
            resultList.add(result);
        }
        for (int i = 0; i < board.length; i++) {
            if (!isValid(board, row, i)) {
                continue;
            }
            StringBuilder line = new StringBuilder(board[row]);
            line.setCharAt(i,'Q');
            board[row] = line.toString();
            traverse(board, row + 1);
            line.setCharAt(i,'.');
            board[row] = line.toString();
        }
    }

    private boolean isValid(String[] board, int row, int col) {
        int len = board.length;
        for (int i = 0; i < row; i++) {
            // 同列
            if (board[i].charAt(col) == 'Q') {
                return false;
            }
        }
        for (int i = row - 1, j = col + 1; i >= 0 && j < len; i--, j++) {
            // 右上
            if (board[i].charAt(j) == 'Q') {
                return false;
            }
        }
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            // 左上
            if (board[i].charAt(j) == 'Q') {
                return false;
            }
        }
        return true;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
