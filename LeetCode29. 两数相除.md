# 两数相除 [题目链接](https://leetcode-cn.com/problems/divide-two-integers/)

> 给定两个整数，被除数 dividend 和除数 divisor。将两数相除，要求不使用乘法、除法和 mod 运算符。
> 返回被除数 dividend 除以除数 divisor 得到的商。
 
> 示例 1:
> 输入: dividend = 10, divisor = 3 输出: 3
> 来源：力扣（LeetCode）

思路：递归法，将除法转换为减法，递归处理相同操作

 1. 除数与被除数都初始化为正数，商初始值为 1 
 2. 当除数减去被除数 > 被除数时，除数减去被除数，除数翻倍，count 翻倍 
 3. 当除数减去被除数 <  被除数时，被除数缩减一半，cout 减半 
 4. 当除数小于等于 0 ，或 count 为 0 时，则返回结果

```java
public int divide(int dividend, int divisor) {
        if (dividend==Integer.MIN_VALUE && divisor==-1) {
            return Integer.MAX_VALUE;
        }
        long x = dividend < 0 ? -(long) dividend : (long) dividend; // 将除数与被除数转换为正数
        long y = divisor < 0 ? -(long) divisor : (long) divisor;
        int count = recurse(x, y, 1);
        return ((dividend >= 0 && divisor >= 0) || (dividend < 0 && (divisor < 0))) ? count : -count; // 同号则返回递归结果，异号返回相反结果
    }

private int recurse(Long x,Long y,int count){
        if (x <= 0 || count == 0) {
            return 0; // 除数除完，或无法除下被除数
        }
        if (x < y) {
            return recurse(x, y >>> 1, count >>> 1);
        }
        return recurse(x - y, y + y, count + count)+count;
    }

    public static void main(String[] args) {
        LeetCode29 lc = new LeetCode29();
        System.out.println(lc.divide(Integer.MIN_VALUE,-1));
    }
```

参考：[英文社区解答](https://leetcode.com/problems/divide-two-integers/discuss/13512/Recursive-Java-Solution-2ms)

