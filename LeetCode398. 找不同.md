# LeetCode398. 找不同

#### [389. 找不同](https://leetcode-cn.com/problems/find-the-difference/)

解题思路：计数法

1、使用两个 26 位数组（sMap,tMap）存储 s,t 两个字符串字符出现次数
2、 遍历两个 字符串 完成 sMap,tMap
3、比较 sMap,tMap 不同，返回不同字符

```java
public char findTheDifference(String s, String t) {
        int[] sMap = new int[26];
        int[] tMap = new int[26];
        for (char c : s.toCharArray()) {
            sMap[c - 'a']++;
        }
        for (char c : t.toCharArray()) {
            tMap[c - 'a']++;
        }
        for (int i = 0; i < sMap.length; i++) {
            if (sMap[i] != tMap[i]) {
                return (char)('a'+i) ;
            }
        }
        return ' ';
    }
```

优化：仅用一个数组存放次数，第二次遍历 t 元素做减法操作，最后找出 map 中所剩下仅出现 1 次的字母

```java
public char findTheDifference(String s, String t) {
        int[] map = new int[26];
        for (char c : t.toCharArray()) {
            map[c - 'a']++;
        }
        for (char c : s.toCharArray()) {
            map[c - 'a']--;
        }
        for (int i = 0; i < map.length; i++) {
            if (map[i] == 1) {
                return (char) ('a' + i);
            }
        }
        return ' ';
    }
```

再优化，使用加法，求和char数组之和，代替计数

```java
public char findTheDifference(String s, String t) {
        int charValue = 0;
        for (char c : t.toCharArray()) {
            charValue += c;
        }
        for (char c : s.toCharArray()) {
            charValue -= c;
        }
        return (char) charValue;
    }
```

终极优化，利用异或特性（任何一个数异或它本身等于 0 => a^a=0）

```java
public char findTheDifference(String s, String t) {
        int charValue = 0;
        for (char c : t.toCharArray()) {
            charValue ^= c;
        }
        for (char c : s.toCharArray()) {
            charValue ^= c;
        }
        return (char) charValue;
    }
```

