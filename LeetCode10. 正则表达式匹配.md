#  正则表达式匹配 [题目里链接](https://leetcode-cn.com/problems/regular-expression-matching/)

> 给定一个字符串 (s) 和一个字符模式 (p)。实现支持 '.' 和 '*' 的正则表达式匹配。

> '.' 匹配任意单个字符。
>  '*' 匹配零个或多个前面的元素。 匹配应该覆盖整个字符串 (s) ，而不是部分字符串。

> 说明:
>  s 可能为空，且只包含从 a-z 的小写字母。
>   p 可能为空，且只包含从 a-z 的小写字母，以及字符 . 和 *。

参考:[中文社区解答](https://leetcode-cn.com/problems/regular-expression-matching/solution/dong-tai-gui-hua-dfs-by-wein/)

思路分析：
 1. 当模式串为空时，字符串为空，则匹配成功，否则匹配失败。
 2. 当模式串只有 1 个字符时，只有字符串也为 1 个且与模式串字符相等或模式串剩余字符为「.」能匹配成功。
 3. 当模式串不止 1 个字符时，判断下一个模式串字符是否为 「 *  」 ，否则比较当前字符串与模式串字符是否相等或模式串字符为「.」是，将模式串与字符串去除当前遍历字符，递归匹配，否，匹配失败。
 4. 若第二个模式串字符为 '*' 则判断当前二者字符是否匹配若后序字段满足匹配，匹配成功
 5. 不满足则消除字符串与模式串当前字符相等字符,继续匹配后序字符串

```java
class Solution {
    public boolean isMatch(String s, String p) {
        
        if(p.isEmpty()){ // 当模式串为空时，字符串为空，则匹配成功，否则匹配失败
            return s.isEmpty();
        }
        else if(p.length()==1){
            return s.length()==1&&(s.charAt(0)==p.charAt(0)||p.charAt(0)=='.');
        }
        else if(p.charAt(1)!='*'){ // 判断模式串第二位不为 * 的情况
            return !s.isEmpty()&&(s.charAt(0)==p.charAt(0)||p.charAt(0)=='.')&&isMatch(s.substring(1), p.substring(1));
        }
        else // 判断当模式串第二位为 * 的情况
        {
            while(!s.isEmpty()&&(s.charAt(0)==p.charAt(0)||p.charAt(0)=='.')){ 
                if( isMatch(s, p.substring(2) ) ){ // 若后序字符串匹配成功则返回 true
                    return true;
                }
                s=s.substring(1); // 消除字符串与模式串当前字符相等字符
            }
            return isMatch(s, p.substring(2)); // 继续匹配后序字符串
        }
    }
}
```

