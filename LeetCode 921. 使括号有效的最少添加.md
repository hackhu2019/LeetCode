# 使括号有效的最少添加 [题目链接](https://leetcode-cn.com/problems/minimum-add-to-make-parentheses-valid/)

> 给定一个由 '(' 和 ')' 括号组成的字符串 S，我们需要添加最少的括号（ '(' 或是 ')'，可以在任何位置），以使得到的括号字符串有效。

>从形式上讲，只有满足下面几点之一，括号字符串才是有效的：
>它是一个空字符串，或者
>它可以被写成 AB （A 与 B 连接）, 其中 A 和 B 都是有效字符串，或者
>它可以被写作 (A)，其中 A 是有效字符串。
>给定一个括号字符串，返回为使结果字符串有效而必须添加的最少括号数。

思路：

1、将传入字符串转换成数组。定义一个字符型栈，将字符压栈。

2、当栈不为空时比较栈顶字符与新遍历字符是否能够合并为完整括号，满足则出栈，否则入栈。

3、字符遍历结束，栈中元素个数即Wie必须添加的最少括号数。
```java
class Solution {
    private boolean IsBH(char cr1,char cr2)
    {
        //当栈顶为前括号，cr 为后括号时返回 True
        return cr1=='('&&cr2==')';
    }

    public int minAddToMakeValid(String S) {
        //int count =0;
        Stack<Character> stk1=new Stack<>();
        char[] str=S.toCharArray();
        
        for(char c :str)
        {
            if(stk1.size()==0)
                stk1.push(c);
            else if(IsBH(stk1.peek(), c))
            {
                stk1.pop(); 
            }
            else
            {
                stk1.push(c); 
            }
        }
        

        return stk1.size();
    }
}
```

