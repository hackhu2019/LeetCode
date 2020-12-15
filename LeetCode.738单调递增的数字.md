# LeetCode.738单调递增的数字

#### [738. 单调递增的数字](https://leetcode-cn.com/problems/monotone-increasing-digits/)

解题思路：贪心算法

从最高位判断能取最大值。

规则：后几位必须 ≧ 当前遍历数

实现：从高到低依次遍历 N 每位数值，与后续数值比较

若下一位数大于当前数，则继续遍历下一位

若下一位数等于当前数，则需要判断后续数值是否有小于当前数值，存在则当前遍历数值-1，后续数值全设为 9。

```java
public int monotoneIncreasingDigits(int N) {
        char[] num = (String.valueOf(N)).toCharArray();
        for (int i = 0; i < num.length - 1; i++) {
            // 下一位数大于当前数，继续遍历下一位
            if (num[i + 1] > num[i]) {
                continue;
            }
            for (int j = i+1; j < num.length; j++) {
                // 后续数值有小于当前数值，当前遍历数值-1，后续数值全设为 9
                if (num[j] < num[i]) {
                    num[i]--;
                    Arrays.fill(num, i + 1, num.length, '9');
                    i = j;
                    break;
                } else if (num[j] == num[i]) {
                    continue;
                } else {
                    break;
                }
            }
        }
        return Integer.valueOf(String.valueOf(num));
    }
```

简化代码：[参考官方题解](https://leetcode-cn.com/problems/monotone-increasing-digits/solution/dan-diao-di-zeng-de-shu-zi-by-leetcode-s-5908/)

```java
public int monotoneIncreasingDigits(int N) {
        char[] num = (String.valueOf(N)).toCharArray();
        // 从最高位开始，过滤满足单调递增的位
        int i = 1;
        while (i < num.length && num[i] >= num[i - 1]) {
            i++;
        }
        while (i < num.length) {
            // 处理退位
            while (i > 0 && num[i - 1] > num[i]) {
                num[i - 1] -= 1;
                i--;
            }
            // 剩余位数全设为 9
            for (i += 1; i < num.length; i++) {
                num[i] = '9';
            }
        }
        return Integer.valueOf(String.valueOf(num));
    }
```



