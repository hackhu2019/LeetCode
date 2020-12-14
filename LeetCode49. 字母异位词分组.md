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
        List<List<String>> resultList = new ArrayList<>();
        HashMap<String, List<String>> strMap = new HashMap<>();
    	// 相同的字符串（排序后），放在相同的 List
        for (String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String key = new String(chars);
            List<String> result = strMap.getOrDefault(key, new ArrayList<>());
            result.add(str);
            strMap.put(key, result);
        }
    	// entry 代替 keySet 不用查询两次 map
        strMap.entrySet().forEach(entry -> resultList.add(entry.getValue()));
        return resultList;
    }
```
优化 key 生成 以字符出现次数为 Key （ 字母 + 出现次数 ）

```java
public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> resultList = new ArrayList<>();
        HashMap<String, List<String>> strMap = new HashMap<>();
        for (String str : strs) {
            int[] alphabet = new int[26];
            for (char c : str.toCharArray()) {
                alphabet[c - 'a']++;
            }
            StringBuilder key = new StringBuilder();
            for (int i = 0; i < alphabet.length; i++) {
                key.append('a' + i);
                key.append(alphabet[i]);
            }
            List<String> result = strMap.getOrDefault(key.toString(), new ArrayList<>());
            result.add(str);
            strMap.put(key.toString(), result);
        }
        strMap.entrySet().forEach(entry -> resultList.add(entry.getValue()));
        return resultList;
    }
```

