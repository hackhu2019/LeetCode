# LeetCode509. 斐波那契数

#### [509. 斐波那契数](https://leetcode-cn.com/problems/fibonacci-number/)

解法一：自底向上——动态规划

动态方程 f(n)=f(n-1)+f(n-2)

```java
public int fib(int n) {
        if (n < 2) {
            return n;
        }
        int result = 0, n1 = 1, n2 = 0;
        for (int i = 2; i < n; i++) {
            result = n1 + n2;
            n2 = n1;
            n1 = result;
        }
        return result;
    }
```

解法二：递归

```java
public int fib(int n) {
        if (n < 2) {
            return n;
        }
        return fib(n-1)+fib(n-2);
    }
```

