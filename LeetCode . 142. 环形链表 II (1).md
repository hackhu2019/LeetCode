# 142. 环形链表 II [题目链接](https://leetcode-cn.com/problems/linked-list-cycle-ii/)

> 给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
>为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。

>说明：不允许修改给定的链表。

>示例 1：
>输入：head = [3,2,0,-4], pos = 1
>输出：tail connects to node index 1
>解释：链表中有一个环，其尾部连接到第二个节点。

***解题思路：*** 
 
 画图找规律
 
 出现环路的首节点为 A ，而快慢指针第一次相遇的点为 A + B 即环长
 
 B 为环内重复段长度
 
 再让一点从首结点出发，与慢指针相交结点必为 A 点
```c

 struct ListNode* hasCycle(struct ListNode *head) { // 判断是否有环路，若有则返回慢指针，无则返回 NULL
	 struct ListNode *q = head;
	 delete(q);
	 struct ListNode  *fast, *slow;
	 slow = fast = head;
	 while (fast&&fast->next&&fast->next->next)
	 {
		 fast = fast->next->next;
		 slow = slow->next;
		 if (slow == fast)
			 return slow;
	 }
	 return NULL;
 }

 struct ListNode *detectCycle(struct ListNode *head) {
	 struct ListNode* result = hasCycle(head),
		 *p = head;

	 if (!result)
		 return NULL;
	 else
	 {
		 while (p != result)
		 {
			 p = p->next;
			 result = result->next;
		 }
	 }
	 return p;
 }
```

