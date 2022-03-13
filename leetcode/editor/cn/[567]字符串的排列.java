//给你两个字符串 s1 和 s2 ，写一个函数来判断 s2 是否包含 s1 的排列。如果是，返回 true ；否则，返回 false 。
//
// 换句话说，s1 的排列之一是 s2 的 子串 。
//
//
//
// 示例 1：
//
//
//输入：s1 = "ab" s2 = "eidbaooo"
//输出：true
//解释：s2 包含 s1 的排列之一 ("ba").
//
//
// 示例 2：
//
//
//输入：s1= "ab" s2 = "eidboaoo"
//输出：false
//
//
//
//
// 提示：
//
//
// 1 <= s1.length, s2.length <= 10⁴
// s1 和 s2 仅包含小写字母
//
// Related Topics 哈希表 双指针 字符串 滑动窗口 👍 601 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean checkInclusion(String s1, String s2) {
        int[] need = new int[26];
        int[] current = new int[26];
        int needSize = 0;
        // 初始化 need
        for (char c : s1.toCharArray()) {
            int index = c - 'a';
            if (need[index] == 0) {
                needSize++;
            }
            need[index] += 1;
        }
        int left = 0, right = 0, valid = 0;
        while (right < s2.length()) {
            Character c = s2.charAt(right);
            right++;
            int index = c - 'a';
            if (need[index] > 0) {
                current[index] += 1;
                if (current[index] == need[index]) {
                    valid++;
                }
            }
            while (right - left >= s1.length()) {
                if (valid == needSize) {
                    return true;
                }
                Character curChar = s2.charAt(left);
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
        return false;
    }




}
//leetcode submit region end(Prohibit modification and deletion)
