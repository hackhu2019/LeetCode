# LeetCode228. 汇总区间

#### [228. 汇总区间](https://leetcode-cn.com/problems/summary-ranges/)

解题思路：一次遍历。若相邻元素差值不为 1 则分成两个区间；差值为1，则合为同一区间

```java
public List<String> summaryRanges(int[] nums) {
        List<String> resultList = new ArrayList<>();
        int index = 0;
        while (index < nums.length) {
            StringBuilder range = new StringBuilder();
            range.append(nums[index]);
            int end = index + 1;
            while (end < nums.length) {
                if (nums[end] - nums[end - 1] != 1) {
                    break;
                }
                end++;
            }
            if (end - 1 != index) {
                range.append("->"+nums[end - 1]);
            }
            index = end;
            resultList.add(range.toString());
        }
        return resultList;
    }
```


  