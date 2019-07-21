#  下一个排列

> 实现获取下一个排列的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。
> 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。 
> 必须原地修改，只允许使用额外常数空间。

> 以下是一些例子，输入位于左侧列，其相应输出位于右侧列。
>  1,2,3 → 1,3,2 
>  3,2,1 → 1,2,
>  3 1,1,5 → 1,5,1

> 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/next-permutation

解题思路：

 1. 过滤特殊情况，若数组为空或长度为 1 则直接原样返回
 2. 逆序遍历数组，若数组为升序，则改为降序，以 len/2 对称交换数组值
 3. 不为升序则从有序区间中找出比临界点大的最小值，二者进行交换
 4. 此时并非下一最小排列，再将有序区间逆序便成为了最小排列

```java
public void nextPermutation(int[] nums) {
        if (nums==null||nums.length<=1){
            return;
        }
        int len = nums.length;
        boolean flag = true; // 默认为降序
        int i=len-1; // 临界点
        for (;i>=1;i--){ // 逆序遍历
            if (nums[i]>nums[i-1]){ // 发现临界点，结束遍历
                flag=false;
                break;
            }
        }
        if (flag){ // 对称交换值
            reverse(nums,0);
        }else { // 找出第二大的数
            int temp = nums[i-1];
            for (int j = i; j < len; j++) {
                if (nums[j] <= nums[i-1]) {
                    swap(nums,i-1,j-1);
                    break;
                }
                if (j == len - 1) {
                    swap(nums,i-1,j);
                }
            }
            // 将有序区间逆序
            reverse(nums,i);
        }
    }
    // 交换数组中二者的值
    private void swap(int[]nums,int index1,int index2){
        int temp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2]=temp;
    }
    // 围绕对称点交换值
    private void reverse(int[] nums,int startIndex){
        int len = nums.length;
        int rang = len - startIndex; // 要进行交换的范围
        for (int i = 0; i < rang/2; i++) {
            swap(nums,startIndex+i,len-i-1);
        }
    }
```
参考[官方题解思路](https://leetcode-cn.com/problems/next-permutation/solution/xia-yi-ge-pai-lie-by-leetcode/)。
