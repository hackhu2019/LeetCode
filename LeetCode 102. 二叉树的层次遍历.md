# 二叉树的层次遍历 [题目链接](https://leetcode-cn.com/problems/binary-tree-level-order-traversal/)
>给定一个二叉树，返回其按层次遍历的节点值。 （即逐层地，从左到右访问所有节点）

思路：按照 BFS（Breath-First-Search）广度优先搜素的方法，通过维护一个队列，依序将每次加入队列，再依序加入 list 即可
```java
public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>(); // 定义一个为整型 List 的 List 集合，类似于交错数组
        if(root==null) return list; // 若 root 为空则无需遍历直接返还空集合
        int count =0 ; // 初始化 count ,记录每一层的节点数
        TreeNode node; // node 为遍历的任意结点
        Queue<TreeNode> queue = new LinkedList<TreeNode>(); // 维护 1 个队列将每一层的结点依序放入队列
        queue.add(root); // 先把第一层结点加入队列
        while(!queue.isEmpty()) // 队列不为空说明树还没有遍历结束
        {
            List<Integer> res = new ArrayList<>(); // 存储每一层结点 
            count=queue.size(); // 记录当前层的结点数
            while(count>0)
            {
                node=queue.poll();
                if(node.left!=null) queue.add(node.left); // 将子层结点加入队列
                if(node.right!=null) queue.add(node.right);
                res.add(node.val);
                count--;
            }
            list.add(res);
        }
        return list;
    }
```

