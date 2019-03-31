# 合并二叉树 [题目链接](https://leetcode-cn.com/problems/merge-two-binary-trees/)
>给定两个二叉树，想象当你将它们中的一个覆盖到另一个上时，两个二叉树的一些节点便会重叠。
>你需要将他们合并为一个新的二叉树。合并的规则是如果两个节点重叠，那么将他们的值相加作为节点合并后的新值，否则不为 NULL 的节点将直接作为新二叉树的节点。

解法一

根据中序遍历的思路对两棵树同时遍历，当某一树遍历结束则返回。

以 t1 树作为最终要返回的值，判断首次返回时 t1 的子节点是否为空，是则让 t1 的子节点指针指向 t2 的子节点。
```java
class Solution {
     public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
         if(t1==null) // 对特殊情况进行处理
             return t2;
        inTwoTree(t1, t2);
        return t1;
    }

    private void inTwoTree(TreeNode t1,TreeNode t2) // 递归遍历两树，某树遍历到空则返回
    {
        if(t1==null||t2==null)
            return ;
        inTwoTree(t1.left, t2.left);
        if(t1.left==null)
            t1.left=t2.left;
        t1.val+=t2.val;
        inTwoTree(t1.right, t2.right);
        if(t1.right==null)
            t1.right=t2.right;
    }
}
```
解法二

来自于官方提供的 [递归法](https://leetcode.com/problems/merge-two-binary-trees/solution/)
采用的是二叉树前序遍历的思路，若两树有一棵遍历为空，则将剩余部分（非重叠部分）返回给 t1。
```java
public class Solution {
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        // inTwoTree(t1, t2);
        // 前序遍历，若有 1 棵树遍历结束则返回另一棵树剩余部分
        if(t1==null)
            return t2;
        if(t2==null)
            return t1;
        t1.val+=t2.val;
        t1.right=mergeTrees(t1.right, t2.right);
        t1.right=mergeTrees(t1.right, t2.right);
        return t1;
    }
}
```

