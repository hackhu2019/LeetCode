# 单词搜索 II
> 给定一个二维网格 board 和一个字典中的单词列表 words，找出所有同时在二维网格和字典中出现的单词。
单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母在一个单词中不允许被重复使用。

>示例:
输入: 
words = ["oath","pea","eat","rain"] and board =
[
  ['o','a','a','n'],
  ['e','t','a','e'],
  ['i','h','k','r'],
  ['i','f','l','v']
]
输出: ["eat","oath"]

>来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/word-search-ii

思路分析：基于 208 题和 79 题，DFS + Trie
1. 根据字符串数组生成对应字典树
2. 在 board 按照路径生成字符，判断是否在字典树有对应前缀，前缀不匹配则剪枝回到上一匹配项
3. 判断是否完全匹配，找到完全匹配，将已匹配字符串加入 set （去重）
4. 不完全匹配，则继续在上下左右移动，寻找下一匹配字符

```java
class Solution {
    private HashSet<String> set = new HashSet<>(); // 结果集，Set 去重
    private boolean[][] memo;
    private Trie trie;
    // 基于 208 题和 79 题，DFS + Trie
    public List<String> findWords(char[][] board, String[] words) {
        memo = new boolean[board.length][];
        trie = new Trie();
        for (int i = 0; i < board.length; i++) { // 初始化备忘录，记录当前坐标是否已被访问
            memo[i] = new boolean[board[i].length];
        }
        for (int i = 0; i < words.length; i++) {
            trie.insert(words[i]); // 生成字典树
        }
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                DFS(board, row, col, "", trie); // 回溯查找
            }
        }
        return new ArrayList<>(set);
    }

    private void DFS(char[][] board, int row, int col, String str, Trie trie) {
        if (row < 0 || col < 0 ||
                row >= board.length ||
                col >= board[row].length || memo[row][col]) {
            return;
        }
        str += board[row][col];
        if (!trie.startsWith(str)) { // 匹配到前缀字符串
            return;
        }
        memo[row][col] = true;
        if (trie.search(str)) { // 完全匹配字符串
            set.add(str); 
        }
        // 上下左右查找，是否有匹配路径
        DFS(board, row - 1, col, str, trie);
        DFS(board, row + 1, col, str, trie);
        DFS(board, row, col - 1, str, trie);
        DFS(board, row, col + 1, str, trie);
        memo[row][col] = false; // 还原
    }
}
class TreeNode {
    private final int SIZE = 26;
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
            char c = word.charAt(i);
            if (!node.containsKey(c)) {
                node.put(c, new TreeNode());
            }
            node = node.get(c); // 进入下一节点
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

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("apple");
        System.out.println(trie.search("apple"));
    }
}
```

