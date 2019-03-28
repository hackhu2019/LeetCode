# 根据字符出现频率排序 [题目链接](https://leetcode-cn.com/problems/sort-characters-by-frequency/)
> 给定一个字符串，请将字符串里的字符按照出现的频率降序排列。

**参考了评论区 [Felix8bit](https://leetcode-cn.com/felix8bit/) 的答案**

解题思路: 

先根据每个词出现的概率进行统计，再把统计的词按照按照次数从大到小放入新字符串中

代码中实现了 Comparator 接口，在调用 Sort 方法时实现了从大到小排序
```java
class Solution {
    public String frequencySort(String s) { // 先根据每个词出现的概率进行统计，再把统计的词按照按照次数从大到小放入新字符串中
        HashMap<Character, CharFreq> map = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (map.containsKey(c))
                map.get(c).freq++;
            else
                map.put(c, new CharFreq(c, 1));
        }

        StringBuilder result = new StringBuilder();
        ArrayList<CharFreq> list = new ArrayList<>(map.values());
        list.sort(new CharFreq());

        for (CharFreq c : list) {
            for (int i =0 ;i < c.freq;i++)
                result.append(c.c);
        }
        return result.toString();
    }

    class CharFreq implements Comparator<CharFreq> {
        char c;
        int freq;

        public CharFreq() {
            
        }


        public CharFreq(char c, int freq) {
            this.c = c;
            this.freq = freq;
        }

        public int getFreq() {
            return freq;
        }

        public void setFreq(int freq) {
            this.freq = freq;
        }

        public char getC() {
            return c;
        }

        public void setC(int C) {
            this.c = c;
        }

        @Override
        public int compare(CharFreq o1, CharFreq o2) { // 实现 Comparator 接口，让 CharFreq 可以使用 sort 方法
            if (o1.freq - o2.freq > 0)
                return -1;
            else if (o1.freq - o2.freq < 0)
                return 1;
            else
                return 0;
        }
    }
}
```

