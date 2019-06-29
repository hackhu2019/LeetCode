#  四数之和 [题目链接](https://leetcode-cn.com/problems/4sum/)

> 给定一个包含 n 个整数的数组 nums 和一个目标值 target，判断 nums 中是否存在四个元素 a，b，c 和 d ，使得 a +
> b + c + d 的值与 target 相等？找出所有满足条件且不重复的四元组。

> 注意：
> 答案中不可以包含重复的四元组。

解题思路：

 1. 在三数之和的思路上修改，首先是对数组进行排序，将 nums 升序排序 
 2. 先选取 nums 中的一个数（第一层外循环），再在剩余数中选取第二个数（第二层循环）  剩余两个数分别从剩余数的最小端 left 、最大端 right扫描数组 
 3. 当四数之和与 target 相等时 ，加入当前四数组合，继续查找下一四数组合。（HashSet 去除重复项）
 4. 若大于 target ，则 right 向左端扫描
 5. 若小于 target ，则 left 向右端扫描

```java
public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> lists = new ArrayList<>();
        HashSet<List<Integer>> map=new HashSet<>(lists);
        int len=nums.length;
        Arrays.sort(nums);
        for(int i=0;i<len-3;i++){
            for(int j=i+1;j<len-2;j++){
                int left=j+1;
                int right=len-1;
                while(left<right){
                    int sum=nums[i]+nums[j]+nums[left]+nums[right];
                    if(sum==target){
                        List<Integer> list = new ArrayList<>() ;
                        list.add(nums[i]);list.add(nums[j]);list.add(nums[left]);list.add(nums[right]);
                        map.add(list); // 用哈希表去重,将匹配的四数集合放入 map
                        left++;
                        right--;
                        continue; // 继续查找下一可能组合
                    }
                    else if(sum>target){
                        right--; // 减小四数和
                    }
                    else {
                        left++; // 增大四数和
                    }
                }
            }
        }
        lists.addAll(map); // 将所有非重复结果加入 lists
        return lists;
    }
```

