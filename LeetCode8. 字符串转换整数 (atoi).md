# 8. 字符串转换整数 (atoi) [题目链接](https://leetcode-cn.com/problems/string-to-integer-atoi/)
解题思路：

1、当 str 去除前端空格后，第一个字符不为数字、+、-，则直接返回 0

2、sign 记录正负号，默认为 +，

3、 当 sign 为 +，prefix = Integer.MAX_VALUE / 10 suffix = 7

4、 当 sign 为 -，prefix = Integer.MAX_VALUE / 10 suffix = 8

5、start 为有效位起始索引，end 为结束索引，从起始位每遍历一个数字 end++

6、 result 记录转换后的数字，若 start==end 则 return 0

```java
public int myAtoi(String str) {
        str = str.trim();
        int start = 0;
        boolean sign = true;
        if (str.length() > 0 && (str.charAt(start) == '-' || str.charAt(start) == '+')) {
            sign = str.charAt(start) == '+' ? true : false;
            start++;
        }
        int prefix = Integer.MAX_VALUE / 10, suffix;
        if (sign) {
            suffix = 7;
        } else {
            suffix = 8;
        }
        int end = start, result = 0;
        while (end < str.length() && str.charAt(end) <= '9' && str.charAt(end) >= '0') {
        	// 超出 int 范围
            if (result > prefix || (result == prefix && suffix <= str.charAt(end) - '0')) {
                return sign ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
            result = result * 10 + str.charAt(end++) - '0';
        }
        return sign ? result : -result;
    }
```

