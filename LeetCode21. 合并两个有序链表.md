# 合并两个有序链表 [题目链接](https://leetcode-cn.com/problems/merge-two-sorted-lists/submissions/)

> 将两个有序链表合并为一个新的有序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 
> 
> 示例： 输入：1->2->4, 1->3->4 输出：1->1->2->3->4->4


初始写法，代码重用率低，不够简洁。

另两个链表当前节点的较小者指向下一次比较的较小者，每次比较后较小者所在链表向后遍历
```java
class Solution {
        public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
            if (l2 == null) {
                return l1;
            }
            if (l1 == null) {
                return l2;
            }
            ListNode head;
            if (l1.val <= l2.val) { // 头结点为二者中较小者
                head = l1;
                l1 = l1.next;
            }
            else {
                head = l2;
                l2 = l2.next;
            }
            ListNode p = head;
            while (l1 != null && l2 != null) { // 二者有一条遍历至尾结点则结束遍历
                ListNode node=null;
                if (l1.val <= l2.val) {
                     node= l1;
                    l1 = l1.next;
                    
                } else {
                    node=l2;
                    l2 = l2.next;
                }
                p.next = node;
                p = p.next;
            }
            if (l1 == null) {
                p.next=l2;
            }
            if (l2 == null) {
                p.next = l1;
            }
            return head;
        }
    }
```
基于「迭代法」的思路进行优化

以浪费一个头结点的方式，简化代码

```java
		/**
         * 迭代法，以浪费一个节点的方式来返回合并后的链表
         * p 的下一节点为两链表的较小者
         * 若遍历完某一链表则直接指向另一链表的剩余节点
         * @param l1
         * @param l2
         * @return
         */
        public ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
            ListNode head = new ListNode(0); // 哨兵节点
            ListNode prev = head;
            while (l1 != null && l2 != null) {
                if (l1.val <= l2.val) {
                    prev.next=l1;
                    l1 = l1.next;
                }else{
                    prev.next = l2;
                    l2 = l2.next;
                }
                prev = prev.next;
            }
            prev.next = l1 == null ? l2 : l1; // 指向另一链表的剩余节点
            return head.next;
        }
```
同时你也可以采用「递归法」的思路来解题

```java
/**
     * 递归思路，问题分解
     * 两个链表当前节点的较小者指向下一次比较的较小者，每次比较后较小者所在链表向后遍历
     * 特殊情况，两条链表有一条遍历至尾结点，则直接返回另一链表即可
     */
    class Solution {
        public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
            if (l1 == null) {
                return l2;
            } else if (l2 == null) {
                return l1;
            } else if (l1.val <= l2.val) { // 指向下一次比较的较小者
                l1.next = mergeTwoLists(l1.next, l2);
                return l1;
            }else{
                l2.next = mergeTwoLists(l1, l2.next);
                return l2;
            }
        }
```
优化的解答思路参考了[官方题解](https://leetcode-cn.com/problems/merge-two-sorted-lists/solution/he-bing-liang-ge-you-xu-lian-biao-by-leetcode/)，太久不做题，效率低了很多，没有使用的思路怎么也想不到。多看看别人的解答有利于发散思维，提高自己的编程能力。
