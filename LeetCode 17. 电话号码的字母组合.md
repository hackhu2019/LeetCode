#  电话号码的字母组合 [题目链接](https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number/submissions/)
>给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。

>给出数字到字母的映射与电话按键相同。注意 1 不对应任何字母。

思路分析：这类求解所有可能的题目，采用 [回溯法](https://baike.baidu.com/item/%E5%9B%9E%E6%BA%AF%E6%B3%95/86074?fr=aladdin) 结合 BFS 广度优先的思想来解答。

穷举每一种组合：

 1. 每次取出当前遍历数字所代表的的字符串 strs ，组合字符串 combin 组合每一种情况，继续遍历下一数字 next_digits。
 2. 若 next_digits 长度为 0 ，则一种组合诞生，将其加入组合集中

。

```java
class Solution {
    Map<Character,String> map = new HashMap<Character,String>(){{ // 哈希表——存储键盘按键所对应的字符串
        put('2', "abc");
        put('2', "abc");
        put('3', "def");
        put('4', "ghi");
        put('5', "jkl");
        put('6', "mno");
        put('7', "pqrs");
        put('8', "tuv");
        put('9', "wxyz");
    }};
    
    List<String> combins= new ArrayList<String>(); // 返回的组合集
    public List<String> letterCombinations(String digits) {
        if(digits.length()!=0){
            backTrack("", digits);
        }
        return combins;
    }

    private void backTrack(String combin,String next_digits){ // 回溯，穷举所有组合
        if(next_digits.length()==0){
            combins.add(combin); // 当 next_digits 长度为 0 说明已遍历完最后一个按键数
        }
        else{
            String strs = map.get(next_digits.charAt(0)); // 取出按键所能表示的字符串
            for(int i=0;i<strs.length();i++){
                String str=strs.substring(i, i+1); // 遍历每一种组合
                backTrack(combin+str, next_digits.substring(1));
            }
        }
    }
}
```
参考自[官方题解](https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number/solution/dian-hua-hao-ma-de-zi-mu-zu-he-by-leetcode/)

