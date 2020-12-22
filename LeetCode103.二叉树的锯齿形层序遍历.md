# LeetCode103.二叉树的锯齿形层序遍历

#### [103. 二叉树的锯齿形层序遍历](https://leetcode-cn.com/problems/binary-tree-zigzag-level-order-traversal/)

解题思路：广度优先遍历

每次遍历当层节点，若是奇数层则从左往右加入List，若是偶数层则从右往左加入 List ( 双端队列实现 )

```java
public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> resultList = new ArrayList<>();
        if (root == null) {
            return resultList;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
    	// 区分当前是否奇数层，选择加入 List 顺序
        boolean isOddFloor = true;
    	// 广度优先遍历
        while (!queue.isEmpty()) {
            int size = queue.size();
            Deque<Integer> nums = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                TreeNode curNode = queue.poll();
                if (isOddFloor) {
                    nums.offerLast(curNode.val);
                } else {
                    nums.offerFirst(curNode.val);
                }
                if (curNode.left != null) {
                    queue.add(curNode.left);
                }
                if (curNode.right != null) {
                    queue.add(curNode.right);
                }
            }
            isOddFloor = !isOddFloor;
            resultList.add(new LinkedList<>(nums));
        }
        return resultList;
    }
```

