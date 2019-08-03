# 有效的数独

> 判断一个 9x9 的数独是否有效。只需要根据以下规则，验证已经填入的数字是否有效即可。

> 数字 1-9 在每一行只能出现一次。
>  数字 1-9 在每一列只能出现一次。 
>  数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。
> 数独部分空格内已填入了数字，空白格用 '.' 表示。

> 来源：力扣（LeetCode） 
> 链接：https://leetcode-cn.com/problems/valid-sudoku

思路分析：
以 Set 集 contains 方法来判断其中的重复项是否已存在，不存在 set 集中，则添加。

最开始的想法，依据三条规则遍历三次，若出现重复数字则返回 false

优化，一次遍历。

字符数组每一个数字的坐标时固定的，在一开始就能根据他们的坐标确定，它们属于哪一行、哪一列、哪一个小九宫格。

所以定义 9 行、9 列，9个九宫格，每遍历 1 个数字，则判断在其所属的行、列、九宫格是否已存在，重复则返回 false，不存在 set 集中，则添加。

```java
class Solution {
    public boolean isValidSudoku(char[][] board) {
        // 定义行、列、分割九宫格
        HashSet[] row = new HashSet[9];
        HashSet[] col = new HashSet[9];
        HashSet[] box = new HashSet[9];

        // set 数组初始化
        for (int i = 0; i < 9; i++) {
            row[i] = new HashSet();
            col[i] = new HashSet();
            box[i] = new HashSet();
        }
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    int box_index = i / 3 * 3 + j / 3; // 计算所属的小九宫格块索引，i/3 取整数
                    if (row[i].contains(board[i][j]) || 
                    	col[j].contains(board[i][j]) || 
                    	box[box_index].contains(board[i][j])) {
                        return false;
                    }
                    row[i].add(board[i][j]);
                    col[j].add(board[i][j]);
                    box[box_index].add(board[i][j]);
                }
            }
        }
        return true;
    }
}
```
初始解答，三次遍历，四层循环遍历九宫格，可太刺激了。
```java
class Solution {
    public boolean isValidSudoku(char[][] board) {
        HashSet set = new HashSet();
        // 判断每一行是否有重复数字
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.' &&set.contains(board[i][j])) {
                    return false;
                }
                set.add(board[i][j]);
            }
            set.clear();
        }
        // 判断每一列是否有重复数字
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[j][i] != '.' &&set.contains(board[j][i])) {
                    return false;
                }
                set.add(board[j][i]);
            }
            set.clear();
        }
        // 判断每个九宫格是否有重复数字
        for (int i = 0; i < 9; i+=3) {
            for (int j = 0; j < 9; j+=3) {
                if (!isValid(board, i, j)) {
                    return false;
                }
            }
        }
        return true;
    }
    private boolean isValid(char[][] board,int x,int y){
        HashSet set = new HashSet();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[x+j][y+i] != '.' &&set.contains(board[x+j][y+i])) {
                    return false;
                }
                set.add(board[x+j][y+i]);
            }
        }
        return true;
    }
}
```

