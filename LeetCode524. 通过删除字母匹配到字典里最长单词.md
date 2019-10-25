# 通过删除字母匹配到字典里最长单词
>给定一个字符串和一个字符串字典，找到字典里面最长的字符串，该字符串可以通过删除给定字符串的某些字符来得到。如果答案不止一个，返回长度最长且字典顺序最小的字符串。如果答案不存在，则返回空字符串。

>示例 1:
输入:
s = "abpcplea", d = ["ale","apple","monkey","plea"]
输出: 
"apple"

>来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/longest-word-in-dictionary-through-deleting。

思路分析：

1、基于字典序对 d 内字符串进行排序，len 记录匹配的字符串最大长度，word 记录匹配的最大字符串

2、每次取出 d 中的一个字符串 str 与 s 匹配，判断 str 是否为 s 子串。

3、若 str 遍历字符与 s 遍历字符相等则匹配下一字符，不相等，则遍历 s 下一字符

4、当 str 所有字符都能匹配则 len 记录最大长度，word 记录字符串，无法完全匹配则取 d 下一字符。
```java
public String findLongestWord(String s, List<String> d) {
        if (s == null || d == null || s.length() < 1 || d.size() < 1) {
            return "";
        }
        Collections.sort(d, (s1, s2) -> s1.length() != s2.length() ?
                s1.length() - s2.length() : s1.compareTo(s2)); // 基于字典顺序排序
        int len=0;
        String word = "";
        for (String str : d) {
            if (str.length()>s.length()||!isSubStr(s,str)){ // 不满足字串条件
                continue;
            }
            if (str.length() > len) { // 记录最长子串以及长度
                len = str.length();
                word = str;
            }
        }
        return word;
    }

    private boolean isSubStr(String str, String subStr) { // 判断是否为子串
        int indexStr = 0, indexSub = 0;
        while (indexStr < str.length() && indexSub < subStr.length()) {
            if (str.charAt(indexStr) != subStr.charAt(indexSub)) {
                indexStr++;
            } else {
                indexSub++;
                indexStr++;
            }
        }
        return indexSub == subStr.length();
    }
```

