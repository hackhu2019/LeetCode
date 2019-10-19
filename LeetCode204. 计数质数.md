# 计数质数 [题目链接](https://leetcode-cn.com/problems/count-primes/)
>统计所有小于非负整数 n 的质数的数量。

>示例:
输入: 10
输出: 4
解释: 小于 10 的质数一共有 4 个, 它们是 2, 3, 5, 7 

方法一：isPrimeNumber 判断是否为质数，遍历 【2-n】出现质数则 count+1，遍历结束返回 count

```java
public int countPrimes(int n) {
        if (n <= 2) {
            return 0;
        }
        int count = 1;
        for (int i = 3; i < n; i++) {
            count = isPrimeNumber(i) ? count + 1 : count;
        }
        return count;
    }

    private boolean isPrimeNumber(int n){
        int sqrt = (int)Math.sqrt(n)+1;
        for (int i = 2; i <= sqrt ; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
```
方法二：set 记录【2-n】 中两个数所有乘积组合（小于 n），若 【2-n】 中未在 set 中则为质数

```java
public int countPrimes(int n) {
        if (n <= 2) {
            return 0;
        }
        boolean[] isPrim = new boolean[n];
        Arrays.fill(isPrim, true);
        for (int i = 2; i < n; i++) {
            for (int j = 2; j*i < n; j++) { // 最小乘积组合 2*2
                isPrim[j * i] = false;
            }
        }
        int count = 0;
        for (int i = 2; i < n; i++) { // 统计所有质数
            if (isPrim[i]) {
                count++;
            }
        }
        return count;
    }
```

