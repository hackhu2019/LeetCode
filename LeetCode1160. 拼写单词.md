# LeetCode1160 [题目链接](https://leetcode-cn.com/problems/find-words-that-can-be-formed-by-characters/)
思路分析：

以 chars 建立哈希表 dictionary(字符，出现次数)

建立辅助方法 isValid(),判断 words 中字符是否完全匹配

匹配规则：word 字符在 dictionary 中出现，且数量大于 0，每匹配一次数量-1

len 记录已完全匹配字符累计长度

```java
public int countCharacters(String[] words, String chars) {
        Map<Character, Integer> dictionary = new HashMap<>();
        for (char c : chars.toCharArray()) {
            if (dictionary.containsKey(c)) {
                dictionary.put(c, dictionary.get(c) + 1);
            } else {
                dictionary.put(c, 1);
            }
        }
        int len = 0;
        for (String word : words) {
            if (isValid(word, new HashMap<>(dictionary))) {
                len += word.length();
            }
        }
        return len;
    }

    private boolean isValid(String word, Map<Character, Integer> dictionary) {
        for (char c : word.toCharArray()) {
            if (!dictionary.containsKey(c) || dictionary.get(c) < 1) {
                return false;
            } else {
                dictionary.put(c, dictionary.get(c) - 1);
            }
        }
        return true;
    }
```
优化方向：

1、以 int[26] 代替哈希表，减少哈希函数带来的消耗

2、优化匹配函数 isValid()，以 word 创建哈希表，判断 word 是否满足 **匹配规则**（字符在 word 中出现的次数<= dictionary 中字符出现次数）。

```java
class Solution {
    public int countCharacters(String[] words, String chars) {
        int[] diction = createDiction(chars);
        int len = 0;
        for (String word : words) {
            if (isValid(createDiction(word), diction)) {
                len += word.length();
            }
        }
        return len;
    }

    private int[] createDiction(String word) {
        int[] diction = new int[26];
        for (char c : word.toCharArray()) {
            diction[c - 'a'] += 1;
        }
        return diction;
    }

    private boolean isValid(int[] word, int[] dictionary) {
        for (int i = 0; i < word.length; i++) {
            if (word[i] > dictionary[i]) {
                return false;
            }
        }
        return true;
    }
}
```

