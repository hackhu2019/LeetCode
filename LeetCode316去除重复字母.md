# LeetCode316去除重复字母

#### [316. 去除重复字母](https://leetcode-cn.com/problems/remove-duplicate-letters/)

解题思路：

1、count[26] 记录每个字符出现次数，visited[26] 记录每个字母是否出现，遍历 s 字符，StringBuilder result 记录结果字符串
2、依序遍历 s 字符，若当前遍历字符，大于 result 末尾字符，且 result 末尾字符出现次数不为 0 则移除末尾字符， result 末尾字符出现次数 -1，直到 result 末尾字符小于当前遍历字符 or  result 末尾字符出现次数为 0
3、比较结束 result 加入当前遍历字符

```java
class Solution {
    public String removeDuplicateLetters(String s) {
        boolean[] visited = new boolean[26];
        int[] count = new int[26];
        StringBuilder result = new StringBuilder();
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }
        for (char c : s.toCharArray()) {
            if (!visited[c - 'a']) {
                while (result.length() > 0 && result.charAt(result.length() - 1) > c 
                && count[result.charAt(result.length() - 1) - 'a'] > 0) {
                    visited[result.charAt(result.length() - 1) - 'a'] = false;
                    result.deleteCharAt(result.length() - 1);
                }
                visited[c - 'a'] = true;
                result.append(c);
            }
            count[c - 'a']--;
        }
        return result.toString();
    }
}
```

