# LeetCode.860柠檬水找零

#### [860. 柠檬水找零](https://leetcode-cn.com/problems/lemonade-change/)

思路分析：贪心算法，每次从最大面值开始找零

1、moneys[2]，分别存放 5元、10 元钱币数量

2、依次遍历账单 bills ，若是 5 元直接入账，依次从最大面值开始尝试找零（可以多张找回），更新 moneys[]

3、若遍历完 moneys，账单未完成找零，返回 false，成功找零，更新 moneys[]

```java
public boolean lemonadeChange(int[] bills) {
        int[] moneys = new int[2];
        for (int bill : bills) {
            int money = bill;
            if (bill == 5) {
                moneys[0]++;
            } else {
                // 减去 5 元饮料费
                bill -= 5;
                // 每次先从最大面值开始找零
                for (int i = moneys.length - 1; i >= 0; i--) {
                    int realMoney = (i + 1) * 5;
                    if (realMoney <= bill && moneys[i] > 0) {
                        int count = bill / realMoney;
                        if (count > moneys[i]) {
                            bill -= moneys[i] * realMoney;
                            moneys[i] = 0;
                        } else {
                            bill -= count * realMoney;
                            moneys[i] -= count;
                        }
                    }
                    // 收入 5 元 、10 元
                    if (bill == 0) {
                        if (money < 20) {
                            moneys[money / 5 - 1]++;
                        }
                        break;
                    }
                }
                // 无法完成找零，返回 false
                if (bill != 0) {
                    return false;
                }
            }
        }
        return true;
    }
```

简化写法，单纯针对 5、10、20 找零

```java
public boolean lemonadeChange(int[] bills) {
        int[] moneys = new int[2];
        for (int bill : bills) {
            switch (bill){
                case 5:
                    moneys[0]++;
                    break;
                case 10:
                    if (moneys[0] > 0) {
                        moneys[0]--;
                        moneys[1]++;
                    } else {
                        return false;
                    }
                    break;
                case 20:
                    if (moneys[1] > 0) {
                        moneys[1]--;
                        bill -= 10;
                    }
                    // 减去 5 元饮料费
                    bill -= 5;
                    if (moneys[0] > 0 && moneys[0] >= bill / 5) {
                        moneys[0] -= bill / 5;
                    } else {
                        return false;
                    } 
            }
        }
        return true;
    }
```



