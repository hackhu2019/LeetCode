# 回文数 [题目链接](https://leetcode-cn.com/problems/palindrome-number/)

> 判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。

> 示例 1:
> 输入: 121 输出: true 

>示例 2:
> 输入: -121 输出: false 
> 解释: 从左向右读, 为 -121 。
>  从右向左读, 为 121- 。
>  因此它不是一个回文数。

初始写法，采用「推进」思想，将整数 x 从右到左放入新整数中，即「低位向高位推进」。

虽然答案通过，但是未考虑，推进后可能产生的新数溢出整数范围问题。

```java
class Solution {
    public boolean isPalindrome(int x) {
        if(x<0) // 负数则直接返回 false
        {
            return false;
        }
        int newNum=x%10; // 初始化为 x 个位值
        int tempNum=x/10; // temp 存储 x 截取个位后的新值
        while(tempNum>0){ // 若 tempNum 为 0 则推进结束
            newNum=newNum*10+tempNum%10;
            tempNum/=10;
        }
        // System.out.println(x+","+newNum);
        if(newNum==x) // 推进后产生的新数与源数相等则
        {
            return true;
        }
        return false;
    }
}
```
参考 [LeetCode 中文版官方题解](https://leetcode-cn.com/problems/palindrome-number/solution/hui-wen-shu-by-leetcode/)

只需比较前半段和半段是否对称即可
```java
class Solution {
    public boolean isPalindrome(int x) {
        if(x<0||(x%10==0&&x!=0)) // 为负数或个位为 0 则不构成回文数
        {
            return false;
        }
        int newNum=x%10;
        x/=10;
        while(x>newNum){
            newNum=newNum*10+x%10;
            x/=10;
        }

        return x==newNum||x==newNum/10; // x 为偶数位回文数则左右对称相等，奇数位回文则以中位数对称相等
    }
}
```

