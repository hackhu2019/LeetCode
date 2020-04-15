# 445. 两数相加 II [题目链接](https://leetcode-cn.com/problems/add-two-numbers-ii/)
解法一：两个栈存储链表元素，依次遍历 l1,l2,依次遍历 stackL1、stackL2，头结点插入生成新链表，carry 标志进位

```java
public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Stack<Integer> stackL1 = new Stack<>(), stackL2 = new Stack<>();
        while (l1 != null) {
            stackL1.push(l1.val);
            l1 = l1.next;
        }
        while (l2 != null) {
            stackL2.push(l2.val);
            l2 = l2.next;
        }
        ListNode head = null;
        int carry = 0;
        while (stackL1.size() != 0 || stackL2.size() != 0 || carry > 0) {
            int l1Value = stackL1.empty() ? 0 : stackL1.pop();
            int l2Value = stackL2.empty() ? 0 : stackL2.pop();
            int sum = l1Value + l2Value + carry;
            carry = sum > 9 ? 1 : 0;
            ListNode node = new ListNode(sum % 10);
            node.next = head;
            head = node;
        }
        return head;
    }
```
解法二：翻转链表，头节点插入相加后的链表节点

```java
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        l1=reverseListNode(l1);
        l2 = reverseListNode(l2);
        int carry = 0;
        ListNode head = null;
        while (l1 != null || l2 != null || carry > 0) {
            int l1Value = l1 == null ? 0 : l1.val,
                    l2Value = l2 == null ? 0 : l2.val,
                    sum = l2Value + l1Value + carry;
            carry = sum / 10;
            ListNode node = new ListNode(sum % 10);
            node.next = head;
            head = node;
            l1 = l1 == null ? null : l1.next;
            l2 = l2 == null ? null : l2.next;
        }
        return head;
    }

    private ListNode reverseListNode(ListNode node) {
        if (node == null || node.next == null) {
            return node;
        }
        ListNode cur = null, next = node;
        while (next != null) {
            ListNode temp = next.next;
            next.next = cur;
            cur = next;
            next = temp;
        }
        return cur;
    }
}
```

