//给定两个字符串 s 和 p，找到 s 中所有 p 的 异位词 的子串，返回这些子串的起始索引。不考虑答案输出的顺序。
//
// 异位词 指由相同字母重排列形成的字符串（包括相同的字符串）。
//
//
//
// 示例 1:
//
//
//输入: s = "cbaebabacd", p = "abc"
//输出: [0,6]
//解释:
//起始索引等于 0 的子串是 "cba", 它是 "abc" 的异位词。
//起始索引等于 6 的子串是 "bac", 它是 "abc" 的异位词。
//
//
// 示例 2:
//
//
//输入: s = "abab", p = "ab"
//输出: [0,1,2]
//解释:
//起始索引等于 0 的子串是 "ab", 它是 "ab" 的异位词。
//起始索引等于 1 的子串是 "ba", 它是 "ab" 的异位词。
//起始索引等于 2 的子串是 "ab", 它是 "ab" 的异位词。
//
//
//
//
// 提示:
//
//
// 1 <= s.length, p.length <= 3 * 10⁴
// s 和 p 仅包含小写字母
//
// Related Topics 哈希表 字符串 滑动窗口 👍 803 👎 0


import java.util.ArrayList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    public List<Integer> findAnagrams(String s, String p) {
        int[] need = new int[26];
        int[] current = new int[26];
        int needSize = 0;
        // 初始化 need
        for (char c : p.toCharArray()) {
            int index = c - 'a';
            if (need[index] == 0) {
                needSize++;
            }
            need[index] += 1;
        }
        List<Integer> result = new ArrayList<Integer>();
        int left = 0, right = 0, valid = 0;
        while (right < s.length()) {
            Character c = s.charAt(right);
            right++;
            int index = c - 'a';
            if (need[index] > 0) {
                current[index] += 1;
                if (current[index] == need[index]) {
                    valid++;
                }
            }
            while (right - left >= p.length()) {
                if (valid == needSize) {
                    result.add(left);
                }
                Character curChar = s.charAt(left);
                left++;
                int curIndex = curChar - 'a';
                if (need[curIndex] > 0) {
                    if (current[curIndex]==need[curIndex]) {
                        valid--;
                    }
                    current[curIndex]--;
                }
            }
        }
        return result;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
