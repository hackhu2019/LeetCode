# 环形链表  [题目链接](https://leetcode-cn.com/problems/linked-list-cycle/)

> 给定一个链表，判断链表中是否有环。
>为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。

 >示例 1：
>输入：head = [3,2,0,-4], pos = 1
>输出：true
>解释：链表中有一个环，其尾部连接到第二个节点。

>示例 2：
>输入：head = [1,2], pos = 0
>输出：true
>解释：链表中有一个环，其尾部连接到第一个节点。

>示例 3：
>输入：head = [1], pos = -1
>输出：false
>解释：链表中没有环。

***解题思路***：链表中常用快慢指针的方法来解决类似题目，快指针为慢指针的两倍速，若快指针经过 NULL 则无环路，若快指针和满指针指向同一结点则链表有环形。
```c
/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     struct ListNode *next;
 * };
 */
bool hasCycle(struct ListNode *head) {
	if (!head||!(head->next))return false;
	struct ListNode* slow = head, *fast = head->next->next;
	while (fast&&fast->next)
	{
		if (slow == fast)
			return true;
		else
		{
			slow = slow->next;
			fast = fast->next->next;
		}
	}
	return false;
}
```


 

