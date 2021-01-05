# LeetCode830. 较大分组的位置

#### [830. 较大分组的位置](https://leetcode-cn.com/problems/positions-of-large-groups/)

解题思路：一次遍历

1、依序遍历 s,每遍历不同字符，记录分组长度

2、若分组长度大于等于 3 ，存储起始索引与结束索引

```java
public List<List<Integer>> largeGroupPositions(String s) {
        List<List<Integer>> resultList = new ArrayList<>();
        int index = 0;
        while (index < s.length()) {
            int startIndex = index;
            while (index < s.length() && s.charAt(index) == s.charAt(startIndex)) {
                index++;
            }
            if (index - startIndex >= 3) {
                resultList.add(Arrays.asList(startIndex, index - 1));
            }
        }
        return resultList;
    }
```

