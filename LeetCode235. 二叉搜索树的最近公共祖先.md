# 二叉搜索树的最近公共祖先
>给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。

>示例 1:
>输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
>输出: 6 
>解释: 节点 2 和节点 8 的最近公共祖先是 6。
>来源：力扣（LeetCode）
>链接：https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-search-tree

首先要理解二叉搜索树特点：大于根节点的都在右子树，小于根节点的都在左子树

思路分析：
1. 若 p/q 位于 root 两侧，则根节点为公共祖先
2. 若都位于左侧，则公共结点出现在左子树
3. 若都位于右侧，则公共结点出现在右子树
```java
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    	// 根节点为公共祖先
        if (root == null ||root.val==p.val||root.val==q.val||
                (root.val > p.val && root.val < q.val) ||
                (root.val < p.val && root.val > q.val)) {
            return root;
        }
        // 都位于左侧，则公共结点出现在左子树
        if (p.val < root.val) {
            return lowestCommonAncestor(root.left, p, q);
        }
        // 都位于右侧，则公共结点出现在右子树
        return lowestCommonAncestor(root.right, p, q);
    }
}
```

