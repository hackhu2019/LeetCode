# LeetCode118. 杨辉三角

[LeetCode 118. 杨辉三角](https://leetcode-cn.com/problems/pascals-triangle/)

```java
/**
     * 规律：第 N 行有 N 个元素，每行起始和末尾元素都为 1，第二行开始第 n 个元素=上一行第N-1 +  第 N-1个
     */
    public List<List<Integer>> generateOne(int numRows) {
        List<List<Integer>> resultList = new ArrayList<>();
        if (numRows == 0) {
            return resultList;
        }
        // 初始化第一行
        resultList.add(Arrays.asList(1));
        for (int i = 1; i < numRows; i++) {
            Integer[] line = new Integer[i + 1];
            line[0] = 1;
            line[line.length - 1] = 1;
            for (int j = 1; j < i; j++) {
                line[j] = resultList.get(i - 1).get(j - 1) + resultList.get(i - 1).get(j);
            }
            resultList.add(Arrays.asList(line));
        }
        return resultList;
    }
```

优化后：

```java
public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> resultList = new ArrayList<>(numRows);
        for (int rowNum = 0; rowNum < numRows; rowNum++) {
            List<Integer> line = new ArrayList<>(rowNum+1);
            for (int colNum = 0; colNum <= rowNum; colNum++) {
                // 首、尾元素特殊处理
                if (colNum == 0 || colNum == rowNum) {
                    line.add(1);
                } else {
                    line.add(resultList.get(rowNum - 1).get(colNum - 1) + resultList.get(rowNum - 1).get(colNum));
                }
            }
            resultList.add(line);
        }
        return resultList;
    }
```

