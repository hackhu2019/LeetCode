# 键盘行 [题目链接](https://leetcode-cn.com/problems/keyboard-row/)
> 给定一个单词列表，只返回可以使用在键盘同一行的字母打印出来的单词

思路：根据传入字符串的首字母判断是在哪一行，若出现不同行的字符则结束查找，所有字符皆在同一行则放入返回的字符串数组中。
```java
public String[] findWords(String[] words) {
        String[] str ={"QWERTYUIOP","ASDFGHJKL","ZXCVBNM"};
        int count;
        int index;
        String test;
        List<String> new_words = new LinkedList(); // 定义一个 String 类型链表，存储属于同一行的输入
        for (String word : words) {
            count=0; // 记录符合长度的元素个数
            test =String.valueOf(word.toUpperCase().charAt(0)) ;
            if(str[0].contains(test)) // 根据首字母判断是属于哪一行
                {
                    index=0;
                }
                else if(str[1].contains(test))
                {
                    index=1;
                }
                else{
                    index=2;
                }
            for (char c : word.toUpperCase().toCharArray()) {
                if(str[index].contains(String.valueOf(c)))
                {
                    count+=1;
                }
                else
                    break; // 若出现不属于同一行的字母则结束循环
            }
            if(count==word.length()) // 根据统计的符合的元素个数判断是否都在一行
                new_words.add(word);
        }
        return new_words.toArray(new String[0]); // 将泛型列表转换为字符串数组
    }
```

