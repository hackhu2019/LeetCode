# LeetCode1208. 尽可能使字符串相等

#### [1208. 尽可能使字符串相等](https://leetcode-cn.com/problems/get-equal-substrings-within-budget/)

解题思路：滑动窗口

1、maxLen 记录最大可转换长度，left、right 分别记录当前窗口左边界与右边界

2、cost 为当前滑动窗口剩余预算

3、依序遍历 s 元素，当 cost- Math.abs(s.charAt(right) - t.charAt(right))

4、若 cost < 0 说明此时无法转换右边界字符，则滑动串口左边界右移，cost+=Math.abs(s.charAt(left) - t.charAt(left))，直到 cost>=0 （即满足条件转换有边界字符）

5、若 cost >= 0 直接更新右边界，同时更新 maxLen 记录最大可转换长度

```java
public int equalSubstring(String s, String t, int maxCost) {
        int maxLen = 0;
        int left = 0, right = 0, cost = maxCost;
        while (right < s.length()) {
            int needCost = Math.abs(s.charAt(right) - t.charAt(right));
            cost -= needCost;
            while (cost < 0) {
                cost += Math.abs(s.charAt(left) - t.charAt(left));
                left++;
            }
            right++;
            maxLen = Math.max(right - left, maxLen);
        }
        return maxLen;
    }
```

优化：避免重复计算 s[i]-t[i] 差值，初始化[] diff 存储每一位差值

```java
public int equalSubstring(String s, String t, int maxCost) {
        int maxLen = 0;
        int left = 0, right = 0, cost = maxCost;
        int[] diff = new int[s.length()];
        for (int i = 0; i < s.length(); i++) {
            diff[i] = Math.abs(s.charAt(i) - t.charAt(i));
        }
        while (right < s.length()) {
            int needCost = diff[right];
            cost -= needCost;
            while (cost < 0) {
                cost += diff[left];
                left++;
            }
            right++;
            maxLen = Math.max(right - left, maxLen);
        }
        return maxLen;
    }
```

