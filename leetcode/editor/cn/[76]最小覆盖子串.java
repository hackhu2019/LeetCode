//给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
//
//
//
// 注意：
//
//
// 对于 t 中重复字符，我们寻找的子字符串中该字符数量必须不少于 t 中该字符数量。
// 如果 s 中存在这样的子串，我们保证它是唯一的答案。
//
//
//
//
// 示例 1：
//
//
//输入：s = "ADOBECODEBANC", t = "ABC"
//输出："BANC"
//
//
// 示例 2：
//
//
//输入：s = "a", t = "a"
//输出："a"
//
//
// 示例 3:
//
//
//输入: s = "a", t = "aa"
//输出: ""
//解释: t 中两个字符 'a' 均应包含在 s 的子串中，
//因此没有符合条件的子字符串，返回空字符串。
//
//
//
// 提示：
//
//
// 1 <= s.length, t.length <= 10⁵
// s 和 t 由英文字母组成
//
//
//
//进阶：你能设计一个在 o(n) 时间内解决此问题的算法吗？ Related Topics 哈希表 字符串 滑动窗口 👍 1693 👎 0


import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String minWindow(String s, String t) {
        Map<Character, Integer> need = new HashMap<>();
        Map<Character, Integer> current = new HashMap<>();
        int needSize = 0;
        // 初始化 need
        for (char c : t.toCharArray()) {
            if (!need.containsKey(c)) {
                needSize++;
            }
            need.put(c, need.getOrDefault(c, 0) + 1);
        }
        String result = "";
        int left = 0, right = 0, valid = 0;
        while (right < s.length()) {
            Character c = s.charAt(right);
            right++;
            if (need.containsKey(c)) {
                current.put(c, current.getOrDefault(c, 0) + 1);
                // 注意对象比较使用 equals
                if (current.get(c).equals(need.get(c))) {
                    valid++;
                }
            }
            while (valid == needSize) {
                System.out.println("排查问题");
                if (result == "" || right - left < result.length()) {
                    result = s.substring(left, right);
                }
                Character curChar = s.charAt(left);
                left++;
                if (need.containsKey(curChar)) {
                    if (current.get(curChar).equals(need.get(curChar))) {
                        valid--;
                    }
                    current.put(curChar, current.get(curChar) - 1);
                }
            }
        }
        return result;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
