# 两数相加 [题目链接](https://leetcode-cn.com/problems/add-two-numbers/)
>给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
你可以假设除了数字 0 之外，这两个数都不会以 0 开头。

>示例：
输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
输出：7 -> 0 -> 8
原因：342 + 465 = 807

思路分析：

这道题不能暴力的将两链表表示的数相加再放入链表，因为测试数据中有表示的数远大于整形范围的，而两个链表都是逆序表示数值的，我们可以模拟加法运算的过程来得到我们的结果。

用 1 个变量 carry 来存储进位，而节点所存储的只能是一位数，所以我们需要求余 10，进位数则为：节点相加值 / 10 。

因为是链表还要考虑节点为空的情况。若两链表的下一结点都为空，且有进位，则需要在末尾插入 1 个新的节点表示进位。

若只有 1 条链表，则判断剩余节点是否需要进位。
```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // 选定链表 1 为返回的链表，同时遍历两条链表，用一个变量表示进位，每次同位数相加，若有一条遍历结束则输出链表
        int carry = 0; // 表示进位
        int doAnd = 0;
        if (l1 == null)
            return l2;
        if (l2 == null)
            return l1;
        ListNode node1 = l1;
        ListNode node2 = l2;
        while (true) {
            doAnd = (node1.val + node2.val + carry); // 和
            node1.val = doAnd % 10; // 求和的尾数
            carry = doAnd / 10; // 进位
            if (node1.next == null && node2.next == null && carry == 1) {
                ListNode node = new ListNode(carry);
                node.next = null;
                node1.next = node;
                break;
            }
            if (node1.next == null) {
                if (node2.next != null) {
                    // 判断后序位数是否产生进位
                    node1.next = node2.next;
                    node1=node1.next;
                    while (carry == 1) {
                        doAnd = node1.val + carry;
                        node1.val = doAnd % 10;
                        carry = doAnd / 10;
                        if (node1.next== null && carry == 1) {
                            ListNode node = new ListNode(carry);
                            node.next = null;
                            node1.next = node;
                            break;
                        }
                        node1=node1.next;
                    }
                } 
                break;
            }
            if (node2.next == null) {
                if (node1.next != null) {
                    node1=node1 .next;
                    while (carry == 1) {
                        doAnd = node1.val + carry;
                        node1.val = doAnd % 10;
                        carry = doAnd / 10;
                        if (node1.next== null && carry == 1) {
                            ListNode node = new ListNode(carry);
                            node.next = null;
                            node1.next = node;
                            break;
                        }
                        node1=node1.next;
                    }
                } 
                break;
            }
            node1 = node1.next;
            node2 = node2.next;
        }
        return l1;
    }
}
```

