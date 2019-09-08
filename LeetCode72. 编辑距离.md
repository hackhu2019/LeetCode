#  编辑距离
>给定两个单词 word1 和 word2，计算出将 word1 转换成 word2 所使用的最少操作数 。
你可以对一个单词进行如下三种操作：
插入一个字符
删除一个字符
替换一个字符

>示例 1:
输入: word1 = "horse", word2 = "ros"
输出: 3
解释: 
horse -> rorse (将 'h' 替换为 'r')
rorse -> rose (删除 'r')
rose -> ros (删除 'e')

>来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/edit-distance

思路分析：
1. min[i][j] i,j 分别代表 word1、word2 索引，遍历至 min[i][j]  会出现两种情况，word1、word2 当前遍历字符相等 / 不相等
2. 当前遍历字符相等，word1[i-1] == word2[j-1] 则当前操作次数与前一位操作次数相等
3. 当前遍历字符不相等，word1[i-1]！= word2[j-1] 则当前操作次数 = min(删除、插入、替换)+1
4.  min[i-1,j] 代表删除字符,min[i][j-1] 代表插入字符，min[i-1][i-1] 代表替换字符
```java
class Solution {
    public int minDistance(String word1, String word2) {
        if (word1 == null && word2 == null) {
            return 0;
        }
        if (word1 == null) {
            return word2.length();
        }
        if (word2 == null) {
            return word1.length();
        }
        int len1 = word1.length(), len2 = word2.length();
        int[][] min = new int[len1 + 1][len2 + 1];
        // 初始化 min
        for (int i = 0; i <= len1; i++) {
            min[i][0] = i;
        }
        for (int i = 0; i <= len2; i++) {
            min[0][i] = i;
        }
        for (int i = 1; i <= len1; i++) { // 推导 min[i][j] 最小值
            for (int j = 1; j <= len2; j++) {
                if (word1.charAt(i-1) == word2.charAt(j-1)) {
                    min[i][j] = min[i - 1][j - 1];
                } else {
                    int minValue = min[i - 1][j - 1];
                    minValue = minValue > min[i - 1][j] ? min[i - 1][j]:minValue;
                    minValue = minValue > min[i][j - 1] ? min[i][j - 1] : minValue;
                    min[i][j] = minValue+1;
                }
            }
        }
        return min[len1][len2];
    }
}
```

