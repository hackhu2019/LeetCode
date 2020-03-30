# 面试题62. 圆圈中最后剩下的数字
解法一：暴力枚举

以 new ArrayList<>(n)  模拟圆圈，每次移除 圆圈中按顺序数到的第 m 个数，最后剩余的那个数就是我们需要找的数。

```java
public int lastRemaining(int n, int m) {
        List<Integer> cycle = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            cycle.add(i);
        }
        int startIndex = 0;
        while (n > 1) {
            startIndex = (startIndex + m-1) % cycle.size();
            cycle.remove(startIndex);
            n--;
        }
        return cycle.get(0);
    }
```
解法二：递归

n 个数以 m 报数，最后一个数位置 = （n 个数以 m 报数的最后位置）+ m  --> 写作 (m + len)

因为 (m + len) 可能超出 n，需要取余数  (m + len) % n

```java
public int lastRemaining(int n, int m) {
        int index = 0;
        for (int i = 2; i <= n; i++) {
            index = (m + index) % i;
        }
        return recursive(n, m);
    }

    private int recursive(int n, int m) {
        if (n == 1) {
            return 0;
        }
        int len = recursive(n - 1, m);
        return (m + len) % n;
    }
```

