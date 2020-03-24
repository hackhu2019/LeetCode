# 30. 串联所有单词的子串 [题目链接](https://leetcode-cn.com/problems/substring-with-concatenation-of-all-words/)
解法一思路：

暴力匹配，每次取 s 定长字符串，与 words 单词匹配，若能完全匹配则将子串起始索引加入结果

匹配规则：当前遍历字符串（s.substring(i, i + wordLen)）在 words 存在（去除完全不可能的匹配），且与 words 组成的哈希表也一致。

```java
public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new ArrayList<>();
        if (s == null || s.length() < 1 || words == null || words.length < 1) {
            return result;
        }
        int wordLen = words[0].length(), totalLen = wordLen * words.length;
        Map<String, Integer> wordsMap = new HashMap<>();
        for (String word : words) {
            wordsMap.put(word, wordsMap.getOrDefault(word, 0) + 1);
        }

        for (int i = 0; i <= s.length() - totalLen; i++) {
            if (wordsMap.containsKey(s.substring(i, i + wordLen)) &&
                    isEqual(s.substring(i, i + totalLen), wordsMap, wordLen)) {
                result.add(i);
            }
        }
        return result;
    }

    private boolean isEqual(String subStr, Map<String, Integer> wordsMap, int wordLen) {
        Map<String, Integer> subStrMap = new HashMap<>();
        for (int i = 0; i < subStr.length(); i += wordLen) {
            String tempStr = subStr.substring(i, i + wordLen);
            subStrMap.put(tempStr, subStrMap.getOrDefault(tempStr, 0) + 1);
        }
        return subStrMap.equals(wordsMap);
    }
```
解法二：滑动窗口，优化字符串匹配

```java
public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new ArrayList<>();
        if (s == null || s.length() < 1 || words == null || words.length < 1) {
            return result;
        }
        int wordLen = words[0].length(), totalLen = wordLen * words.length;
        Map<String, Integer> wordsMap = new HashMap<>();
        for (String word : words) {
            wordsMap.put(word, wordsMap.getOrDefault(word, 0) + 1);
        }

        for (int i = 0; i < wordLen; i++) {
            int left = i, right = i, match = 0;
            Map<String, Integer> window = new HashMap<>();
            while (right <= s.length() - wordLen) {
                String tempStr = s.substring(right, right + wordLen);
                if (wordsMap.containsKey(tempStr)) {
                    window.put(tempStr, window.getOrDefault(tempStr, 0) + 1);
                    if (window.get(tempStr) == wordsMap.get(tempStr)) {
                        match++;
                    }
                }
                right += wordLen;
                while (wordsMap.size() == match) {
                    if (right - left == totalLen) {
                        result.add(left);
                    }
                    String temp = s.substring(left, left + wordLen);
                    if (wordsMap.containsKey(temp)) {
                        window.put(temp, window.get(temp) - 1);
                        if (window.get(temp) < wordsMap.get(temp)) {
                            match--;
                        }
                    }
                    left += wordLen;
                }
            }
        }
        return result;
    }
```

