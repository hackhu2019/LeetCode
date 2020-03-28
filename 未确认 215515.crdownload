# 820. 单词的压缩编码 [题目链接](https://leetcode-cn.com/problems/short-encoding-of-words/)
解法一：

1、遍历字符串，建立 set，len 记录压缩后的列表长度初始化为 0

2、将 words 按照字符串长度降序排序，方便过滤后缀子串

3、若所遍历字符串不在 set 中，且不是 set 中任一字符后缀）则加入 set

4、 否，则 len+= word.length+1 ，继续遍历下一字符

```java
public int minimumLengthEncoding(String[] words) {
        Arrays.sort(words, (o1, o2) -> o2.length() - o1.length());
        int len = 0;
        Set<String> set = new HashSet<>();
        for (String word : words) {
            if (isSubStr(word, set)) {
                continue;
            } else {
                len += word.length() + 1;
                set.add(word);
            }
        }
        return len;
    }

    public boolean isSubStr(String word, Set<String> set) {
        for (String str : set) {
            if (str.endsWith(word)) {
                return true;
            }
        }
        return false;
    }
```
解法二：以 words 建立字典树，字典树的分支数+叶节点所在字符串长度即为压缩后字符长度

思路参考：https://leetcode-cn.com/problems/short-encoding-of-words/solution/dan-ci-de-ya-suo-bian-ma-by-leetcode-solution/

```java
class Solution {
    class Solution {
        public int minimumLengthEncoding(String[] words) {
            TrieNode trie = new TrieNode();
            Map<TrieNode, Integer> trieNodes = new HashMap();

            for (int i = 0; i < words.length; ++i) {
                String word = words[i];
                TrieNode cur = trie;
                for (int j = word.length() - 1; j >= 0; --j)
                    cur = cur.get(word.charAt(j));
                trieNodes.put(cur, i);
            }

            int len = 0;
            for (TrieNode node: trieNodes.keySet()) {
                if (node.count == 0)
                    len += words[trieNodes.get(node)].length() + 1;
            }
            return len;
        }
    }
}
// 构造字典树
class TrieNode {
    TrieNode[] children;
    int count;
    TrieNode() {
        children = new TrieNode[26];
        count = 0;
    }
    public TrieNode get(char c) {
        if (children[c - 'a'] == null) {
            children[c - 'a'] = new TrieNode();
            count++;
        }
        return children[c - 'a'];
    }
}。
```

