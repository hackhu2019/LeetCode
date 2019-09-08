# 实现 Trie (前缀树)
> 实现一个 Trie (前缀树)，包含 insert, search, 和 startsWith 这三个操作。

> 示例:
> Trie trie = new Trie();
> trie.insert("apple");
> trie.search("apple");   // 返回 true
> trie.search("app");     // 返回 false
> trie.startsWith("app"); // 返回 true
> trie.insert("app");   
> trie.search("app");     // 返回 true

>说明:
你可以假设所有的输入都是由小写字母 a-z 构成的。
保证所有输入均为非空字符串。

>来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/implement-trie-prefix-tree

方法 1，存储子 Trie，不同的的字符生成不同分支（无辅助类，递归查询），用数组索引 0~25 代表 'a' ~ 'z' 26 个字符

```java
public class Trie {
    private static final int ALPHABET_SIZE = 26;
    Trie[] children;
    boolean isEndOfWord; // 定义是否为结束字符
    /** Initialize your data structure here. */
    public Trie() {
        children = new Trie[26];
        isEndOfWord = false;
    }

    /** Inserts a word into the trie. */
    public void insert(String word) { // 根据字符串，递归生成子树
        if (word == null||word.length()<1) { // 避免空指针异常
            return;
        }
        int index = word.charAt(0) - 'a';
        if (children[index] == null) {
            children[index] = new Trie(); // 若子树不存在则创建
        }
        if (word.length() == 1) {
            children[index].isEndOfWord = true; // 将当前结点标记为结束
        } else {
            children[index].insert(word.substring(1, word.length())); // 递归插入，子树插入字符串子串
        }
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) { // // 根据字符串，递归查找
        if (word == null||word.length()<1) { //  // 避免空指针异常
            return false;
        }
        int index = word.charAt(0) - 'a'; // 记录当前字符
        if (children[index] == null) { // 未生成对应字符子树，则无对应字符，返回 false
            return false;
        }
        if (word.length() == 1&&children[index].isEndOfWord) { // 在递归查找到对应前缀时，还要判断是否为结束点
            return true;
        } else {
            return children[index].search(word.substring(1, word.length())); // 递归在子树中查找
        }
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        if (prefix == null||prefix.length()<1) {
            return false;
        }
        int index = prefix.charAt(0) - 'a';
        if (children[index] == null) {
            return false;
        }
        if (prefix.length() == 1) {
            return true;
        } else {
            return children[index].startsWith(prefix.substring(1, prefix.length()));
        }
    }

}
```
方法 2，添加 TreeNode 辅助类，核心思想不变
```java
class TreeNode {
    private final int SIZE = 26; // 26 个字符
    private TreeNode children[];
    private boolean isEnd = false;

    public TreeNode() {
        children = new TreeNode[SIZE];
    }

    // 判断是否存在对应字符
    public boolean containsKey(char key) {
        return children[key - 'a'] != null;
    }

    // 查询子树
    public TreeNode get(char key) {
        return children[key - 'a'];
    }

    // 插入子树
    public void put(char key, TreeNode sub) {
        children[key - 'a'] = sub;
    }

    // 设为结束点
    public void setEnd() {
        isEnd = true;
    }
    // 判断是否为结束节点
    public boolean isEnd() {
        return isEnd;
    }
}

class Trie {
    TreeNode root;
    /** Initialize your data structure here. */
    public Trie() {
        root = new TreeNode();
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        TreeNode node = root;
        for (int i = 0; i < word.length(); i++) {
            if (!node.containsKey(word.charAt(i))) {
                node.put(word.charAt(i), new TreeNode());
            }
            node = node.get(word.charAt(i)); // 进入下一节点
        }
        node.setEnd(); // 设为结束点
    }
    // 抽象出查找前缀的公有方法
    private TreeNode searchPrefix(String word) {
        TreeNode node = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (!node.containsKey(c)) {
                return null;
            }
            node = node.get(c);
        }
        return node;
    }
    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        TreeNode node = searchPrefix(word);
        return node!=null&&node.isEnd(); // 判断是否为结束点
    }
    
    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        return searchPrefix(prefix)!=null;
    }
}
```

