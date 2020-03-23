# 876. 链表的中间结点 [题目链接](https://leetcode-cn.com/problems/middle-of-the-linked-list/)
解题思路：双指针法，快指针一次走两步，慢指针一次走一步，当快指针走完链表时，慢指针指向的就是中间节点。

```java
public ListNode middleNode(ListNode head) {
        ListNode fast = head, slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }
```

