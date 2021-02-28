# LeetCode395. 至少有 K 个重复字符的最长子串

#### [395. 至少有 K 个重复字符的最长子串](https://leetcode-cn.com/problems/longest-substring-with-at-least-k-repeating-characters/)

解法一：分治法，查考官方 [题解](https://leetcode-cn.com/problems/longest-substring-with-at-least-k-repeating-characters/solution/zhi-shao-you-kge-zhong-fu-zi-fu-de-zui-c-o6ww/)

1、遍历统计 s 中每个字符出现的频率

2、以字符出现次数小于 k 的字符切分字符串，最长字串必定出现在其中，result 记录满足要求子串的最大长度

3、若所有字符出现次数均大于 K ，直接返回该字串长度

```java
public int longestSubstring(String s, int k) {
        return dfs(s.toCharArray(), 0, s.length(), k);
    }

    private int dfs(char[] str, int left, int right, int k) {
        int[] count = new int[26];
        // 遍历统计 s 中每个字符出现的频率
        for (int i = left; i < right; i++) {
            count[str[i] - 'a']++;
        }
        char split = 0;
        // 记录切分字符串
        for (int i = 0; i < count.length; i++) {
            if (count[i] > 0 && count[i] < k) {
                split = (char) (i + 'a');
            }
        }
        // 所有字符出现次数均大于 K ，直接返回该字串长度
        if (split == 0) {
            return right - left;
        }
        int result = 0, l = left;
        while (l < right) {
            while (l < right && str[l] == split) {
                l++;
            }
            if (l >= right) {
                break;
            }
            int star = l;
            // 依次切分字符串
            while (l < right && str[l] != split) {
                l++;
            }
            int len = dfs(str, star, l, k);
            result = Math.max(result, len);
        }
        return result;
    }
```

