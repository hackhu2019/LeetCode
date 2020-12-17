# LeetCode.209单词规律

#### [290. 单词规律](https://leetcode-cn.com/problems/word-pattern/)

解题思路：双哈希表法

1、同时遍历 pattern 与 s 字符串，若 pattern 字符数量 s 字符串数 不相等，说明无法匹配，返回 false
2、建立两个哈希表，表明映射关系，strMap 以 s 字符为 key ， pattern 字符为 value；patternMap 以 pattern 字符为 key ， s 字符为 value
3、当同一key 对应不同 value 时，说明模式不匹配，返回 false

```java
public boolean wordPattern(String pattern, String s) {
        String[] strs = s.split(" ");
        char[] chars = pattern.toCharArray();
    	// 长度不一致，说明无法匹配
        if (strs.length != chars.length) {
            return false;
        }
        String[] patternMap = new String[26];
        Map<String, Character> strMap = new HashMap<>(26);
    	// 构建双哈希表，判断互相映射关系是否成立
        for (int i = 0; i < strs.length; i++) {
            if (!strMap.containsKey(strs[i]) && patternMap[chars[i] - 'a'] == null) {
                strMap.put(strs[i], chars[i]);
                patternMap[chars[i] - 'a'] = strs[i];
                continue;
            }
            if (strMap.get(strs[i])==null||
                    patternMap[chars[i] - 'a']==null ||
                    !strMap.get(strs[i]).equals(chars[i]) ||
                    !strs[i].equals(patternMap[chars[i] - 'a'])) {
                return false;
            }
        }
        return true;
    }
```

