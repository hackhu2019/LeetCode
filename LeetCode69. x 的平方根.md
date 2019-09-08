# x 的平方根
>实现 int sqrt(int x) 函数。
计算并返回 x 的平方根，其中 x 是非负整数。
由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。

> 示例 1:
> 输入: 4
> 输出: 2

>来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/sqrtx

思路分析：
1. 基于二分查找法，每次取中值判断其平方是否等于 x
2. 若小于则在左半边查找，大于则在右边查找，左右边界相等则返回最接近平方数的最小值
```java
class Solution {
    public int mySqrt(int x) {
        if (x < 2) { // 处理特殊值
            return x;
        }
        int left = 0, right = x;
        while (left < right) {
            int mid = (left + right) >>> 1; // 取中值
            if (x/mid == mid) { // 找到平方根
                return mid;
            } else if (x / mid > mid) { // 大于则在右边查找
                left = mid + 1;
            } else { // 小于则在左半边查找
                right = mid - 1;
            }
        }
        return x / left < left ? left - 1 : left; // 取最小值
    }
}
```

方法 2 ，基于牛顿迭代法公式：
![牛顿跌代法公式](https://img-blog.csdnimg.cn/20190904105322345.png)

```java
class Solution {
    public int mySqrt(int x) {
        if (x < 2) {
            return x;
        }
        int result = x;
        while (x / result < result) { // 不断迭代，逼近解
            result = (result + x / result) >>> 1; // 迭代公式
        }
        return result;
    }
}
```

