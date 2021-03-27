# LeetCode61. 旋转链表

#### [61. 旋转链表](https://leetcode-cn.com/problems/rotate-list/)

思路分析：

1、首次遍历，计算出链表长度 len，k=k%len,index=len-k，tail 指向链表末尾节点
2、 将 [1,index] 节点移动至链表末尾，mid 代表 index 节点
3、newHead=mid.next,tail.next=head,mid.next=nul

```java
public ListNode rotateRight(ListNode head, int k) {
        int len = 0;
        ListNode tail = head, node = head;
        while (node != null) {
            len++;
            tail = node;
            node = node.next;
        }
        k = len == 0 ? 0 : k % len;
        if (k == 0) {
            return head;
        }
        int index = len - k;
        node = head;
        for (int i = 1; i < index; i++) {
            node = node.next;
        }
        ListNode newNode = node.next;
        tail.next = head;
        node.next = null;
        return newNode;
    }
```

代码优化基于 [官方题解：闭合为环](https://leetcode-cn.com/problems/rotate-list/solution/xuan-zhuan-lian-biao-by-leetcode-solutio-woq1/)

思路：首次遍历后 tail.next 指向 head,当 i=index 时，newNode = node.next，node.next=null

```java
public ListNode rotateRight(ListNode head, int k) {
        int len = 0;
        ListNode tail = head, node = head;
        while (node != null) {
            len++;
            tail = node;
            node = node.next;
        }
        k = len == 0 ? 0 : k % len;
        if (k == 0) {
            return head;
        }
        int index = len - k;
        node = head;
        tail.next=head;
        for (int i = 1; i < index; i++) {
            node = node.next;
        }
        ListNode newNode = node.next;
        node.next = null;
        return newNode;
    }
```

