# K 个一组翻转链表 [题目链接](https://leetcode-cn.com/problems/reverse-nodes-in-k-group/)

> 给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
> k 是一个正整数，它的值小于或等于链表的长度。
> 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。

> 示例 :
> 给定这个链表：1->2->3->4->5
> 当 k = 2 时，应当返回: 2->1->4->3->5
> 当 k = 3 时，应当返回: 3->2->1->4->5

思路：

 1. 在建立哨兵节点（牺牲一个头结点），迭代法的思路基础上修改代码
 2. 建立一个链式栈，用 prev 指针做串联
 3. 每次遍历 K 个节点，若遍历到空节点则结束遍历，prev 指向栈尾节点
 4. 若未遍历空节点，则 prev 指针按照出栈顺序依次串联节点

```java
public ListNode reverseKGroup(ListNode head, int k) {
            ListNode preHead = new ListNode(0);
            ListNode prev=preHead;
            LinkedList<ListNode> stack = new LinkedList<>();
            while (head != null) {
                while (head!=null&&stack.size() < k) {
                    stack.push(head); // 入栈
                    head = head.next;
                }
                if (stack.size() == k) {
                    while (head != null && stack.size() > 0) {
                        prev.next = stack.poll();
                        prev = prev.next;
                    }
                } else {
                    prev.next=stack.pollLast();
                    return preHead.next;
                }
            }
            prev.next = head;
            return preHead.next;
        }
```

