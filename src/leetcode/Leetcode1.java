package leetcode;
//给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。
//
//        你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
//
//        你可以按任意顺序返回答案。
//
//         
//
//        示例 1：
//
//        输入：nums = [2,7,11,15], target = 9
//        输出：[0,1]
//        解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
//        示例 2：
//
//        输入：nums = [3,2,4], target = 6
//        输出：[1,2]
//        示例 3：
//
//        输入：nums = [3,3], target = 6
//        输出：[0,1]
//         
//
//        提示：
//
//        2 <= nums.length <= 104
//        -109 <= nums[i] <= 109
//        -109 <= target <= 109
//        只会存在一个有效答案


import java.util.HashMap;
import java.util.Map;

public class Leetcode1 {
    class Solution {
        public int[] twoSum(int[] nums, int target) {
            // 遍历nums,如果map里没有target-nums[i]，则map里增加key value，如果有直接输出答案
            int[] ans = new int[2];
            Map<Integer, Integer> map = new HashMap<>();
            for (int i = 0;i < nums.length;i++) {
                if (map.containsKey(target - nums[i])) {
                    ans[0] = i;
                    ans[1] = map.get(target - nums[i]);
                    return ans;
                } else {
                    map.put(nums[i], i);
                }
            }
            return null;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Leetcode1().new Solution();

        int[] nums = new int[3];
        nums[0] = 1;
        nums[1] = 2;
        nums[2] = 3;
        int target = 4;
        int[] ints = solution.twoSum(nums, target);
        System.out.println("" + ints[0] + " " + ints[1]);
    }
}

