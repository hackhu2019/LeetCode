# 整数反转 [题目链接](https://leetcode-cn.com/problems/reverse-integer/submissions/)
>给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。

>示例 1:
>输入: 123
>输出: 321

第一种解法思路分析：
按照字符串反转的思路，len 为数组位数（strX 长度），第 i 位和 len-i-1 位交换位置即可。
对于 int 数字转换后可能出现的上溢出、下溢出进行特殊处理，直接返回 0.
```java
public int reverse(int x) {
        if(x<=Integer.MIN_VALUE){ // 处理最下溢出
            return 0;
        }
        if(x<0){
            return -reverse(-x);
        }
        char[] strX=String.valueOf(x).toCharArray();
        int len = strX.length;
        char temp=' ';
        for(int i=0;i<len>>1;i++){ // 反转
            temp=strX[i];
            strX[i]=strX[len-i-1];
            strX[len-i-1]=temp;
        }
        double returnNum =Double.parseDouble(String.copyValueOf(strX));
        return returnNum>Integer.MAX_VALUE?0:(int)returnNum; // 处理上溢出
    }
```

第二种解法，来自于官方解答
解题思路：把反转看做，低位向高位推进的过程。
从 x 的最低位开始处理，每次将  reN ( 返回数 ) 推进一位，取 x 最低位放置 reN的末尾。
当处理到需要 31 位来表示的整数时，判断个位是否大于 Int 最大最小值个位，做特殊处理。

补充：Java Int 可表示最大值： 2147483647、最小值：-2147483648。
```java
public int reverse(int x) {
        int reN = 0; // 返回数
        int reminder = 0; // 余数
        while (x != 0) {
            reminder = x % 10;
            x /= 10;
            if (reN > Integer.MAX_VALUE / 10 || (reN == Integer.MAX_VALUE / 10 && reminder > 7)) { // int最大值个位为 7,超过 7
                return 0;
                }                                                                                 // 则上溢出
            if (reN < Integer.MIN_VALUE / 10 || (reN == Integer.MIN_VALUE / 10 && reminder > 8)) {
                return 0; // Int 最小值个位为 8 此时余数大于 8 则下溢出
            }
            reN = reN*10 + reminder;
        }
        return reN;
    }
```

