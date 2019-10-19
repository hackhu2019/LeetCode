# 快乐数
>编写一个算法来判断一个数是不是“快乐数”。
一个“快乐数”定义为：对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和，然后重复这个过程直到这个数变为 1，也可能是无限循环但始终变不到 1。如果可以变为 1，那么这个数就是快乐数。

>示例: 
输入: 19
输出: true
解释: 
12 + 92 = 82
82 + 22 = 68
62 + 82 = 100
12 + 02 + 02 = 1

>来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/happy-number

解题思路：set 记录已计算数字，当出现重复数字时说明不是快乐数（一直在进行死循环）,否，为快乐数，因为会枚举到 1 的结果。

```java
public boolean isHappy(int n) {
        HashSet<Integer> set = new HashSet<>();
        set.add(n);
        while (n != 1) {
            int sum = 0;
            while (n != 0) {
                sum += (n % 10) * (n % 10);
                n /= 10;
            }
            if (set.contains(sum)) {
                return false;
            }
            n = sum;
            set.add(sum);
        }
        return true;
    }
```

