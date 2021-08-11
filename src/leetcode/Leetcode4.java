package leetcode;

//给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。
//
//         
//
//        示例 1：
//
//        输入：nums1 = [1,3], nums2 = [2]
//        输出：2.00000
//        解释：合并数组 = [1,2,3] ，中位数 2
//        示例 2：
//
//        输入：nums1 = [1,2], nums2 = [3,4]
//        输出：2.50000
//        解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5
//        示例 3：
//
//        输入：nums1 = [0,0], nums2 = [0,0]
//        输出：0.00000
//        示例 4：
//
//        输入：nums1 = [], nums2 = [1]
//        输出：1.00000
//        示例 5：
//
//        输入：nums1 = [2], nums2 = []
//        输出：2.00000
//         
//
//        提示：
//
//        nums1.length == m
//        nums2.length == n
//        0 <= m <= 1000
//        0 <= n <= 1000
//        1 <= m + n <= 2000
//        -106 <= nums1[i], nums2[i] <= 106


import java.util.Arrays;

public class Leetcode4 {
    class Solution {
        public double findMedianSortedArrays(int[] nums1, int[] nums2) {
            // 总长度奇数、偶数两种大case
            // 将中位数左边和右边分别染色,如果nums1只有一种颜色，判断出颜色后直接得到答案
            // 如果nums1数组拥有两种颜色，那么一定能通过二分找到
            if (nums1.length <= nums2.length) {
                return getMid(nums1, nums2);
            }
            return getMid(nums2, nums1);
        }

        private double getMid(int[] nums1, int[] nums2) {
            boolean isJiShu = (nums1.length + nums2.length) % 2 != 0;
            int midLength = (nums1.length + nums2.length) / 2;
            // 如果nums1为空，直接得出答案
            if (nums1.length == 0) {
                if (isJiShu) {
                    // 样例-> nums1 = {}  nums2 = {4,5,6}
                    return nums2[midLength];
                } else {
                    // 样例-> nums1 = {}  nums2 = {4,5,6,8}
                    return (double) (nums2[midLength - 1] + nums2[midLength]) / 2;
                }
            }
            // 如果nums1只有一种颜色成立，直接得出答案
            if (nums1.length == nums2.length) {
                // 如果两个数组数量相同
                if (nums1[0] >= nums2[nums2.length - 1]) {
                    // 偏大色
                    // 样例-> nums1 = {5,6,7,8}  nums2 = {1,2,3,4}
                    return (double) (nums1[0] + nums2[nums2.length - 1]) / 2;
                } else if (nums2[0] >= nums1[nums1.length - 1]) {
                    // 偏小色
                    // 样例-> nums1 = {1,2,3,4}  nums2 = {5,6,7,8}
                    return (double) (nums2[0] + nums1[nums1.length - 1]) / 2;
                }
            } else if (nums1[0] >= nums2[midLength - 1]) {
                // 两者长度不同，nums1偏大色
                if (isJiShu) {
                    // 样例-> nums1 = {5,6,7}  nums2 = {1,2,3,7}
                    // 样例-> nums1 = {5,6,7}  nums2 = {1,2,3,4}
                    return Math.min(nums2[midLength], nums1[0]);
                } else {
                    // 样例-> nums1 = {5,6,7}  nums2 = {1,2,3,4,5}
                    return (double) (nums2[midLength - 1] + Math.min(nums2[midLength], nums1[0])) / 2;
                }
            } else if (nums2[midLength - nums1.length] >= nums1[nums1.length - 1]) {
                // 两者长度不同，nums1偏小色
                if (isJiShu) {
                    // 样例-> nums1 = {1,2,3}  nums2 = {5,6,7,7}
                    return nums2[midLength - nums1.length];
                } else {
                    // 样例-> nums1 = {1,2,3}  nums2 = {5,6,7,7,7}
                    return (double) (Math.max(nums2[midLength - nums1.length - 1], nums1[nums1.length - 1]) + nums2[midLength - nums1.length]) / 2;
                }
            }
            // 如果nums2有两种颜色，一定能通过二分找出来
            return getMidWhenNums1HaveTwoColors(nums1, 0, nums1.length - 1, nums2, isJiShu);
        }

        private double getMidWhenNums1HaveTwoColors(int[] nums1, int l1, int r1, int[] nums2, boolean isJiShu) {
            // nums1不为空，l1 r1表示偏小色的最右边的索引值的搜索范围（左闭右开），r1在此函数里满足 1 <= r1 <= nums1.length - 1
            int m1 = (r1 + l1) / 2;
            int midLength = (nums1.length + nums2.length) / 2;
            int m2 = midLength - m1  - 2;
            int lMax = Math.max(nums1[m1], nums2[m2]);
            int rMin = Math.min(nums1[m1 + 1], nums2[m2 + 1]);
            if (lMax <= rMin) {
                if (isJiShu) {
                    return rMin;
                } else {
                    return (double) (lMax + rMin) / 2;
                }
            } else if (nums1[m1] > rMin) {
                // 往左边缩
                return getMidWhenNums1HaveTwoColors(nums1, l1, m1, nums2, isJiShu);
            } else {
                // 往右边缩
                return getMidWhenNums1HaveTwoColors(nums1, m1 + 1, r1, nums2, isJiShu);
            }

        }
    }

    public static void main(String[] args) {
        Leetcode4.Solution solution = new Leetcode4().new Solution();
        int[] a = new int[] {2};
        int[] b = new int[] {1,3,7 };
        System.out.println(solution.findMedianSortedArrays(a, b));
    }
}
