package com.xj.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 *
 * 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
 */
public class NumSum {
    public static void main(String[] args) {
        int [] nums = {1,4,56,5,89,9};
        int target = 9;
        System.out.println(getSumIndex(nums, target));
    }
    public static List<Integer> getSumIndex(int[] nums, int target){
        HashMap<Integer,Integer> map = new HashMap<>();
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if(map.get(target - nums[i]) == null) {
                map.put(target - nums[i],i);
            }
            if (map.get(nums[i]) != null) {
                res.add(map.get(nums[i]));
                res.add(i);
            }
        }

        return res;
    }
}
