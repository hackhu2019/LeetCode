# 罗马数字转整数 [题目链接](https://leetcode-cn.com/problems/roman-to-integer/)

> 罗马数字包含以下七种字符: I， V， X， L，C，D 和 M。
> 字符          数值
>  I             1
>   V             5 
>   X             10
>    L           50 
>    C             100 
>    D             500
>     M             1000 

> 例如， 罗马数字 2 写做 II ，即为两个并列的 1。
> 12 写做 XII ，即为 X + II 。
>  27 写做  XXVII, 即为 XX + V + II 。

> 通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 IIII，而是 IV。数字 1 在数字 5
> 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。同样地，数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况：
> 
> I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。 X 可以放在 L (50) 和 C (100) 的左边，来表示
> 40 和 90。  C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
> 给定一个罗马数字，将其转换成整数。输入确保在 1 到 3999 的范围内。

解题思路：

 1. 根据罗马数字符号转换表，建立相应的散列表，字符为 key,数值为 value 
 2. 将字符串 s 转换为字符数组依序遍历 num为每次遍历字符值总和 
 3. 正常情况下，从最高位开始罗马数所遍历字符对应值都小于前一字符 若当前遍历字符大于前一字符则需要根据特殊规则计算值
 4. 当前遍历字符为前一字符 5 倍则 num 加上上一遍历字符值*3 
 5. 当前遍历字符为前一字符 10 倍则 num 加上上一遍历字符值*8

```java
class Solution {
    public int romanToInt(String s){
        char[] str=s.toCharArray();
        HashMap<Character,Integer> map = new HashMap<>();
        map.put('M', 1000);
        map.put('D', 500);
        map.put('C', 100);
        map.put('L', 50);
        map.put('X', 10);
        map.put('V', 5);
        map.put('I', 1);
        int num=map.get(str[0]) ; // num 初始化为首字符值
        int len = str.length;
        int value=0; // value 存储当前遍历字符对应的值
        int preValue=0; // preValue 存储上一遍历字符对应的值
        for(int i=1;i<len;i++)
        {
            value=map.get(str[i]);
            preValue=map.get(str[i-1]);
            if(value>preValue){
                num+=value/preValue==5?preValue*3:preValue*8; 
                // 为前一字符 5 倍则 num 加上上一遍历字符值*3，否则 num 加上上一遍历字符值*8
            }
            else{
                num+=value; // 无对应特殊规则加上对应值
            }
        }
        return num;
    }


}
```

