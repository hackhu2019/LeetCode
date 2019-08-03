# 解数独

> 编写一个程序，通过已填充的空格来解决数独问题。

> 一个数独的解法需遵循如下规则：
> 数字 1-9 在每一行只能出现一次。 数字 1-9 在每一列只能出现一次。 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。
> 空白格用 '.' 表示。
 
> Note: 给定的数独序列只包含数字 1-9 和字符 '.' 。 你可以假设给定的数独只有唯一解。 给定数独永远是 9x9
> 形式的。来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/sudoku-solver

解题思路：
1. 用三个 set 数组分别记录行、列、小九宫格数字。
2. 先初始化数组，并确定数独已有布局
3. 回溯法：执行：向数独中空白区（ '.' ）填充数字。约束：行、列、小九宫格数字不重复。
4. 结束：若无法满足约束，将已填充空白区还原，填充另一数字。
5. 当遍历至九宫格末尾说明找到正确解，返回结果。
```java
char[] nums = {'1','2' , '3','4' , '5', '6', '7', '8', '9'};
    // 定义行、列、分割九宫格
    HashSet[] row = new HashSet[9];
    HashSet[] col = new HashSet[9];
    HashSet[] box = new HashSet[9];

    public void solveSudoku(char[][] board) {
        // set 数组初始化
        for (int i = 0; i < 9; i++) {
            row[i] = new HashSet();
            col[i] = new HashSet();
            box[i] = new HashSet();
        }
        for (int i = 0; i < 9; i++) { // 确定数独已有布局
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    int box_index = i / 3 * 3 + j / 3; // 计算所属的小九宫格块索引，i/3 取整数
                    row[i].add(board[i][j]);
                    col[j].add(board[i][j]);
                    box[box_index].add(board[i][j]);
                }
            }
        }
        recusiveSlove(board, 0, 0);
    }

    private boolean recusiveSlove(char[][] board, int x, int y) {
        if (y == 9) {
            x++;
            if (x == 9) { // 找到唯一正确解
                return true;
            }
            y = 0; // 遍历完一层
        }
        if (board[x][y] == '.') {
            int box_index = x / 3 * 3 + y / 3;
            for (int i = 0; i < 9; i++) {
                if (!row[x].contains(nums[i])&&
                        !col[y].contains(nums[i])&&
                        !box[box_index].contains(nums[i])){ // 满足规则，不存在重复
                    board[x][y] = nums[i];
                    row[x].add(nums[i]);
                    col[y].add(nums[i]);
                    box[box_index].add(nums[i]);
                    if (recusiveSlove(board, x, y + 1)) { // 找到正确解
                        return true;
                    } else { // 找不到正确解，还原数独
                        board[x][y] = '.';
                        row[x].remove(nums[i]);
                        col[y].remove(nums[i]);
                        box[box_index].remove(nums[i]);
                    }
                }
            }
        } else {
            return recusiveSlove(board, x, y+1); // 已有数字，跳到下一格
        }
        return false;
    }
```

