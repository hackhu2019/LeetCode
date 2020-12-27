# LeetCode205. 同构字符串

#### [205. 同构字符串](https://leetcode-cn.com/problems/isomorphic-strings/)

解题思路：双哈希表法

1、遍历 s、t 字符，哈希表 map1 以 s 遍历字符为 key， t 遍历字符为 value；哈希表 map2 以 t 遍历字符为 key， s 遍历字符为 value

2、若 s 当前遍历字符字符在 map1 已出现且对应 value 不等于 t 当前 遍历字符，返回 false

3、若 t 当前遍历字符字符在 map2 已出现且对应 value 不等于 s 当前 遍历字符，返回 false

3、s、t 正常遍历结束，返回 true

```java
public boolean isIsomorphic(String s, String t) {
        Map<Character, Character> map1 = new HashMap<>(26);
        Map<Character, Character> map2 = new HashMap<>(26);
        for (int i = 0; i < s.length(); i++) {
            char sChar = s.charAt(i);
            char tChar = t.charAt(i);
            if ((map1.containsKey(sChar) && map1.get(sChar) != tChar) ||
                    (map2.containsKey(tChar) && map2.get(tChar) != sChar)) {
                return false;
            }
            map1.put(sChar, tChar);
            map2.put(tChar, sChar);
        }
        return true;
    }
```

优化，参考思路：[[C++/Java] 高效写法](https://leetcode-cn.com/problems/isomorphic-strings/solution/c-gao-xiao-xie-fa-by-francissoft-le3k/) 使用 128 位数组表示代替哈希表存储 s、t 映射关系

```java
public boolean isIsomorphic(String s, String t) {
        int[] map1 = new int[128];
        int[] map2 = new int[128];
        for (int i = 0; i < s.length(); i++) {
            char sChar = s.charAt(i);
            char tChar = t.charAt(i);
            if (map1[sChar]!=map2[tChar]) {
                return false;
            }
            map1[sChar] = map2[tChar] = i + 1;
        }
        return true;
    }
```

