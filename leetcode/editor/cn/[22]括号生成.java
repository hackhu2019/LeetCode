//数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
//
//
//
// 示例 1：
//
//
//输入：n = 3
//输出：["((()))","(()())","(())()","()(())","()()()"]
//
//
// 示例 2：
//
//
//输入：n = 1
//输出：["()"]
//
//
//
//
// 提示：
//
//
// 1 <= n <= 8
//
// Related Topics 字符串 动态规划 回溯 👍 2481 👎 0


import java.util.ArrayList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    public List<String> generateParenthesis(int n) {
        List<String> resultList = new ArrayList<String>(n);
        traverse(resultList, n, n, new StringBuilder());
        return resultList;
    }

    private void traverse(List<String> resultList, int left, int right, StringBuilder str) {
        if (left < 0 || right < 0 || right < left) {
            return;
        }
        // 左右括号都用完，将 str 加入结果
        if (left == 0 && right == 0) {
            resultList.add(str.toString());
            return;
        }
        str.append("(");
        traverse(resultList, left-1, right, str);
        str.deleteCharAt(str.length() - 1);
        str.append(")");
        traverse(resultList, left, right - 1, str);
        str.deleteCharAt(str.length() - 1);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
