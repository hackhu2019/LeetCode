# 字符串转换整数 (atoi) [题目链接](https://leetcode-cn.com/problems/string-to-integer-atoi/)
>请你来实现一个 atoi 函数，使其能将字符串转换成整数。

>首先，该函数会根据需要丢弃无用的开头空格字符，直到寻找到第一个非空格的字符为止。
当我们寻找到的第一个非空字符为正或者负号时，则将该符号与之后面尽可能多的连续数字组合起来，作为该整数的正负号；假如第一个非空字符是数字，则直接将其与之后连续的数字字符组合起来，形成整数。

>该字符串除了有效的整数部分之后也可能会存在多余的字符，这些字符可以被忽略，它们对于函数不应该造成影响。
注意：假如该字符串中的第一个非空格字符不是一个有效整数字符、字符串为空或字符串仅包含空白字符时，则你的函数不需要进行转换。
在任何情况下，若函数不能进行有效的转换时，请返回 0。

>说明：
假设我们的环境只能存储 32 位大小的有符号整数，那么其数值范围为 [−231,  231 − 1]。如果数值超过这个范围，qing返回  INT_MAX (231 − 1) 或 INT_MIN (−231) 。

为了处理边界值/特殊值，写出了一串又丑又长的代码。

思路分析：

	字符串转换 将字符串转化成字符数组方便遍历 记录第一个非空字符索引，若为不为数字则返回 0
	出现数字或正负符号，则记录下一非数字字符，或遍历到数组结束 记录数字起始至末尾索引，
	若转换成 double 后大于或小于 Int，最大或最小则返回相应结果
	对于正负号后出现字符不为数字或唯一有效字符串为正负号则返回 0
```java
class Solution {
    public int myAtoi(String str) {
        if (str.isEmpty()) {
            return 0;
        }
        String num = "0123456789"; // 数字集合
        int index = -1; // 有效数字的起始索引和结束索引初识化为 -1
        int endIndex = -1;
        int len = str.length();
        String temp = "";
        for (int i = 0; i < len; i++) {
            temp = str.substring(i, i + 1); // 取出当前遍历字符
            if (index == -1) {
                if (temp.equals("-")||temp.equals("+") || num.contains(temp)) { // 考虑特殊情况首数字为 -、+
                    index = i;
                } else if (temp.equals(" ")) {
                    continue;
                } else {
                    return 0;
                }
                continue;
            } else if (index != -1 && !num.contains(temp)) {
                endIndex = i;
                if((str.charAt(index) == '-'||str.charAt(index) == '+')&&endIndex-1==index){ // 正负号后不为数字则返回 0
                    return 0;
                }
               
                break;
            }
        }
        if (index == -1 || ( (str.charAt(index) == '-'||str.charAt(index) == '+') && index == len - 1)) { // 全为空格或只为正负号则返回 0
            return 0;
        } else if (endIndex == -1) {
            endIndex = len;
        }
        double reN = Double.parseDouble(str.substring(index, endIndex)); // 用 double 存储转换后的数字
        if (reN > Integer.MAX_VALUE) { // 上溢出
            return Integer.MAX_VALUE;
        } else if (reN < Integer.MIN_VALUE) { // 下溢出
            return Integer.MIN_VALUE;
        }
        return (int) reN;
    }
}
```

