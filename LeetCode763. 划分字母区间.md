# 划分字母区间
>字符串 S 由小写字母组成。我们要把这个字符串划分为尽可能多的片段，同一个字母只会出现在其中的一个片段。返回一个表示每个字符串片段的长度的列表。

>示例 1:
输入: S = "ababcbacadefegdehijhklij"
输出: [9,7,8]
解释:
划分结果为 "ababcbaca", "defegde", "hijhklij"。
每个字母最多出现在一个片段中。
像 "ababcbacadefegde", "hijhklij" 的划分是错误的，因为划分的片段数较少。

>来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/partition-labels

思路分析：

1、lastIndex[] 记录 S 中每个字母出现的最大索引（最后出现位置）

2、 count 记录每一段分词长度

3、max 记录每一段分词字母出现的最大索引，当分词内所有字母最后出现索引 <= max 时，可以分词。
```java
class Solution {
    public List<Integer> partitionLabels(String S) {
        int[] lastIndex = new int[26]; // 存储 26 字母出现最大索引
        for (char c : S.toCharArray()) { // 记录所有字母最后出现位置
            lastIndex[c - 'a'] = S.lastIndexOf(c);
        }
        List<Integer> result = new ArrayList<>(); // 存储分词长度
        int index = 0;
        while (index < S.length()) {
            int count = 0, max = lastIndex[S.charAt(index) - 'a']; // max 存储当前区间最大索引值
            for (int i = index; i <= max; i++) { // 当前区间所有字符都小于 max，达成分词条件
                count++; // 记录分词长度
                int curLastIndex = lastIndex[S.charAt(i) - 'a'];
                max = curLastIndex > max ? curLastIndex : max; // 更新 max 值
            }
            index = max + 1; // 更新索引
            result.add(count); // 加入结果集
        }
        return result;
    }
}
```

