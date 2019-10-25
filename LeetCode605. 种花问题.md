# 种花问题
>假设你有一个很长的花坛，一部分地块种植了花，另一部分却没有。可是，花卉不能种植在相邻的地块上，它们会争夺水源，两者都会死去。
给定一个花坛（表示为一个数组包含0和1，其中0表示没种植花，1表示种植了花），和一个数 n 。能否在不打破种植规则的情况下种入 n 朵花？能则返回True，不能则返回False。

>示例 1:
输入: flowerbed = [1,0,0,0,1], n = 1
输出: True

>来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/can-place-flowers

方法一，思路分析：

1、从 flowerbed 起始点（前一个元素不为0）开始种植花，n--，flag 标记当前是否已种花。

2、若下一位置为 0 ，则上一种植成立，在下一点尝试种植新的花。

3、若下一位置不为 0，则上一种植不合法，N++，flag=false。

若种结束遍历时 n<=0 则能够种植数量为 n 的花，等式不成立则不能

```java
public boolean canPlaceFlowers(int[] flowerbed, int n) {
        boolean flag = false; // 标记当前是否种植花
        for (int i = 0; i < flowerbed.length && n >= 0; i++) {
            if (!flag && flowerbed[i] == 0) {
                if (i > 0 && flowerbed[i - 1] == 1) {
                    continue;
                }
                flag = true;
                n--;
            } else if (flag) {
                flag = false;
                n = flowerbed[i] == 1 ? n + 1 : n;
            }
        }
        return n <= 0;
    }
```
方法二，思路分析：

1、pre 记录前一元素值、next 记录下一元素值，针对首尾边界值进行特殊处理

2、当 pre、next 都为 0 时，则当前种植符合要求，n--

3、不满足，则继续判断下一元素是否符合要求

4、当 n<=0 则能够种植数量为 n 的花

```java
public boolean canPlaceFlowers(int[] flowerbed, int n) {
        for (int i = 0; i < flowerbed.length && n > 0; i++) {
            int pre = i == 0 ? 0 : flowerbed[i - 1],
                    next = i == flowerbed.length - 1 ? 0 : flowerbed[i + 1];
            if (pre == 0 && next == 0 && flowerbed[i] == 0) { // 满足种植条件，更新 n 和 flowerbed[i]
                n--;
                flowerbed[i] = 1;
            }
        }
        return n <= 0;
    }
```

