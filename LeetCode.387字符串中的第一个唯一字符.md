# LeetCode.387字符串中的第一个唯一字符

#### [387. 字符串中的第一个唯一字符](https://leetcode-cn.com/problems/first-unique-character-in-a-string/)

解法一：哈希计数法

1首次遍历字符串，统计每个字符串出现次数

2第二次遍历，若字符出现次数为 1，返回 索引，遍历结束无只出现1次的字符返回 -1

```java
public int firstUniqChar(String s) {
        int[] count = new int[26];
        char[] chars = s.toCharArray();
        for (char c : chars) {
            count[c - 'a']++;
        }
        for (int i = 0; i < chars.length; i++) {
            if (count[chars[i] - 'a'] == 1) {
                return i;
            }
        }
        return -1;
    }
```

进一步优化，遍历时不统计次数，而是记录数组索引，若遍历相同字符则将存放索引置为 -1，

遍历结束，返回统计数组中除 -1 外最小值

```java
class Solution {
    public int firstUniqChar(String s) {
        int[] count = new int[26];
        // 初始化统计数组
        Arrays.fill(count, Integer.MAX_VALUE);
        char[] chars = s.toCharArray();
        for (int index = 0; index < chars.length; index++) {
            count[chars[index] - 'a'] = count[chars[index] - 'a'] == Integer.MAX_VALUE ? index : -1;
        }
        int minIndex = Integer.MAX_VALUE;
        for (int i = 0; i < count.length; i++) {
            minIndex = count[i] == -1 ? minIndex : Math.min(minIndex, count[i]);
        }
        return minIndex == Integer.MAX_VALUE ? -1 : minIndex;
    }
}
```



解法二：计数+双端队列

1、首次遍历字符串，统计每个字符串出现次数,队列依序存放字符索引

2、若当前队首索引字符出现次数>1,则队首字符出队，遍历结束，若队列不为空，返回队首字符

```java
public int firstUniqChar(String s) {
        int[] count = new int[26];
        Deque<Integer> deque = new LinkedList<>();
        char[] chars = s.toCharArray();
        for (int index = 0; index < chars.length; index++) {
            count[chars[index] - 'a']++;
            deque.offerLast(index);
            while (!deque.isEmpty() && count[chars[deque.getFirst()] - 'a'] > 1) {
                deque.poll();
            }
        }
        return deque.isEmpty() ? -1 : deque.getFirst();
    }
```

