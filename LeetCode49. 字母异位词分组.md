#  字母异位词分组

> 给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。

> 示例:
> 输入: ["eat", "tea", "tan", "ate", "nat", "bat"], 
> 输出: [  ["ate","eat","tea"],   ["nat","tan"],   ["bat"] ]
> 
> 来源：力扣（LeetCode） 
> 链接：https://leetcode-cn.com/problems/group-anagram

思路分析：拿到这道题首先想到的就是哈希表，相同的字符串（排序后），放在相同的 List 集合中。

剩余问题就是：如何判断字符串是否一致。

让字符串排序，原本无序的字符串变为有序，相同字符的字符串排序后出现的字符串序列自然相等。

则排序后的字符串一致我们就将其放在同个  List 集合中

不一致放在单独的 List 集合中。

```java
public List<List<String>> groupAnagrams(String[] strs) {
        if (strs == null) {
            return null;
        }
        List<List<String>> lists = new ArrayList<>();
        HashMap<String, List<String>> hash = new HashMap<>();
        // 以排序后的字符串为键，原字符串为值
        for (int i = 0; i < strs.length; i++) {
            char[] c = strs[i].toCharArray();
            Arrays.sort(c);
            String key = new String(c);
            if (hash.containsKey(key)) {
                hash.get(key).add(strs[i]);
            } else {
                List<String> list = new ArrayList<>();
                list.add(strs[i]);
                hash.put(key, list);
            }
        }
        for (String i : hash.keySet()) {
            lists.add(hash.get(i));
        }
        return lists;
    }
```

