# 每日温度

> 根据每日 气温 列表，请重新生成一个列表，对应位置的输入是你需要再等待多久温度才会升高的天数。如果之后都不会升高，请输入 0 来代替。

>例如，给定一个列表 temperatures = [73, 74, 75, 71, 69, 72, 76, 73]，你的输出应该是 [1, 1, 4, 2, 1, 1, 0, 0]。

>提示：气温 列表长度的范围是 [1, 30000]。每个气温的值的都是 [30, 100] 范围内的整数。

思路：

返回的天数实际就是与数组后遇见的第一个更大数组的索引差。

特殊情况：遍历完数组没有找到气温更高的数，则保持默认值 0。
```java
public int[] dailyTemperatures(int[] T) {
        int count,len=T.length ;
        //int[] nums = new int[len];
        
        for(int i=0;i<len;i++)
        {
            count=0;
            for(int j=i+1;j<len;j++)
            {
                if(T[j]>T[i])
                {
                    count=j-i;
                    break;
                }
                
            }
            T[i]=count;
        }


        return T;
    }
```

