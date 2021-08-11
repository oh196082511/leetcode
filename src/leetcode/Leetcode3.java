package leetcode;
//给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
//
//         
//
//        示例 1:
//
//        输入: s = "abcabcbb"
//        输出: 3
//        解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
//        示例 2:
//
//        输入: s = "bbbbb"
//        输出: 1
//        解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
//        示例 3:
//
//        输入: s = "pwwkew"
//        输出: 3
//        解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
//             请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
//        示例 4:
//
//        输入: s = ""
//        输出: 0
//         
//
//        提示：
//
//        0 <= s.length <= 5 * 10^4
//        s 由英文字母、数字、符号和空格组成


import leetcode.data.ListNode;

public class Leetcode3 {
    class Solution {
        public int lengthOfLongestSubstring(String s) {
            // 双指针，维护一个int[]
            int hash[] = new int[256];
            for (int i = 0;i < 256;i++) {
                hash[i] = -1;
            }
            int pos_l = 0;
            int max = 0;
            for (int i = 0;i < s.length();i++)  {
                pos_l = Math.max(hash[s.charAt(i)] + 1, pos_l);
                max = Math.max(i - pos_l + 1, max);
                hash[s.charAt(i)] = i;
            }
            return max;
        }
    }

    public static void main(String[] args) {
        Leetcode3.Solution solution = new Leetcode3().new Solution();
        String s = "pwwkew";
        System.out.println(solution.lengthOfLongestSubstring(s));

    }
}
