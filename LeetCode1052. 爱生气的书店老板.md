# LeetCode1052. 爱生气的书店老板

#### [1052. 爱生气的书店老板](https://leetcode-cn.com/problems/grumpy-bookstore-owner/)

解题思路：滑动窗口

1、首次遍历，记录老板不生气时的总客户数 total

3、滑动窗口（固定大小为 X）记录每次窗口内老板生气时的顾客数量 sum

4、依次更新窗口元素，max 存储滑动窗口最大值

```java
public int maxSatisfied(int[] customers, int[] grumpy, int X) {
        int total = 0, max = 0;
    	// 首次遍历，记录老板不生气时的总客户数 total
        for (int i = 0; i < customers.length; i++) {
            if (grumpy[i] == 0) {
                total += customers[i];
            }
        }
    	// 初始化 max 
        for (int i = 0; i < X; i++) {
            if (grumpy[i] == 1) {
                max += customers[i];
            }
        }
        int sum = max;
    	// 依次更新窗口元素，max 存储滑动窗口最大值
        for (int i = X; i < customers.length; i++) {
            sum = sum - customers[i - X] * grumpy[i - X] + customers[i] * grumpy[i];
            max = Math.max(max, sum);
        }
        return total + max;
    }
```

