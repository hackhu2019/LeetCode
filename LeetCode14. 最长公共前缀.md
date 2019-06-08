# 最长公共前缀 [题目链接](https://leetcode-cn.com/problems/longest-common-prefix/)

> 编写一个函数来查找字符串数组中的最长公共前缀。
> 如果不存在公共前缀，返回空字符串 ""。

> 示例 1: 
> 输入: ["flower","flow","flight"] 输出: "fl"
 
 解题思路：

 1. 先考虑特殊情况：strs 为空或只有 1 个字符，则返回 "" 或 strs[0] 。
 2. 以str[0] 字符串长度为基准，依序遍历字符串数组字符。 
 3. 若当前遍历字符串字符与上一字符串字符不同或已遍历当前字符串末尾则结束匹配，返回公共前缀。

```java
class Solution {
    public String longestCommonPrefix(String[] strs) {
        int len = strs.length; // 存储字符串数组长度
        if(len==0){ // 过滤特殊情况
            return "";
        }
        else if(len==1){
            return strs[0];
        }

        int lenStr1=strs[0].length(); // 存储字符串数组第一条字符串长度
        int i=0; // 匹配字符索引
        int j=1; // strs 字符串数组索引
        while(i<lenStr1){
            if(i>=strs[j].length()||strs[j].charAt(i)!=strs[j-1].charAt(i)){ // 无法构成公共前缀则结束遍历
                break;
            }
            else{
                j++;
            }
            if(j==len){
                i++;
                j=1;
            }
        }
        return strs[0].substring(0,i); // 返回公共前缀
    }
}
```

