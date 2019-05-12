# Z 字形变换 [题目链接](https://leetcode-cn.com/problems/zigzag-conversion/)
>将一个给定字符串根据给定的行数，以从上往下、从左到右进行 Z 字形排列。
>比如输入字符串为 "LEETCODEISHIRING" 行数为 3 时，排列如下：

>L   C   I   R
>E T O E S I I G
>E   D   H   N

>之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："LCIRETOESIIGEDHN"。
>请你实现这个将字符串进行指定行数变换的函数：
>string convert(string s, int numRows);

>示例 1:
>输入: s = "LEETCODEISHIRING", numRows = 3
>输出: "LCIRETOESIIGEDHN"

思路分析：

1、Z 形排列可以可以由两部分组成，第一部分为竖排按顺序排列，第二部分为斜排从第二排开始倒序排列

2、由此可得 Z 字形的周期 2numRows-2

3、只要依序遍历字符串，将每一字符放入对应行，再将每行相加就是需要返回的 Z 形字符串。
```java
public String convert(String s, int numRows) {
        // Z 字形的周期 2numRows-2
        if(s.isEmpty()||numRows<=1) return s;
        String[] row = new String[numRows+1]; // 存储每一行字符
        for(int i=0;i<numRows;i++) // 将每一行字符串初始化为 "" ,不初始化则默认为 null
        {
            row[i]="";
        }
        String result = "";
        int index=0;
        int len = s.length();
        int cycle=numRows+numRows-2; // 周期
        int count=0; // 记录周期变换
        while(index!=len)
        {
            if(count<numRows) // 竖排
            {
                row[count]+=s.charAt(index);
            }
            else{ // 斜排
                row[numRows*2-count-2]+=s.charAt(index);
            }
            count++;
            index++;
            if(count==cycle)
                count=0;
        }
        for(int i=0;i<numRows;i++)
        {
            result+=row[i]; // 每一行加入返回数组
        }
        return result;
    }

```

