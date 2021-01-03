# LeetCode86. 分隔链表

#### [86. 分隔链表](https://leetcode-cn.com/problems/partition-list/)

解题思路：

1、lessXHead、otherHead 代表两链表头结点（**使用哨兵节点**） ，node1、node2 分别代表两链表当前节点

3、依序遍历 head 若当前节点值小于x则 node1 下一节点指向当前节点 node1=node1.next

4、若当前节点值大于等于 x 则 node2 下一节点指向当前节点 node2=node2.next

5、遍历结束，拼接两节点 node1.next=otherHead.next 返回 lessXHead.next

```java
public ListNode partition(ListNode head, int x) {
    	// 使用哨兵节点，减少空指针处理
        ListNode lessXHead = new ListNode(0);
        ListNode otherHead = new ListNode(0);
        ListNode node1 = lessXHead;
        ListNode node2 = otherHead;
        while (head != null) {
            ListNode node = head;
            head = head.next;
            // 每次将两条链表的下一节点置为 null，防止链表死循环
            node.next = null;
            if (node.val < x) {
                node1.next = node;
                node1 = node1.next;
            } else {
                node2.next = node;
                node2 = node2.next;
            } 
        }
    	// 合并链表
        node1.next = otherHead.next;
        return lessXHead.next;
    }
```

