# LeetCode82. 删除排序链表中的重复元素 II

#### [82. 删除排序链表中的重复元素 II](https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list-ii/)

解题思路：哨兵节点

lastValue 记录上一节点值，pre 代表上一节点，curr 代表当前节点， next代表下一节点

依次遍历链表，判断当前节点是否应该被删除：（pre.next= curr.next）

1、当前节点值与上一节点值相同，curr.val=lastValue

2、当前节点存在下一节点，且下一节点值与当前节点值相等

```java
public ListNode deleteDuplicates(ListNode head) {
        ListNode node = new ListNode(-1);
        node.next = head;
        ListNode pre = node, curr = node.next;
        int lastValue = node.val;
        while (curr != null) {
            // 当前节点应当被删除
            if (lastValue == curr.val || (curr.next != null && curr.next.val == curr.val)) {
                lastValue = curr.val;
                pre.next = curr.next;
            } else {
                pre = pre.next;
            }
            curr = curr.next;
        }
        return node.next;
    }
```

