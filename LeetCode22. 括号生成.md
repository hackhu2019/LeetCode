# 22. 括号生成 [题目链接](https://leetcode-cn.com/problems/generate-parentheses/)
解题思路：创建组合原理，先放左括号，再放右括号，左括号数不能超过 n，右括号数要与右括号数一致

```java
public List<String> generateParenthesis(int n) {
            List<String> list = new ArrayList<>();
            backList(list, "",0,0,n);
            return list;
        }
        /**
         * @param list
         * @param str 组合
         * @param left 左括号数量
         * @param right 右括号数量
         * @param max 括号对数
         */
        private void backList(List<String> list,String str,int left,int right,int max){
            if(str.length()==max*2){
                list.add(str);
                return;
            }
            if (left < max) {
                backList(list, str+"(", left + 1, right, max);
            }
            if (right < left) {
                backList(list,str+")",left,right+1,max);
            }
        }
```

