# 914. 卡牌分组
解题思路：遍历 deck 以每个非重复数字为 key，出现次数为 value

若所有的 value 存在最大公约数（大于 1），则返回 true，否则返回 false。

```java
public boolean hasGroupsSizeX(int[] deck) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int card : deck) {
            map.put(card, map.getOrDefault(card, 0) + 1);
        }
        int count = 1;
        boolean flag = false;
        for (Integer key : map.keySet()) {
            if (!flag) {
                count = map.get(key);
                flag = true;
            } else {
                count = gcd(count, map.get(key));
            }
            if (count < 2) {
                return false;
            }
        }
        return count != 1;
    }

    /**
     * 求最大公约数
     */
    private int gcd(int a, int b) {
        return a == 0 ? b : gcd(a % b, b);
    }
```

