# 有效的括号 [题目链接](https://leetcode-cn.com/problems/valid-parentheses/)

> 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
>有效字符串需满足：
>左括号必须用相同类型的右括号闭合。
>左括号必须以正确的顺序闭合。
>注意空字符串可被认为是有效字符串。

>示例 1:
>输入: "()"
>输出: true

***思路讲解***：

用单链表模拟栈的压栈和出栈，当输入为左括号时执行压栈，读取到右括号则进行比较，如果和栈顶（链表首结点）存储的括号对应上则出栈，无对应入栈。

单链表与栈特性吻合，只在数据的一边操作，头结点插入与压栈后进先出的操作对应。
```c
struct list
{
	char val;
	struct list* next;
};
bool IsBH(char cr1, char cr2)
{
	return (cr1 == '['&&cr2 == ']') || (cr1 == '{'&&cr2 == '}') || (cr1 == '('&&cr2 == ')');
}
bool isValid(char* s) {
	struct list *p;
	p = NULL;
	for (int i = 0; s[i] != '\0'; i++)
	{
		if (p == NULL)
		{
			struct list *node = (struct list*)malloc(sizeof(struct list));
			node->val = s[i]; node->next = NULL;
			p = node;
		}
		else if (IsBH(p->val, s[i]))
		{
			p = p->next;
		}
		else {
			struct list *node = (struct list*)malloc(sizeof(struct list));
			node->val = s[i]; node->next = p;
			p = node;
		}
	}
	if (p == NULL) return true;
	else
	{
		while (p)
		{
			struct list* node = p;
			p = p->next;
			free(node);

		}
	}
	return false;
}
```
代码优化

当读入右括号无对应左括号，就可以直接结束循环，返回 False 减少不必要的内存消耗

```c
struct list//定义单链表
{
	char val;
	struct list* next;
};
bool IsBH(char cr1, char cr2)//判断括号是否为闭合
{
	return (cr1 == '['&&cr2 == ']') || (cr1 == '{'&&cr2 == '}') || (cr1 == '('&&cr2 == ')');
}
bool isValid(char* s) {
	struct list *p;
	p = NULL;
	for (int i = 0; s[i] != '\0'; i++)
	{
		if (s[i] == '{' || s[i] =='['||s[i]=='(')//左半边括号则入栈
		{
			struct list *node = (struct list*)malloc(sizeof(struct list));
			node->val = s[i]; node->next = p;
			p = node;
		}
		else if (p!=NULL&&IsBH(p->val, s[i]))//完整括号则出栈
		{
			p = p->next;
		}
		else {
			return false;//出现右括号且不匹配则无法形成完整括号，返回 false
		}
	}
	if (p == NULL) return true;
	else
	{
		while (p)//释放链表内存
		{
			struct list* node = p;
			p = p->next;
			free(node);

		}
	}
	return false;
}
```
Java 代码
```java
class Solution {
    public boolean isValid(String s) {
        Stack<Character> sta1=new Stack<>();//定义整数栈
        char[] str=s.toCharArray();//将字符串转换成字符串数组

        for(char cr:str)
        {
            if(cr=='['||cr=='{'||cr=='(')//左括号入栈
                sta1.push(cr);
            else if(sta1.size()!=0&&IsBH(sta1.peek(), cr))//满足完整括号，出栈
                sta1.pop();
            else
               return  false;
        }

        return sta1.size()==0;
    }

    private boolean IsBH(char cr1,char cr2)
    {
        return (cr1=='('&&cr2==')')||(cr1=='{'&&cr2=='}')||(cr1=='['&&cr2==']');
    }
}
```

