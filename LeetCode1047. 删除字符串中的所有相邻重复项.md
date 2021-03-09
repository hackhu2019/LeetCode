# LeetCode1047. 删除字符串中的所有相邻重复项

#### [1047. 删除字符串中的所有相邻重复项](https://leetcode-cn.com/problems/remove-all-adjacent-duplicates-in-string/)

解题思路：

1. 依次遍历 S 元素放入栈中，若当前遍历元素与栈顶元素相等，则栈顶元素出栈
2. 否，元素入栈
3. 遍历结束，返回栈内元素

```java
public String removeDuplicates(String S) {
        Deque<Character> stack = new LinkedList<>();
        for (char c : S.toCharArray()) {
            if (!stack.isEmpty() && stack.peek() == c) {
                stack.pop();
            } else {
                stack.push(c);
            }
        }
        StringBuilder result = new StringBuilder();
        while (!stack.isEmpty()) {
            result.insert(0, stack.pop());
        }
        return result.toString();
    }
```

代码优化

```java
public String removeDuplicates(String S) {
        StringBuilder result = new StringBuilder();
        int lastIndex = -1;
        for (char c : S.toCharArray()) {
            if (lastIndex>=0 && result.charAt(lastIndex) == c) {
                result.deleteCharAt(lastIndex--);
            } else {
                result.insert(++lastIndex, c);
            }
        }
        return result.toString();
    }
```

