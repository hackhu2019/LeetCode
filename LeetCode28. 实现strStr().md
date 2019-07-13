# 实现strStr() [题目链接](https://leetcode-cn.com/problems/implement-strstr/)
实现 strStr() 函数。

> 给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置
> (从0开始)。如果不存在，则返回  -1。
 
> 示例 1:
> 输入: haystack = "hello", needle = "ll" 输出: 2
> 来源：力扣（LeetCode）

BF 暴力法思路：

 1. 在主串 haystack 中找到与，模式串 needle 相匹配的第一个字符
 2. 再比较后序字符是否相等，然后再在后序的字符中继续匹配
 3. 若完全一致则返回主串匹配首字符索引，若不匹配则继续寻找匹配子串

```java
class Solution {
    public int strStr(String haystack, String needle) {
        if (needle == null || "".equals(needle)) {
            return 0;
        }
        int len1 = haystack.length();
        int len2 = needle.length();
        for (int i = 0; i <= len1 - len2; i++) {
            if (haystack.charAt(i) == needle.charAt(0)&&haystack.substring(i,i+len2).equals(needle)) {
                return i;
            }
        }
        return -1; // 默认返回 -1
    }
}
```
RK 算法在 BF 算法的基础上优化匹配的方式，建立哈希表，若计算出的哈希值一致，则认为二者为同一字符。
```java
class Solution {
    public int strStr(String haystack, String needle) {
        if (needle == null || "".equals(needle)) {
            return 0;
        }
        Map<String, Integer> map = new HashMap<>();
        map.put(needle, 1);
        int len1 = haystack.length();
        int len2 = needle.length();
        for (int i = 0; i <= len1 - len2; i++) {
            if (haystack.charAt(i) == needle.charAt(0)&&map.get(haystack.substring(i,i+len2))!=null) {
                return i;
            }
        }
        return -1;
    }
}
```
BM、KMP 有点复杂，后面理解清楚了再写对应解法。
