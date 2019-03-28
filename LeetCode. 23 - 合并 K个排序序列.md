# 合并 K 个排序序列  [题目链接](https://leetcode-cn.com/problems/merge-k-sorted-lists/)

> 合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。
>示例:
>输入:
>[
  >1->4->5,
  >1->3->4,
  >2->6
>]
>输出: 1->1->2->3->4->4->5->6

解题思路：采用分治的思想，将 K 个链表的合并问题转换成，合并 2 个有序链表的问题
```c
typedef struct ListNode List; // 定义一个 struct ListNode 类型 名为 List
List *mergeTwoLists(List *list1, List *list2)
{
	if (!list1&&!list2) return NULL;//当两条链表都为遍历结束则返回 NULL
	else if (!list1) return list2;//因为都是有序则一条遍历结束后只要将另一条链表接在后面即可
	else if (!list2) return list1;
	else if (list1->val<list2->val)
	{
		list1->next = mergeTwoLists(list1->next, list2);
		return list1;
	}
	else
	{
		list2->next = mergeTwoLists(list1, list2->next);
		return list2;
	}
}
struct ListNode* mergeKLists(struct ListNode** lists, int listsSize)
{
	if (listsSize == 0) return NULL;
	else if (listsSize == 1) return lists[0];
	else if (listsSize == 2) return mergeTwoLists(lists[0], lists[1]);
	return mergeTwoLists(mergeKLists(lists, listsSize / 2),
		mergeKLists(lists + listsSize / 2, listsSize - listsSize / 2));//将 K 个链表分解成 2 个链表的组合
}
```

