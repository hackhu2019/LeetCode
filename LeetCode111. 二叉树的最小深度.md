# 二叉树的最小深度
>给定一个二叉树，找出其最小深度。
>最小深度是从根节点到最近叶子节点的最短路径上的节点数量。

>说明: 叶子节点是指没有子节点的节点。

注意条件：**叶子节点是指没有子节点的节点**

思路分析：
1. 基于广度优先遍历，每遍历一层我们判断当前结点是否存在左右子结点。
2. 若不存在，则当前深度为最小深度。
3. 若存在则继续遍历下一层。
```java
class Solution {
    public int minDepth(TreeNode root) {
        if (root == null) { // 根节点，特殊处理
            return 0;
        }
        int depth = 1; // 初始深度为 1
        LinkedList<TreeNode> queue = new LinkedList<>(); // 存储每一层元素
        queue.add(root);
        while (queue.size() != 0) {
            int size = queue.size();
            for (int i = 0; i < size; i++) { // 遍历每一层
                TreeNode temp = queue.poll(); // 取出队顶元素
                if (temp.left == null && temp.right == null) {
                    return depth; // 无结点，则当前节点为叶节点，返回当前层数
                }
                if (temp.left != null) { // 队列加入左右非空子结点
                    queue.add(temp.left);
                }
                if (temp.right != null) {
                    queue.add(temp.right);
                }
            }
            depth++;
        }
        return depth;
    }
}
```

