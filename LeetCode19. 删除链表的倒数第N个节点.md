# 删除链表的倒数第N个节点 [题目链接](https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list/submissions/)

> 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
 
> 示例： 
> 给定一个链表: 1->2->3->4->5, 和 n = 2.

> 当删除了倒数第二个节点后，链表变为 1->2->3-5.

双指针法解题思路：
 - node 指针指向头结点，fast 指针指向 node 后的第 n+1 个结点 
 - 双指针同时向后遍历，当 fast 遍历至链尾时， node  指针指向下一结点的下一结点即可 
 - 特殊情况，要删除的结点为头结点，即 fast 在定义时就遍历至链尾，则返回头结点的下一结点即可

```java
public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode node=head;
        ListNode fast=head.next;
        for(int i=0;i<n;i++){
            if(fast==null){ // 若已遍历至链尾说明要删除的就是头结点
                return head.next;
            }
            fast= fast.next;
        }

        while (fast!=null)
        {
            fast=fast.next;
            node=node.next;
        }
        node.next=node.next.next;
        return  head;
    }
```

