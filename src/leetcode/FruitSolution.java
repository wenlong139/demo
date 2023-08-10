package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author qinwenlong
 * @Date 2022/10/17
 **/
public class FruitSolution {
    static int res = 0;
    static Map<Integer, Integer> cat2NumMap = new HashMap();

    public static void main(String[] args) {
        int[] fruits = new int[]{0,1,2,2};
        res=totalFruit(fruits);
        System.out.println(res);
    }

    public static int totalFruit(int[] fruits) {
        int len = fruits.length;
        int res = 0;
        Map<Integer,Integer> cat2NumMap = new HashMap();
        int left = 0;int right =0;
        while(left<=right&&right<len){
            Integer num = cat2NumMap.get(fruits[right]);
            if(cat2NumMap.size()<2){
                if(num==null){
                    cat2NumMap.put(fruits[right],1);
                }else{
                    num++;
                    cat2NumMap.put(fruits[right],num);
                }
                right++;
            }else{
                if(num==null){
                    left++;
                    cat2NumMap.clear();
                    right=left;
                }else{
                    num++;
                    cat2NumMap.put(fruits[right],num);
                    right++;
                }
            }
            res=Math.max(res,cat2NumMap.values().stream().mapToInt(Integer::intValue).sum());

        }
        return res;
    }

    public static void backTrack(int[] fruits, int cur) {
        int len = fruits.length;
        Integer num = cat2NumMap.get(fruits[cur]);
        boolean isAdd = false;
        if (cat2NumMap.size() == 2) {
            if (num != null) {
                num++;
                cat2NumMap.put(fruits[cur], num);
                isAdd = true;
            } else {
                return;
            }
        } else {
            isAdd = true;
            if (num != null) {
                num++;
                cat2NumMap.put(fruits[cur], num);
            } else {
                cat2NumMap.put(fruits[cur], 1);
            }
        }
        res = Math.max(res, cat2NumMap.values().stream().mapToInt(Integer::intValue).sum());
        if (cur == len - 1) {
            return;
        }
        backTrack(fruits, cur + 1);

        if (isAdd) {
            if (num != null) {
                num--;
                if (num > 0) {
                    cat2NumMap.put(fruits[cur], num);
                } else {
                    cat2NumMap.remove(fruits[cur]);
                }
            } else {
                cat2NumMap.remove(fruits[cur]);
            }
        }

    }
}
