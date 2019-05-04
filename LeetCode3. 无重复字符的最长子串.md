# 无重复字符的最长子串
>给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。

>示例 1:
>输入: "abcabcbb"
>输出: 3 
>解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。

解题思路：这道题实际考察的是字符串匹配，我们可以滑动窗口的思路来解决这个问题。建立一个动态的窗口（实际就是无重复的子串）遍历主串，每遍历一个新字符则在窗口中查找是否包含该字符，无则窗口增大，继续遍历。若已存在该字符则记录窗口长度，将窗口滑动至重复字符后，继续向下遍历，最后返回窗口的最大长度。
```java
public int lengthOfLongestSubstring(String s) {
        if(s.isEmpty()) return 0;
        int LongLen = 1; // 初始化最长子串长度为 1 
        int length=s.length();
        String subStr = s.substring(0,1);
        String temp="";
        for(int i=1;i<length;i++)
        {
            temp = s.substring(i,i+1);
            if(subStr.contains(temp))
            {
                if(subStr.length()>LongLen)
                    LongLen = subStr.length();
                subStr=subStr.substring(subStr.indexOf(temp)+1)+temp; // 从重复字符后开始组建新的无重复子串
            }
            else{
                subStr+=temp;
            }
        }
        if(subStr.length()>LongLen) // 考虑特殊情况
                LongLen = subStr.length();
        return LongLen;
    }
```

