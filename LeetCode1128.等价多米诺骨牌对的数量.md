# LeetCode1128.等价多米诺骨牌对的数量

#### [1128.等价多米诺骨牌对的数量](https://leetcode-cn.com/problems/number-of-equivalent-domino-pairs/)

解题思路：参考 [官方题解](https://leetcode-cn.com/problems/number-of-equivalent-domino-pairs/solution/deng-jie-duo-mi-nuo-gu-pai-dui-de-shu-li-yjlz/)

1、总对数计算规则：第 N 次出现等价多米诺骨牌，增加的对的数量为 n (注：[1,2],[2,1] 为 1次出现)

2、多米诺骨牌二维坐标转一维坐标，转换方式： dominoe[0] * 9 + dominoe[1]， dominoe[0]、dominoe[1] 分别代表横纵坐标较大者

3、因为 1 <= dominoes\[i]\[j] <= 9，所以将二维坐标转换为一维坐标后 [2,90]可以表示所有坐标

4、count[] 记录每对多米诺骨牌出现次数

5、每遍历一张多米诺骨牌 总最对数+=result += count[index]，同时更新count[index]

```java
public int numEquivDominoPairs(int[][] dominoes) {
    	// 多米诺骨牌二维坐标转一维坐标
        int[] count = new int[91];
    	// 总最对数
        int result = 0;
    	// 遍历多米诺骨牌
        for (int[] dominoe : dominoes) {
            int index = dominoe[0] > dominoe[1] ? dominoe[0] * 9 + dominoe[1] : dominoe[1] * 9 + dominoe[0];
            result += count[index];
            count[index]++;
        }
        return result;
    }
```

