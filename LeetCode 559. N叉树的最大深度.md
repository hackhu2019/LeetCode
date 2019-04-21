# N叉树的最大深度 [题目链接](https://leetcode-cn.com/problems/maximum-depth-of-n-ary-tree/)
>给定一个 N 叉树，找到其最大深度。

>最大深度是指从根节点到最远叶子节点的最长路径上的节点总数。

根据 BFS 广度优先、DFS 深度优先得出两种不同的解法

深度优先解法思路：让每 1 条路径都遍例到最深，最后返回所有深度中的最大值。
```java
/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val,List<Node> _children) {
        val = _val;
        children = _children;
    }
};
*/
class Solution {
    private int count=0; // 记录层数
    public int maxDepth(Node root) {
        // 深度遍历 DFS 
        // 只要当前结点还有子结点则继续向下遍历，每遍历 1 个结点则深度 +1
        if(root == null) return 0;
        recurDFS(root, 0); // 从第 1 层开始
        return count;
    }
    private void recurDFS(Node node,int deep)
    {
        deep++; // 每遍历到新的 1 层 则深度 +1
        if(deep>count) // 若该路线深度大于 count 则替换 count
            count=deep;
            //System.out.print(count+",");
        if(node==null)
        {
            
            return ; // 该路线遍历结束
        }    
        for(int i=0;i<node.children.size();i++)
        {
            if(node.children==null)
                continue;
            recurDFS(node.children.get(i), deep);
        }
    }
}
```
BFS 广度优先解法思路：把所有层都遍例 1 遍，每遍历到新层则把深度 +1，遍历结束返回深度。
```java
class Solution {
    public int maxDepth(Node root) {
        // 维护 1 个队列，逐层遍历树
        if(root == null) return 0;
        int count =0; // 记录层数
        int size = 0 ; // 记录每一层节点数
        Node temp;
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty())
        {
            count++;
            size=queue.size();
            for(int i=0;i<size;i++)
                {
                    temp = queue.poll();
                    if(temp.children!=null)
                        queue.addAll(temp.children);
                }
        }
        return count;
    }
}
```

