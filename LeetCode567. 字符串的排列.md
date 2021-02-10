# LeetCode567. 字符串的排列

#### [567. 字符串的排列](https://leetcode-cn.com/problems/permutation-in-string/)

思路分析：

1、先判断 S2 窗口内是否满足：出现和 s1 同频次字符

 2、不满足，更新窗口

3、满足，判断是否存在和 s1 长度一致且字符出现频率也一致的子字符串

```java
public boolean checkInclusion(String s1, String s2) {
        char[] s1Chars = s1.toCharArray();
        char[] s2Chars = s2.toCharArray();
        int left = 0, right = 0, s1CharCount = 0, slideCharCount = 0;
        int[] s1CharFre = new int[26], slideCharFre = new int[26];
        // 统计 s1 出现字符频率与不同字符数
        for (char c : s1Chars) {
            s1CharFre[c - 'a']++;
        }
        for (int i = 0; i < s1CharFre.length; i++) {
            s1CharCount = s1CharFre[i] > 0 ? s1CharCount + 1 : s1CharCount;
        }
        // 移动滑动窗口
        while (right < s2.length()) {
            if (s1CharFre[s2Chars[right] - 'a'] > 0) {
                slideCharFre[s2Chars[right] - 'a']++;
                if (slideCharFre[s2Chars[right] - 'a'] == s1CharFre[s2Chars[right] - 'a']) {
                    slideCharCount++;
                }
            }
            right++;
            while (slideCharCount == s1CharCount) {
                if (right - left == s1.length()) {
                    return true;
                }
                if (s1CharFre[s2Chars[left] - 'a'] > 0) {
                    slideCharFre[s2Chars[left] - 'a']--;
                    if (slideCharFre[s2Chars[left] - 'a'] < s1CharFre[s2Chars[left] - 'a']) {
                        slideCharCount--;
                    }
                }
                left++;
            }
        }
        return false;
    }
```

