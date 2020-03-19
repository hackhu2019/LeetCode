# 409. 最长回文串 [题目链接](https://leetcode-cn.com/problems/longest-palindrome/)
解题思路：

最长回文串为偶数字符总数+（存在奇数字符+1，不存在奇数字符+0）

```java
public int longestPalindrome(String s) {
        if (s == null || s.length() < 1) {
            return 0;
        }
        int oddLen = 0, evenLen = 0;
        Map<Character, Integer> map = new HashMap<>();
        // 统计字符出现次数
        for (char c : s.toCharArray()) {
            if (map.containsKey(c)) {
                map.put(c, map.get(c) + 1);
            } else {
                map.put(c, 1);
            }
        }
        // 遍历 map 更新 evenLen、oddLen
        for (Character key : map.keySet()) {
            evenLen += map.get(key) / 2 * 2;
            if (oddLen == 0 && map.get(key) % 2 == 1) {
                oddLen = 1;
            }
        }
        return oddLen + evenLen;
    }
```
算法优化：

1、考虑字符的特殊性，使用 int[128] 替换 HashMap

2、仅用一个变量 len 存储回文字符最大长度

```java
public int longestPalindrome(String s) {
        if (s == null || s.length() < 1) {
            return 0;
        }
        int len = 0;
        int[] countChars = new int[128];
        // 统计字符出现次数
        for (char c : s.toCharArray()) {
            countChars[c - 'a']++;
        }
        // 遍历 map 更新 evenLen、oddLen
        for (int countChar : countChars) {
            len += countChar / 2 * 2;
            if ((countChar & 1) == 1 && (len & 1) == 0) {
                len++;
            }
        }
        return len;
    }
```
