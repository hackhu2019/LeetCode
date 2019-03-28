# 删除排序链表中的重复元素 II

> 给定一个排序链表，删除所有含有重复数字的节点，只保留原始链表中 没有重复出现 的数字。

>示例 1:
>输入: 1->2->3->3->4->4->5
>输出: 1->2->5

***解题思路：*** 

先借 1 个空结点指向头节点,方便头结点去重

去重，让指针向下遍历，若指针遍历出现与下 1 结点数据相同情况则让指针指向下一节点

若指针位置无变化，则说明无重复，则遍历下一结点

若指针位置变化说明已出现重复，则删除重复结点
```c
struct ListNode* deleteDuplicates(struct ListNode* head) {
	struct ListNode *p = (struct ListNode*)malloc(sizeof(struct ListNode)),
		*node1, *node2;
	p->next = head;
	head = p;
	while (p->next)
	{
		node1 = p->next;
		node2 = node1; //记录 node1 之前的位置
		while (node1->next&&node1->next->val == node1->val)// 若不为空且与下一结点数值重复则指向下一结点
			node1 = node1->next;
		if (node1 == node2)//判断 node1 的位置是否变化
			p = p->next;
		else
			p->next = node1->next;
	}
	return head->next;
}
```

