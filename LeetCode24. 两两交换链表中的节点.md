#  两两交换链表中的节点 [题目链接](https://leetcode-cn.com/problems/swap-nodes-in-pairs/)

> 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
> 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。

> 示例: 
> 给定 1->2->3->4, 你应该返回 2->1->4->3.。

解题思路：迭代法，建立哨兵结点，每次遍历两个节点，交换位置，直到遍历至空节点。
```java
	/**
     * 建立一个哨兵节点 preHead
     * p1 指向当前节点,p2 指向下一节点，交换二者位置
     * 每次遍历链表两个节点，若遍历空节点则结束遍历
     */
class Solution {
        public ListNode swapPairs(ListNode head) {
            ListNode preHead = new ListNode(0);
            ListNode node = preHead;
            while (head != null && head.next != null) {
                ListNode next = head.next.next; // 存储下一要遍历节点
                // head.next=head; // 交换节点
                node.next=head.next;
                node.next.next=head;
                node=node.next.next;
                head=next; // 向后遍历两个节点
            }
            node.next=head;
            return preHead.next;
        }
    }
```

