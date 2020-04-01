# 124. 二叉树中的最大路径和 [题目链接](https://leetcode-cn.com/problems/binary-tree-maximum-path-sum/)
解题思路：当前节点最大路径值=Max(左子树最大路径值，右子树最大路径值)+当前节点值

若左右子树最大路径值为负数，则不加上子树路径值（子树路劲值设为 0）

以后序遍历顺序依次计算每一节点路径值，max 存储最大值即可

```java
 private int maxPath = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        postOrder(root);
        return maxPath;
    }

    private int postOrder(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = Math.max(0, postOrder(root.left));
        int right = Math.max(0, postOrder(root.right));
        maxPath = Math.max(maxPath, right + left + root.val);
        return Math.max(right, left) + root.val;
    }
```

