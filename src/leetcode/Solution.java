package leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Solution {
    Set<Set<Character>> list = new HashSet();

    int res = 0;
    public int lengthOfLongestSubstring(String s) {
        char [] array=s.toCharArray();
        dfs(array,0);
        for(Set<Character> c:list){
            res=Math.max(res,c.size());
        }
        return res;
    }
    public void dfs(char [] array,int start){
        int len =array.length;
        Set<Character> set = new HashSet();
        for(int i=start;i<len;i++){
            char c = array[i];
            if(!set.contains(c)){
                set.add(c);
                list.add(new HashSet(set));
                dfs(array,i+1);
            }else {
                set.clear();
                break;
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution().lengthOfLongestSubstring("gfcowtlznsrkpwibprhss"));
    }
}