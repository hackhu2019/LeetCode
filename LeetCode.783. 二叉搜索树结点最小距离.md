# 二叉搜索树结点最小距离 [题目链接](https://leetcode-cn.com/problems/minimum-absolute-difference-in-bst/)
>给定一个二叉搜索树的根结点 root, 返回树中任意两节点的差的最小值。

思路：通过二叉树中序遍历的思路对树进行遍历。这里的任意两节点指的是相邻结点的差值
```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    private int min = Integer.MAX_VALUE; // 定义要返回的最小值
    private TreeNode pre; // 前驱指针

    public int getMinimumDifference(TreeNode root) {
        inPrint(root); // 中序遍历二叉树
        return min;
    }

    private void inPrint(TreeNode root) {
        if (root == null)
            return;
        inPrint(root.left); // 先向左遍历到最高层

        if (pre != null)
            min = Math.min(min, Math.abs(root.val - pre.val)); // 比较最小值和相邻节点的差值哪个更小
        pre = root; // 前驱指针指向子一节点
        inPrint(root.right); // 再遍历右节点
    }
}
```




