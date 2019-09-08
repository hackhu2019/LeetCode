# 二叉树的最大深度
>给定一个二叉树，找出其最大深度。
>二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。

>说明: 叶子节点是指没有子节点的节点。

>示例：
给定二叉树 [3,9,20,null,null,15,7]，
返回最大深度：3

思路分析：深度优先遍历，若还有子节点则继续往下遍历层数+1， 无子结点则返回当前层数,max 记录最大层数
```java
class Solution {
    private int max = 0;
    public int maxDepth(TreeNode root) {
        maxDepth(root, 0);
        return max;
    }

    private void maxDepth(TreeNode root, int depth) {
        if (root == null) {
            max = max < depth ? depth : max; // 记录最大层数
            return;
        }
        maxDepth(root.left, depth+1); // 遍历下一层，层数+1
        maxDepth(root.right, depth+1);
    }
}
```

